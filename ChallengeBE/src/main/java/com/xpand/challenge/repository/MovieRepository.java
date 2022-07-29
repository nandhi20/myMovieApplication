package com.xpand.challenge.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xpand.challenge.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	List<Movie> findAllByDate(LocalDate date);

	List<Movie> findAllByDate(LocalDate date, Pageable pageable);
}
