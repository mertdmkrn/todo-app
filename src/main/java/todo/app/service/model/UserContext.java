package todo.app.service.model;

public class UserContext {
	
	private int userId;
	
	private String name;
	
	private String userName;
	
	private String userPsw;
	
	private int roleId;
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPsw() {
		return userPsw;
	}
	
	public void setUserPsw(String userPsw) {
		this.userPsw = userPsw;
	}
	
	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
