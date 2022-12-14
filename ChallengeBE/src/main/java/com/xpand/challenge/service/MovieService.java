package com.xpand.challenge.service;

import java.time.LocalDate;
import java.util.List;

import com.xpand.challenge.dto.IdentifiableMovieDTO;
import com.xpand.challenge.dto.MovieDTO;
import com.xpand.challenge.model.Movie;

public interface MovieService {

	IdentifiableMovieDTO createMovie(MovieDTO movieDTO);

	IdentifiableMovieDTO getMovie(Long id);

	List<IdentifiableMovieDTO> getMovies();

	List<IdentifiableMovieDTO> getMoviesByDate(LocalDate date);

	Movie getMovieById(Long id);

	void updateMovie(Long id, MovieDTO movieDTO);

	void deleteMovie(Long id);

	List<IdentifiableMovieDTO> getMoviesByDateAndPage(LocalDate date, Integer pageOffset, Integer pageSize);

	List<IdentifiableMovieDTO> getMoviesByPageing(Integer pageOffset, Integer pageSize);
}
