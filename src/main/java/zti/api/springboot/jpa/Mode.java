package zti.api.springboot.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Mode implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
    @OneToMany(mappedBy = "mode", fetch = FetchType.EAGER)
	private List<Genre> genres = new ArrayList<>();


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
    
    
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List <Genre> genres) {
       this.genres = genres;
    }

}
