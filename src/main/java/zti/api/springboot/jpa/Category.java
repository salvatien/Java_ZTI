package zti.api.springboot.jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "Category")
@Table(name = "category")
public class Category implements Serializable {
//    @Column(name = "ID", nullable = false, length = 10)

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    @NotBlank(message = "Enter the category name")
	private String name;
	private String description;
	
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Set<Question> questions = new HashSet<>();


    public Category() {
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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set <Question> questions) {
       this.questions = questions;
    }

}
