package com.xpand.challenge.controller;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.service.ActorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/actors", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActorController {

	private final ActorService actorService;

	public ActorController(ActorService actorService) {
		this.actorService = actorService;
	}

	@Operation(summary = "Endpoint to get all actors", description = "Pagination can be applied. Filter with movie id can be applied")
	@GetMapping
	public ResponseEntity<?> getActors(
			@RequestParam(name = "movieId", required = false) @Parameter(description = "Movie id", example = "1") Optional<Long> movieId,
			@RequestParam(name = "pageOffset", required = false) @Parameter(description = "page number", example = "1") Optional<Integer> pageOffset,
			@RequestParam(name = "pageSize", required = false) @Parameter(description = "number of actors per page", example = "5") Optional<Integer> pageSize) {
		if (movieId.isPresent() && pageOffset.isPresent() && pageSize.isPresent())
			return ResponseEntity.ok()
					.body(actorService.getActorsByMovieAndPage(movieId.get(), pageOffset.get(), pageSize.get()));
		else if (movieId.isPresent())
			return ResponseEntity.ok().body(actorService.getActorsByMovie(movieId.get()));
		else if (pageOffset.isPresent() && pageSize.isPresent())
			return ResponseEntity.ok().body(actorService.getActorsByPage(pageOffset.get(), pageSize.get()));
		return ResponseEntity.ok().body(actorService.getActors());
	}

	@Operation(summary = "Get actor", description = "Get actor by id")
	@GetMapping("/{id}")
	public ResponseEntity<?> getActor(@PathVariable @Parameter(description = "Movie id", example = "1") Long id) {
		return ResponseEntity.ok().body(actorService.getActor(id));
	}

	@Operation(summary = "Create actor", description = "Create new actor")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createActor(@RequestBody @Parameter(description = "New Actor Object") ActorDTO actorDTO) {
		actorService.createActor(actorDTO);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "Update actor", description = "Update actor by id")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateActor(@PathVariable @Parameter(description = "Actor id", example = "1") Long id,
			@RequestBody @Parameter(description = "Actor object json to be updated") ActorDTO actorDTO) {
		actorService.updateActor(id, actorDTO);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Delete actor", description = "Delete an existing actor by id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteActor(@PathVariable @Parameter(description = "Actor id", example = "1") Long id) {
		actorService.deleteActor(id);
		return ResponseEntity.noContent().build();
	}
}
