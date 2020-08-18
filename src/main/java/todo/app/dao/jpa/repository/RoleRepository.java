package todo.app.dao.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import todo.app.dao.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>  {

	@Query(value = "SELECT r from Role r")
	public List<Role> getAllRoleList();

}
