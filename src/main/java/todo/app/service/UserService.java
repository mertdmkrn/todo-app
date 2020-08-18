package todo.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;

import todo.app.dao.entity.UserInfo;
import todo.app.dao.jpa.repository.UserRepository;
import todo.app.service.model.UserContext;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserInfo> getAllUserList() {
		
		return userRepository.getAllUserList();
		
	}
	
	public UserInfo findByNick(String usernick) {
		
		return userRepository.findByNick(usernick);
		
	}
	
	public void deleteItem(int id) {
		
		userRepository.deleteById(id);
	
	}
	
	public UserInfo findById(int id) {
		
		return userRepository.findById(id);
	
	}
	
	@Transactional
	public int save(UserContext userContext) {
		
		UserInfo userInfo = new UserInfo();
	
		int maxId = userRepository.findMaxId()  +1;
		
		userInfo.setUserId(maxId);
		userInfo.setUserName(userContext.getName());
		userInfo.setUserNick(userContext.getUserName());
		userInfo.setRoleId(userContext.getRoleId());
		userInfo.setUserPsw(userContext.getUserPsw());
		
		userInfo = userRepository.save(userInfo);
		
		return userInfo.getUserId();
	}
	
	@Transactional
	public int update(UserContext userContext) {
		
		UserInfo userInfo = new UserInfo();
		
		userInfo.setUserId(userContext.getUserId());
		userInfo.setUserName(userContext.getName());
		userInfo.setUserNick(userContext.getUserName());
		userInfo.setRoleId(userContext.getRoleId());
		userInfo.setUserPsw(userContext.getUserPsw());
		
		userInfo = userRepository.save(userInfo);
		
		return userInfo.getUserId();
	}
	
	@Override
	public UserDetails loadUserByUsername(String usernick) throws UsernameNotFoundException {
		
		UserInfo userInfo = userRepository.findByNick(usernick);
		
	    if (userInfo == null){
	    	throw new UsernameNotFoundException("Invalid username or password.");
		}
			       
	    List<String> roles = userRepository.getUserRole(usernick);
			         
	    List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
	    
	    PasswordEncoder pswencode = new BCryptPasswordEncoder();
	    
		if(roles!= null){
			
			for(String role: roles)  {
				
				GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
			    grantList.add(authority);
			    
			}
			
		}        
			         
		UserDetails userDetails = (UserDetails) new User(userInfo.getUserNick(),
		pswencode.encode(userInfo.getUserPsw()),grantList);
			        
		return userDetails;
	
	}
		     
}