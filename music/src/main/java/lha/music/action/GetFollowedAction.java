package lha.music.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import lha.music.dao.FollowDao;
import lha.music.dao.UserDao;
import lha.music.vo.User;

@Component
@Scope("prototype")
public class GetFollowedAction extends ActionSupport{

	private Map<Object,Object> resultMap=new HashMap<Object,Object>();

	public Map<Object, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<Object, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
    public  String formatDate(Date date)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
   }
    
	public String execute() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");
		if(username==null){
			resultMap.put("result", "unlogin");
			return SUCCESS;
		}
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		UserDao userDao=(UserDao) context.getBean("userDao");
		FollowDao followDao=(FollowDao) context.getBean("followDao");
		List<User> _followedUsers=followDao.findFollwed(username);//获取我关注的人
		List<Object> followedUsers=new ArrayList<Object>();
		for(int i=0;i<_followedUsers.size();i++){
			Map user=new HashMap<Object,Object>();
			User _user=_followedUsers.get(i);
			user.put("username",_user.getUsername());
			user.put("nickname", _user.getNickname());
			user.put("followedState", "true");
			followedUsers.add(user);
		}
		resultMap.put("followedUsers", followedUsers);
		return SUCCESS;
	}
}
