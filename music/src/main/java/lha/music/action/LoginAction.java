package lha.music.action;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import lha.music.service.LoginService;

@Component
@Scope("prototype")
public class LoginAction extends ActionSupport{

	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String execute() throws Exception{
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		LoginService ls=(LoginService) context.getBean("loginService");
		if(ls.login(username, password)){
			HttpSession session=ServletActionContext.getRequest().getSession();
			session.setAttribute("username", username);
			return SUCCESS;
		}
		else{
			addFieldError("usererror","用户名或密码错误！");
			return ERROR;
		}		
	}
	
}
