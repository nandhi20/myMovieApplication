package com.xpand.challenge.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.ActorDTOMapper;
import com.xpand.challenge.dto.IdentifiableActorDTO;
import com.xpand.challenge.model.Actor;
import com.xpand.challenge.repository.ActorRepository;
import com.xpand.challenge.service.ActorService;

@Service
public class ActorServiceImpl implements ActorService {

	private final ActorRepository actorRepository;

	public ActorServiceImpl(ActorRepository actorRepository) {
		this.actorRepository = actorRepository;
	}

	@Override
	public List<IdentifiableActorDTO> getActors() {
		return actorRepository.findAll().stream().map(ActorDTOMapper::toActorDTO).collect(Collectors.toList());
	}

	@Override
	public IdentifiableActorDTO createActor(ActorDTO actorDTO) {
		Actor actor = ActorDTOMapper.fromActorDTO(actorDTO);
		return ActorDTOMapper.toActorDTO(actorRepository.save(actor));
	}

	@Override
	public void updateActor(Long id, ActorDTO actorDTO) {
		actorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Actor not found - " + id));
		Actor actor = ActorDTOMapper.fromActorDTO(actorDTO);
		actor.setId(id);
		actorRepository.save(actor);
	}

	@Override
	public void deleteActor(Long id) {
		actorRepository.deleteById(id);
	}

	@Override
	public IdentifiableActorDTO getActor(Long id) {
		return actorRepository.findById(id).map(ActorDTOMapper::toActorDTO)
				.orElseThrow(() -> new NoSuchElementException("Actor not found - " + id));
	}

	@Override
	public List<IdentifiableActorDTO> getActorsByMovie(Long id) {
		return actorRepository.findAllByMovie_Id(id).stream().map(ActorDTOMapper::toActorDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<IdentifiableActorDTO> getActorsByPage(Integer pageOffset, Integer pageSize) {
		return actorRepository.findAll(PageRequest.of(pageOffset, pageSize)).stream().map(ActorDTOMapper::toActorDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<IdentifiableActorDTO> getActorsByMovieAndPage(Long movieId, Integer pageOffset, Integer pageSize) {
		return actorRepository.findAllByMovie_Id(movieId, PageRequest.of(pageOffset, pageSize)).stream()
				.map(ActorDTOMapper::toActorDTO).collect(Collectors.toList());
	}
}
