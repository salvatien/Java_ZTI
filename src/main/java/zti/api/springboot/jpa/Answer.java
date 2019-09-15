/**
 * 
 */
package zti.api.springboot.jpa;

/**
 * @author salva
 *
 */
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity(name = "Answer")
@Table(name = "answer")
@Transactional
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String text;
	@ManyToOne(fetch = FetchType.EAGER)
	private Question question;
	private boolean isCorrect;
	
	public Answer() {

	}

	public Answer(String text, Question question, boolean isCorrect) {
		super();
		this.text = text;
		this.question = question;
		this.isCorrect = isCorrect;
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
	
	public boolean getIsCorrect() {
		return isCorrect;
	}
	
	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
	    //prevent endless loop
	    if (sameAsFormer(question))
	      return ;
	    //set new question
	    Question oldQuestion = this.question;
	    this.question = question;
	    //remove from the old question
	    if (oldQuestion!=null)
	      oldQuestion.removeAnswer(this);
	    //set this is answer to question
	    if (question!=null)
	      question.addAnswer(this);
	}
	
	public String toString() {
        return "Answer with Id " + this.id;
    }

	
	private boolean sameAsFormer(Question newQuestion) {
	    return question==null? newQuestion == null : question.equals(newQuestion);
	}
}
