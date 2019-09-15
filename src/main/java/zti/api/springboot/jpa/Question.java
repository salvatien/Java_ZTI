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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "Question")
@Table(name = "question")
@Transactional
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
	private Category category;
	
	private String text;
	@OneToMany(
	        mappedBy = "question",
	        orphanRemoval = true
	    )
	@Fetch(value=FetchMode.JOIN)
	@Cascade({CascadeType.SAVE_UPDATE})
	private List<Answer> answers = new ArrayList<>();

	public Question() {
		
	}
	
	public Question(Category category, String text) {
		super();
		this.category = category;
		this.text = text;
	}
	
	public Question(Category category, String text,
			List<Answer> answers) {
		super();
		this.category = category;
		this.text = text;
		this.answers = answers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
	    //prevent endless loop
	    if (sameAsFormer(category))
	      return ;
	    //set new category
	    Category oldCategory = this.category;
	    this.category = category;
	    //remove from the old category
	    if (oldCategory!=null)
	      oldCategory.removeQuestion(this);
	    //set myself into new owner
	    if (category!=null)
	      category.addQuestion(this);
	}
	
	private boolean sameAsFormer(Category newCategory) {
	    return category==null? newCategory == null : category.equals(newCategory);
	}

	  
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	

	 /**
	   * Returns a collection of answers. The 
	   * returned collection is a defensive copy.
	   *  
	   * @return a list of answers
	   */
	  public List<Answer> getAnswers() {
	    //defensive copy, nobody will be able to change 
	    //the list from the outside
	    return new ArrayList<Answer>(answers);
	  }

	  /**
	   * Add new account to the person. The method keeps 
	   * relationships consistency:
	   * * this person is set as the account owner
	   */
	  public void addAnswer(Answer newAnswer) {
	    //prevent endless loop
	    if (answers.contains(newAnswer))
	      return ;
	    //add new answer
	    answers.add(newAnswer);
	    //set this into answer
	    newAnswer.setQuestion(this);
	  }
	  
	  /**
	   * Removes the answer from the question. The method keeps 
	   * relationships consistency:
	   * * the answer will no longer reference this question
	   */
	  public void removeAnswer(Answer answer) {
	    //prevent endless loop
	    if (!answers.contains(answer))
	      return ;
	    //remove this from answer
	    answer.setQuestion(null);
	    //remove the answer
	    answers.remove(answer);
	  }
	  
		public String toString() {
	        return "Question with Id " + this.id;
	    }
}
