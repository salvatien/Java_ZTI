package zti.api.springboot.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "question")

public class Question  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genreId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Genre genre;
    
    
    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<Answer> answers = new ArrayList<>();
    


    public Question() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getGenre_id(){
        return genre.getId();
    }

    public String getGenreName(){
        return genre.getName();
    }

    @JsonIgnore
    public Genre getGenre() {
        return genre;
    }

    @JsonIgnore
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List <Answer> answers) {
        this.answers = answers;
    }
    
    @JsonIgnore
    public Answer getCorrectAnswer() {
    	return answers.stream()
    			.filter((answer) -> answer.getIsCorrect() == true)
    			.findFirst()
    			.get();
    	
    }
    
    public Long getCorrectAnswerId() {
    	return answers.stream()
    			.filter((answer) -> answer.getIsCorrect() == true)
    			.findFirst()
    			.get()
    			.getId();
    	
    }

}