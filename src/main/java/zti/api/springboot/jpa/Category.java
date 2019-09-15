/**
 * 
 */
package zti.api.springboot.jpa;

import java.util.ArrayList;
import java.util.List;

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
import javax.transaction.Transactional;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "Category")
@Table(name = "category")
@Transactional
public class Category {

	private Long id;
	private String name;
	private String description;
	
	private List<Question> questions = new ArrayList<>();
	
	public Category() {

	}

	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	

	 /**
	   * Returns a collection of questions. The 
	   * returned collection is a defensive copy.
	   *  
	   * @return a list of questions
	   */
	@OneToMany(
	        mappedBy = "category",
	        cascade = javax.persistence.CascadeType.ALL,
	        orphanRemoval = true
	    )
	@Cascade({CascadeType.SAVE_UPDATE})
	  public List<Question> getQuestions() {
	    //defensive copy, nobody will be able to change 
	    //the list from the outside
	    return new ArrayList<Question>(questions);
	  }
	public void setQuestions(List<Question> questions) {
	    this.questions = questions;
	  }

	  /**
	   * Add new account to the person. The method keeps 
	   * relationships consistency:
	   * * this person is set as the account owner
	   */
	  public void addQuestion(Question newQuestion) {
	    //prevent endless loop
	    if (questions.contains(newQuestion))
	      return ;
	    //add new question
	    questions.add(newQuestion);
	    //set this into question
	    newQuestion.setCategory(this);
	  }
	  
	  /**
	   * Removes the answer from the question. The method keeps 
	   * relationships consistency:
	   * * the answer will no longer reference this question
	   */
	  public void removeQuestion(Question question) {
	    //prevent endless loop
	    if (!questions.contains(question))
	      return ;
	    //remove the question
	    questions.remove(question);
	    //remove this category from question
	    question.setCategory(null);
	  }
	  
		public String toString() {
	        return "Category with Id " + this.id;
	    }
}
