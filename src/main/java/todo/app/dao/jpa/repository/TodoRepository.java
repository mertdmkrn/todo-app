package todo.app.dao.jpa.repository;

import java.util.List;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import todo.app.dao.entity.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer>  {

	@Query(value = "SELECT t from Todo t")
	public List<Todo> getAllTodoList();

	@Query(value = "SELECT t from Todo t where t.user.userId = :id")
	public List<Todo> findByUserId(@Param("id") int id);
	
	@Query(value = "SELECT t from Todo t where t.todoId = :id")
	public Todo findById(@Param("id") int id);
	
	@Query(value = "SELECT t from Todo t where t.todoDate = :date")
	public List<Todo> findByDate(@Param("date") Date date);
	
	@Query(value = "SELECT t from Todo t where t.todoDate = :date and t.user.userId = :id")
	public List<Todo> findByDateandUserId(@Param("date") Date date,@Param("id") int id);
	
	@Query(value = "SELECT max(t.todoId) from Todo t")
	public int findMaxId();

}
