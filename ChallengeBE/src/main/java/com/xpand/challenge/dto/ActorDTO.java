package com.xpand.challenge.dto;

import java.time.LocalDate;

import com.xpand.challenge.model.Actor.Gender;

public class ActorDTO {
	private String actorName;
	private LocalDate birthDate;
	private Gender gender;
	private IdentifiableMovieDTO movie;

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public IdentifiableMovieDTO getMovie() {
		return movie;
	}

	public void setMovie(IdentifiableMovieDTO identifiableMovieDTO) {
		this.movie = identifiableMovieDTO;
	}
}
