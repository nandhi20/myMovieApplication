package com.xpand.challenge.controller;

import org.springframework.context.annotation.Description;
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
	@Description("API to get all actors")
	public ResponseEntity<?> getActors() {
		return ResponseEntity.ok().body(actorService.getActors());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getActor(@PathVariable Long id) throws Exception {
		return ResponseEntity.ok().body(actorService.getActor(id));
	}
	
	@GetMapping("/movie_id/")
	public ResponseEntity<?> getActorsByMovie(
			@RequestParam(name = "movieId", required = true) Long movieId) throws Exception {
		return ResponseEntity.ok().body(actorService.getActorsByMovie(movieId));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createActor(@RequestBody ActorDTO actorDTO) {
		actorService.createActor(actorDTO);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateActor(@PathVariable Long id, @RequestBody ActorDTO actorDTO) throws Exception {
		actorService.updateActor(id, actorDTO);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteActor(@PathVariable Long id) {
		actorService.deleteActor(id);
		return ResponseEntity.noContent().build();
	}

}
