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
import lha.music.service.RegisterService;
import lha.music.vo.User;


@Component
@Scope("prototype")
public class Register2Action extends ActionSupport{

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
		String nickname=request.getParameter("nickname");
		RegisterService rs=(RegisterService) context.getBean("registerService");
		if(rs.existUser(username)){
			resultMap.put("result", "该用户已经存在");
			return SUCCESS;
		}
		User user=new User();
		user.setNickname(nickname);
		user.setPassword(password);
		user.setUsername(username);
		if(rs.register(user)){
			resultMap.put("result", "success");
			return SUCCESS;
		}else{
			resultMap.put("result", "注册失败");
			return SUCCESS;
		}
	}

}

