package zti.api.springboot.jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Genre implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
    @OneToMany(mappedBy = "genre", fetch = FetchType.EAGER)
	private Set<Question> questions = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modeId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Mode mode;


    public Genre() {
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
    
    public Long getModeId(){
        return mode.getId();
    }

    public String getModeName(){
        return mode.getName();
    }

    @JsonIgnore
    public Mode getMode() {
        return mode;
    }

    @JsonIgnore
    public void setMode(Mode mode) {
        this.mode = mode;
    }
    
    
    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set <Question> questions) {
       this.questions = questions;
    }

}