package zti.api.springboot.jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Mode implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
    @OneToMany(mappedBy = "mode", fetch = FetchType.EAGER)
	private Set<Genre> genres = new HashSet<>();


    public Mode() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set <Genre> genres) {
       this.genres = genres;
    }

}
