package com.xpand.challenge.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xpand.challenge.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {

	List<Actor> findAllByMovie_Id(Long movieId);

	List<Actor> findAllByMovie_Id(Long movieId, Pageable pageable);

}
