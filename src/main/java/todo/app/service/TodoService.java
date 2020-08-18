package todo.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todo.app.dao.entity.Todo;
import todo.app.dao.jpa.repository.TodoRepository;
import todo.app.service.model.TodoContext;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	public List<Todo> getAllTodoList() {
		
		return todoRepository.getAllTodoList();
		
	}
	
	public List<Todo> findByDate(Date date) {
		
		return todoRepository.findByDate(date);
		
	}
	
	public List<Todo> findByDateandUserId(Date date,int id) {
		
		return todoRepository.findByDateandUserId(date,id);
		
	}
	
	public void deleteItem(int id) {
		
		todoRepository.deleteById(id);
	
	}
	
	public List<Todo> findByUserId(int id) {
		
		return todoRepository.findByUserId(id);
		
	}
	
	public Todo findById(int id) {

		return todoRepository.findById(id);

	}
	
	@Transactional
	public int save(TodoContext todoContext) {
		
		Todo todo = new Todo();
		
		int maxId = todoRepository.findMaxId() + 1;
		
		todo.setTodoId(maxId);
		todo.setTodoInfo(todoContext.getTodoInfo());
		todo.setTodoDate(todoContext.getTodoDate());
		todo.setTodoStatus("Beklemede");
		todo.setUserId(todoContext.getUserId());
		
		todo = todoRepository.save(todo);
		
		return todo.getTodoId();
		
	}
	
	@Transactional
	public int update(TodoContext todoContext) {
		
		Todo todo = new Todo();
		
		
		todo.setTodoId(todoContext.getTodoId());
		todo.setTodoInfo(todoContext.getTodoInfo());
		
		if(todoContext.getTodoStatus().equals("Tamamlandı"))
			todo.setTodoDate(new Date());
		else
			todo.setTodoDate(todoContext.getTodoDate());
		
		todo.setTodoStatus(todoContext.getTodoStatus());
		todo.setUserId(todoContext.getUserId());
		
		todo = todoRepository.save(todo);
		
		return todo.getTodoId();
		
	}

	
}
