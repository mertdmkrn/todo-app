package todo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todo.app.dao.entity.Role;
import todo.app.dao.jpa.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> getAllRoleList() {
		
		return roleRepository.getAllRoleList();
		
	}
	
}