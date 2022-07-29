package com.xpand.challenge.dto;

import java.util.Optional;

import com.xpand.challenge.model.Actor;

public class ActorDTOMapper {

	public static IdentifiableActorDTO toActorDTO(Actor actor) {
		return Optional.ofNullable(actor).map(a -> {
			IdentifiableActorDTO dto = new IdentifiableActorDTO();
			dto.setId(actor.getId());
			dto.setActorName(actor.getActorName());
			dto.setBirthDate(actor.getBirthDate());
			dto.setGender(actor.getGender());
			dto.setMovie(MovieDTOMapper.toMovieDTO(actor.getMovie()));
			return dto;
		}).orElse(null);
	}

	public static Actor fromActorDTO(ActorDTO dto) {
		return Optional.ofNullable(dto).map(d -> {
			Actor actor = new Actor();
			actor.setActorName(dto.getActorName());
			actor.setBirthDate(dto.getBirthDate());
			actor.setGender(dto.getGender());
			actor.setMovie(MovieDTOMapper.fromMovieDTO(dto.getMovie()));
			actor.getMovie().setId(dto.getMovie().getId());
			return actor;
		}).orElse(null);
	}
}
