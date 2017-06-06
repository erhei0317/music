package lha.music.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import lha.music.dao.UserDao;

@Component
@Scope("prototype")
public class LoginStateAction extends ActionSupport{

	private Map<Object,Object> resultMap=new HashMap<Object,Object>();

	public Map<Object, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<Object, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public String execute() throws Exception{
		String username=(String) ServletActionContext.getRequest().getSession().getAttribute("username");
		if(username==null){
			resultMap.put("login", "false");
		}else{
			resultMap.put("login", "true");
			ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
			UserDao userDao=(UserDao) context.getBean("userDao");
			String nickname=userDao.findUserById(username).getNickname();
			resultMap.put("nickname", nickname);
		}
		return SUCCESS;
	}
}
