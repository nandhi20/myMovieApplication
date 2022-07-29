package com.xpand.challenge.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
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

import com.xpand.challenge.dto.MovieDTO;
import com.xpand.challenge.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@Operation(summary = "Endpoint to get all movies", description = "Pagination can be applied. Filter with release date can be applied")
	@GetMapping
	public ResponseEntity<?> getMovies(
			@RequestParam(name = "date", required = false) @Parameter(name = "date", description = "Release date", example = "2022-03-18") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date,
			@RequestParam(name = "pageOffset", required = false) @Parameter(name = "pageOffset", description = "page number", example = "1") Optional<Integer> pageOffset,
			@RequestParam(name = "pageSize", required = false) @Parameter(name = "pageSize", description = "number of movies per page", example = "5") Optional<Integer> pageSize) {
		if (date.isPresent() && pageOffset.isPresent() && pageSize.isPresent())
			return ResponseEntity.ok()
					.body(movieService.getMoviesByDateAndPage(date.get(), pageOffset.get(), pageSize.get()));
		else if (date.isPresent())
			return ResponseEntity.ok().body(movieService.getMoviesByDate(date.get()));
		else if (pageOffset.isPresent() && pageSize.isPresent())
			return ResponseEntity.ok().body(movieService.getMoviesByPageing(pageOffset.get(), pageSize.get()));
		return ResponseEntity.ok().body(movieService.getMovies());
	}

	@Operation(summary = "Get Movie by id", description = "Return movie with id provided")
	@GetMapping("/{id}")
	public ResponseEntity<?> getMovie(@PathVariable Long id) {
		return ResponseEntity.ok().body(movieService.getMovie(id));
	}

	@Operation(summary = "Create movie", description = "Creates new movie into DB")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createMovie(@RequestBody @Parameter(description = "Movie object json") MovieDTO movieDTO) {
		movieService.createMovie(movieDTO);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "Update movie", description = "Updates existing movie")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMovie(
			@PathVariable @Parameter(description = "Movie id to be updated", example = "1") Long id,
			@RequestBody @Parameter(description = "Movie object json to be updated") MovieDTO movieDTO) {
		movieService.updateMovie(id, movieDTO);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Delete movie", description = "Deletes existing movie")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMovie(
			@PathVariable @Parameter(description = "Movie id to be deleted", example = "1") Long id) {
		movieService.deleteMovie(id);
		return ResponseEntity.noContent().build();
	}
}
