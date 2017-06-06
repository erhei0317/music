package lha.music.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
public class Login2Action extends ActionSupport{


	private Map<Object,Object> resultMap=new HashMap<Object,Object>();

	public Map<Object, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<Object, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public String execute() throws Exception{
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		LoginService ls=(LoginService) context.getBean("loginService");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=ServletActionContext.getRequest().getSession();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		if(ls.login(username, password)){
			session.setAttribute("username", username);
			resultMap.put("result", "success");
			return SUCCESS;
		}
		else{
			resultMap.put("result", "用户名或密码错误");
			return SUCCESS;
		}		
	}

}
