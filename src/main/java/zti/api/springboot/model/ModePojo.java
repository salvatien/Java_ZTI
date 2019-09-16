package zti.api.springboot.model;

import zti.api.springboot.jpa.Mode;

public class ModePojo {
	public Long id;
	public String name;
	
	public ModePojo(Mode mode) {
		this.id = mode.getId();
		this.name = mode.getName();
	}
}
