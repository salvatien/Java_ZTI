/**
 * 
 */
package zti.api.springboot.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
/**
 * @author salva
 *
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Category")
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	@OneToMany(
	        mappedBy = "category",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Question> questions = new ArrayList<>();
	
	public Category() {

	}

	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	
	public void addQuestion(Question question) {
        questions.add(question);
        question.setCategory(this);
    }
 
    public void removeQuestion(Question question) {
    	questions.remove(question);
        question.setCategory(null);
    }

}
