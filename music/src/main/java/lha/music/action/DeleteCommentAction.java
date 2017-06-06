package lha.music.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import lha.music.dao.CommentDao;
@Component
@Scope("prototype")
public class DeleteCommentAction extends ActionSupport{
	private Map<Object,Object> resultMap=new HashMap<Object,Object>();

	public Map<Object, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<Object, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public String execute() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		int commentId=Integer.parseInt(request.getParameter("commentId"));
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		CommentDao commentDao=(CommentDao) context.getBean("commentDao");
		commentDao.deleteById(commentId);
		resultMap.put("commentId", commentId);
		return SUCCESS;
	}
}
