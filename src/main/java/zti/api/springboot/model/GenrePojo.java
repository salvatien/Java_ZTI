package zti.api.springboot.model;

import zti.api.springboot.jpa.Genre;

public class GenrePojo {
	public Long id;
	public String name;
	
	public GenrePojo(Genre genre) {
		this.id = genre.getId();
		this.name = genre.getName();
	}
}
