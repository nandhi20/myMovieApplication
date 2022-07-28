package com.xpand.challenge.service;

import java.util.List;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.IdentifiableActorDTO;

public interface ActorService {

	List<IdentifiableActorDTO> getActors();

	IdentifiableActorDTO createActor(ActorDTO actorDTO);

	void updateActor(Long id, ActorDTO actorDTO);

	void deleteActor(Long id);

	IdentifiableActorDTO getActor(Long id);

	List<IdentifiableActorDTO> getActorsByMovie(Long id);
}
