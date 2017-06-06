package lha.music.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import lha.music.service.RegisterService;
import lha.music.vo.User;

@Component
@Scope("prototype")
public class RegisterAction extends ActionSupport{

	private String username;
	private String password1;
	private String password2;
	private String nickname;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String execute() throws Exception{
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password1);
		user.setNickname(nickname);
		RegisterService rs=(RegisterService) context.getBean("registerService");
		if(rs.register(user))
			return SUCCESS;
		return ERROR;
	}
	
	public void validate(){
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		RegisterService rs=(RegisterService) context.getBean("registerService");
		if(rs.existUser(username)){
			addFieldError("userexist","该用户已存在");
			return;
		}

		if(password1!=null&&!password1.equals(password2)){
			addFieldError("upwd","两次密码不一致");
		}
	}
}
