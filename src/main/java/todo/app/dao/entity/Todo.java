package todo.app.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "todo")
public class Todo {
	
	@Id
	@Column(name = "todo_id")
	private int todoId;
	
	@Column(name = "todo_info")
	private String todoInfo;	

	@Column(name = "todo_date")
	@Temporal(TemporalType.DATE)
	private Date todoDate;
	
	@Column(name = "todo_status")
	private String todoStatus;	
	
	
	@Column(name = "user_id")
	private int userId;	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserInfo user;

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}

	public String getTodoInfo() {
		return todoInfo;
	}

	public void setTodoInfo(String todoInfo) {
		this.todoInfo = todoInfo;
	}

	public Date getTodoDate() {
		return todoDate;
	}

	public void setTodoDate(Date todoDate) {
		this.todoDate = todoDate;
	}

	public String getTodoStatus() {
		return todoStatus;
	}

	public void setTodoStatus(String todoStatus) {
		this.todoStatus = todoStatus;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
