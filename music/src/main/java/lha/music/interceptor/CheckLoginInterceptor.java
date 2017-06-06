package lha.music.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


@Component
@Scope("prototype")
public class CheckLoginInterceptor extends AbstractInterceptor{

	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("拦截");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String adminName=(String) session.getAttribute("adminName");
		if(adminName!=null){
			System.out.println("adminName不为空");
			return arg0.invoke();
		}else{
			System.out.println("adminName为空");
			return "unlogin";
		}
			
		
	}

}
