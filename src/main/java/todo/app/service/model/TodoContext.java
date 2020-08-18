package todo.app.service.model;

import java.sql.Date;

public class TodoContext {
	
	private int todoId;
	
	private String todoInfo;
	
	private Date todoDate;
	
	private String todoStatus;
	
	private int userId;

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}

}
