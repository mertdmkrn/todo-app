package todo.app.dao.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import todo.app.dao.entity.UserInfo;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Integer>  {

	@Query(value = "SELECT u from UserInfo u")
	public List<UserInfo> getAllUserList();
	
	@Query(value = "SELECT u.role.roleName from UserInfo u where u.userNick = :usernick")
	public List<String> getUserRole(@Param("usernick") String usernick);

	@Query(value = "SELECT u from UserInfo u where u.userNick = :usernick")
	public UserInfo findByNick(@Param("usernick") String usernick);
	
	@Query(value = "SELECT u from UserInfo u where u.userId = :id")
	public UserInfo findById(@Param("id") int id);
	
	@Query(value = "SELECT max(u.userId) from UserInfo u")
	public int findMaxId();
	
}
