package todo.app.web.ui.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import todo.app.dao.entity.UserInfo;
import todo.app.service.RoleService;
import todo.app.service.TodoService;
import todo.app.service.UserService;
import todo.app.service.model.TodoContext;
import todo.app.service.model.UserContext;


@Controller
@RequestMapping("")
public class PageController {
	
		@Autowired
		private UserService userService;
		
		@Autowired
		private TodoService todoService;
		
		@Autowired
		private RoleService roleService;
	
		@RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String login() {
			
	        return "login";
	   
		}
	  	
		@RequestMapping(value = "/user", method = RequestMethod.GET)
	    public String user(Authentication authentication,Model model) {
			
			UserInfo userInfo = userService.findByNick(authentication.getName());
			
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("todoList", todoService.findByUserId(userInfo.getUserId()));
	        return "user";
	        
	    }
	
		@RequestMapping(value = "/user/search", method = RequestMethod.GET)
	    public String userTodoSearch(HttpServletRequest request, Authentication authentication, Model model) throws ParseException {
			
			Date todoDate; 
			String date = request.getParameter("todoDate");
			
			UserInfo userInfo = userService.findByNick(authentication.getName());
			model.addAttribute("userInfo", userInfo);
			
			if(date.equals("") || date==null)
				return "redirect:/user";
			else {	
				todoDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				model.addAttribute("todoList", todoService.findByDateandUserId(todoDate,userInfo.getUserId()));
				return "user";
			}
		
		}
		
		@RequestMapping(value = "/admin", method = RequestMethod.GET)
	    public String admin(Authentication authentication,Model model) {

			model.addAttribute("userInfo", userService.findByNick(authentication.getName()));
			model.addAttribute("userList", userService.getAllUserList());
			model.addAttribute("todoList", todoService.getAllTodoList());
			
	        return "admin";
	        
	    }
		
		@RequestMapping(value = "/admin/search", method = RequestMethod.GET)
	    public String adminTodoSearch(HttpServletRequest request, Authentication authentication,Model model) throws ParseException {
			
			Date todoDate; 
			String date = request.getParameter("todoDate");
			model.addAttribute("userInfo", userService.findByNick(authentication.getName()));
			model.addAttribute("userList", userService.getAllUserList());
			
			if(date.equals("") || date==null)
				return "redirect:/admin";
			else {	
				todoDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				model.addAttribute("todoList", todoService.findByDate(todoDate));
				return "admin";
			}
			
		}
		
		@RequestMapping(value = "/user/todosave", method = RequestMethod.GET)
		public String todoSavePage(Authentication authentication, Model model) {
			 
			model.addAttribute("userInfo", userService.findByNick(authentication.getName()));
		    model.addAttribute("todoContext", new TodoContext());
		    return "todosave";
		    
		}
		
		@RequestMapping(value = "/user/todosave", method = RequestMethod.POST)
		public String todoSave(TodoContext todoContext,BindingResult bindingResult, Model model) {
			 
			todoService.save(todoContext);
		    return "redirect:/user";
		    
		}
		
		@RequestMapping(value = "/user/tododelete", method = RequestMethod.GET)
		public String todoDelete(HttpServletRequest request, Authentication authentication, RedirectAttributes model) {
			
			int id = -1;  
	
		    if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
		       	id = Integer.parseInt(request.getParameter("id"));
		    }        
		    
			todoService.deleteItem(id);	
			model.addFlashAttribute("userInfo", userService.findByNick(authentication.getName()));
			 
			return "redirect:/user";		    
			
		}
		
		@RequestMapping(value = "/user/todoupdate", method = RequestMethod.GET)
		public String todoUpdatePage(HttpServletRequest request, Authentication authentication, Model model,RedirectAttributes model2) {

			int id = -1;  

	        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
	        	id = Integer.parseInt(request.getParameter("id"));
	        }

		    model.addAttribute("userInfo", userService.findByNick(authentication.getName()));
		    model.addAttribute("todoContext", new TodoContext());
	        model.addAttribute("todo", todoService.findById(id));
				
		    return "todoupdate";
		
		}
		
		@RequestMapping(value = "/user/todoupdate", method = RequestMethod.POST)
		public String todoUpdate(TodoContext todoContext, Authentication authentication, RedirectAttributes model) {
			 
			todoService.update(todoContext);
			model.addFlashAttribute("userInfo", userService.findByNick(authentication.getName()));
		    return "redirect:/user";
		   
		}
		
		@RequestMapping(value = "/admin/usersave", method = RequestMethod.GET)
		public String userSavePage(Authentication authentication,Model model) {
			 
			model.addAttribute("userInfo", userService.findByNick(authentication.getName()));
		    model.addAttribute("userContext", new UserContext());
		    model.addAttribute("roles", roleService.getAllRoleList());
		    return "usersave";
		    
		}
		
		@RequestMapping(value = "/admin/usersave", method = RequestMethod.POST)
		public String userSave(UserContext userContext, Model model) {
			 
			userService.save(userContext);
		    return "redirect:/admin";
		    
		}
		
		@RequestMapping(value = "/admin/userdelete", method = RequestMethod.GET)
		public String userDelete(HttpServletRequest request, Authentication authentication, RedirectAttributes model) {
			
			int id = -1;  
	
		    if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
		       	id = Integer.parseInt(request.getParameter("id"));
		    }        
		    
		    userService.deleteItem(id);	
		    
		    model.addFlashAttribute("bilgi",null);
			model.addFlashAttribute("userInfo", userService.findByNick(authentication.getName()));
		    
			return "redirect:/admin";
			
		}
		
		@RequestMapping(value = "/admin/userupdate", method = RequestMethod.GET)
		public String userUpdatePage(HttpServletRequest request, Authentication authentication, Model model,RedirectAttributes model2) {

			int id = -1;  

	        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
	        	id = Integer.parseInt(request.getParameter("id"));
	        }
	       
	       	model.addAttribute("userInfo", userService.findByNick(authentication.getName()));
	        model.addAttribute("userContext", new UserContext());
	        model.addAttribute("user", userService.findById(id));
		    model.addAttribute("roles", roleService.getAllRoleList());
			
	        return "userupdate";       
		
	    }
		
		@RequestMapping(value = "/admin/userupdate", method = RequestMethod.POST)
		public String userUpdate(UserContext userContext, Authentication authentication, RedirectAttributes model) {
			 
			userService.update(userContext);
			model.addFlashAttribute("userInfo", userService.findByNick(authentication.getName()));
		    return "redirect:/admin";
		   
		}
		
}