package com.xpand.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.ActorDTOMapper;
import com.xpand.challenge.dto.IdentifiableActorDTO;
import com.xpand.challenge.dto.IdentifiableMovieDTO;
import com.xpand.challenge.model.Actor;
import com.xpand.challenge.model.Movie;
import com.xpand.challenge.model.Actor.Gender;

public class ActorMapperTests {
	@Test
	void givenActorDTO_thenReturnMovie() {
		ActorDTO dto = new ActorDTO();
		dto.setActorName("Actor");
		dto.setBirthDate(LocalDate.now());
		dto.setGender(Gender.MALE);
		dto.setMovie(new IdentifiableMovieDTO());
		Actor actor = ActorDTOMapper.fromActorDTO(dto);
		assertNotNull(actor);
		assertEquals(dto.getActorName(), actor.getActorName());
		assertEquals(dto.getBirthDate(), actor.getBirthDate());
		assertEquals(dto.getGender(), actor.getGender());
	}

	@Test
	void givenActor_thenReturnActorDTO() {
		Actor actor = new Actor();
		actor.setId(1l);
		actor.setActorName("Actor");
		actor.setBirthDate(LocalDate.now());
		actor.setGender(Gender.MALE);
		actor.setMovie(new Movie());
		IdentifiableActorDTO dto = ActorDTOMapper.toActorDTO(actor);
		assertNotNull(dto);
		assertEquals(actor.getId(), dto.getId());
		assertEquals(actor.getActorName(), dto.getActorName());
		assertEquals(actor.getBirthDate(), dto.getBirthDate());
		assertEquals(actor.getGender(), dto.getGender());
	}

	@Test
	void doTestNull() {
		assertNull(ActorDTOMapper.fromActorDTO(null));
		assertNull(ActorDTOMapper.toActorDTO(null));
	}

}
