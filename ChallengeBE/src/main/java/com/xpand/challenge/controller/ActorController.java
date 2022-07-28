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

@RestController
@RequestMapping(path = "/actors", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActorController {

	private final ActorService actorService;

	public ActorController(ActorService actorService) {
		this.actorService = actorService;
	}

	@GetMapping
	public ResponseEntity<?> getActors(@RequestParam(name = "movieId", required = false) Optional<Long> movieId) {
		return !movieId.isPresent() ? ResponseEntity.ok().body(actorService.getActors())
				: ResponseEntity.ok().body(actorService.getActorsByMovie(movieId.get()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getActor(@PathVariable Long id) {
		return ResponseEntity.ok().body(actorService.getActor(id));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createActor(@RequestBody ActorDTO actorDTO) {
		actorService.createActor(actorDTO);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateActor(@PathVariable Long id, @RequestBody ActorDTO actorDTO) {
		actorService.updateActor(id, actorDTO);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteActor(@PathVariable Long id) {
		actorService.deleteActor(id);
		return ResponseEntity.noContent().build();
	}

}
