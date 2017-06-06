package lha.music.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import lha.music.service.AdminLoginService;


@Component
@Scope("prototype")
public class AdminLoginAction extends ActionSupport{

	private Map<String,String> resultMap=new HashMap<String,String>();
	
	public Map<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}

	public String execute() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		String adminName=request.getParameter("adminName");
		String adminPwd=request.getParameter("adminPwd");
		AdminLoginService als=new AdminLoginService();
		if(als.login(adminName, adminPwd)){
			request.getSession().setAttribute("adminName", adminName);
			System.out.println("添加adminName");
			resultMap.put("result", "success");
			return SUCCESS;
		}
		resultMap.put("result", "failure");
		return SUCCESS;
	}
}
