///**
// * 
// */
//package zti.api.springboot.jpa;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author salva
// *
// */
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.transaction.Transactional;
//
//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//
//@Entity(name = "Question")
//@Table(name = "question")
//@Transactional
//public class Question {
//
//	private Long id;
//	
//
//	private Category category;
//	
//	private String text;
//
//	private List<Answer> answers = new ArrayList<>();
//
//	public Question() {
//		
//	}
//	
//	public Question(Category category, String text) {
//		super();
//		this.category = category;
//		this.text = text;
//	}
//	
//	public Question(Category category, String text,
//			List<Answer> answers) {
//		super();
//		this.category = category;
//		this.text = text;
//		this.answers = answers;
//	}
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "category_id")
//	public Category getCategory() {
//		return category;
//	}
//	
//	public void setCategory(Category category) {
//	    //prevent endless loop
//	    if (sameAsFormer(category))
//	      return ;
//	    //set new category
//	    Category oldCategory = this.category;
//	    this.category = category;
//	    //remove from the old category
//	    if (oldCategory!=null)
//	      oldCategory.removeQuestion(this);
//	    //set myself into new owner
//	    if (category!=null)
//	      category.addQuestion(this);
//	}
//	
//	private boolean sameAsFormer(Category newCategory) {
//	    return category==null? newCategory == null : category.equals(newCategory);
//	}
//
//	  
//	public String getText() {
//		return text;
//	}
//
//	public void setText(String text) {
//		this.text = text;
//	}
//
//	
//
//	 /**
//	   * Returns a collection of answers. The 
//	   * returned collection is a defensive copy.
//	   *  
//	   * @return a list of answers
//	   */
//	@OneToMany(
//	        mappedBy = "question",
//	    	cascade = javax.persistence.CascadeType.ALL,
//	        orphanRemoval = true
//	    )
//	@Fetch(value=FetchMode.JOIN)
//	@Cascade({CascadeType.SAVE_UPDATE})
//	  public List<Answer> getAnswers() {
//	    //defensive copy, nobody will be able to change 
//	    //the list from the outside
//	    return new ArrayList<Answer>(answers);
//	  }
//	
//	public void setAnswers(List<Answer> answers) {
//		this.answers = answers;
//	}
//
//	  /**
//	   * Add new account to the person. The method keeps 
//	   * relationships consistency:
//	   * * this person is set as the account owner
//	   */
//	  public void addAnswer(Answer newAnswer) {
//	    //prevent endless loop
//	    if (answers.contains(newAnswer))
//	      return ;
//	    //add new answer
//	    answers.add(newAnswer);
//	    //set this into answer
//	    newAnswer.setQuestion(this);
//	  }
//	  
//	  /**
//	   * Removes the answer from the question. The method keeps 
//	   * relationships consistency:
//	   * * the answer will no longer reference this question
//	   */
//	  public void removeAnswer(Answer answer) {
//	    //prevent endless loop
//	    if (!answers.contains(answer))
//	      return ;
//	    //remove this from answer
//	    answer.setQuestion(null);
//	    //remove the answer
//	    answers.remove(answer);
//	  }
//	  
//		public String toString() {
//	        return "Question with Id " + this.id;
//	    }
//}
//
//


package zti.api.springboot.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Question")
@Table(name = "question")
public class Question  implements Serializable {

    //@Column(name = "ID", nullable = false, length = 10)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Question must have a text")
    private String text;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;
    
    
    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private Set<Answer> answers = new HashSet<>();
    


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

    //getter method to retrieve the CategoryId
    public Long getCategory_id(){
        return category.getId();
    }

    //getter Method to get the category name
    public String getCategoryName(){
        return category.getName();
    }

    @JsonIgnore
    public Category getCategory() {
        return category;
    }

    @JsonIgnore
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set <Answer> answers) {
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