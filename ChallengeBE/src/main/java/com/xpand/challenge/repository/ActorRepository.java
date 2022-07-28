package com.xpand.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpand.challenge.model.Actor;
import com.xpand.challenge.model.Movie;

public interface ActorRepository  extends JpaRepository<Actor, Long>{

	List<Actor> findByMovie(Movie movie);
}
