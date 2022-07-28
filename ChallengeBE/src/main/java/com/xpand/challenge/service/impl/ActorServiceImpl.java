package com.xpand.challenge.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.ActorDTOMapper;
import com.xpand.challenge.dto.IdentifiableActorDTO;
import com.xpand.challenge.model.Actor;
import com.xpand.challenge.model.Movie;
import com.xpand.challenge.repository.ActorRepository;
import com.xpand.challenge.repository.MovieRepository;
import com.xpand.challenge.service.ActorService;
import com.xpand.challenge.service.MovieService;

@Service
public class ActorServiceImpl implements ActorService {

	private final ActorRepository actorRepository;
	private final MovieService movieService;

	public ActorServiceImpl(ActorRepository actorRepository, MovieService movieService) {
		this.movieService = movieService;
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
	public void updateActor(Long id, ActorDTO actorDTO) throws Exception {
		actorRepository.findById(id).orElseThrow(() -> new Exception("Actor not found - " + id));
		Actor actor = ActorDTOMapper.fromActorDTO(actorDTO);
		actor.setId(id);
		actorRepository.save(actor);
	}

	@Override
	public void deleteActor(Long id) {
		actorRepository.deleteById(id);
	}

	@Override
	public IdentifiableActorDTO getActor(Long id) throws Exception {
		return actorRepository.findById(id).map(ActorDTOMapper::toActorDTO)
				.orElseThrow(() -> new Exception("Actor not found - " + id));
	}

	@Override
	public List<IdentifiableActorDTO> getActorsByMovie(Long id) {
		Movie movie = movieService.getMovieById(id);
		return actorRepository.findByMovie(movie).stream().map(ActorDTOMapper::toActorDTO).collect(Collectors.toList());
	}
}
