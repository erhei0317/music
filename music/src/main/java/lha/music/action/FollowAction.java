package lha.music.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import lha.music.dao.FollowDao;
import lha.music.dao.SongCollectionDao;
import lha.music.dao.UserDao;
import lha.music.vo.Follow;
import lha.music.vo.User;
import lha.music.websocket.MusicWebSocket;
import net.sf.json.JSONObject;

@Component
@Scope("prototype")
public class FollowAction extends ActionSupport{

	private Map<Object,Object> resultMap=new HashMap<Object,Object>();

	public Map<Object, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<Object, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public String execute() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String songId=request.getParameter("songId");
		String username=(String) session.getAttribute("username");
		if(username==null){
			resultMap.put("result", "unlogin");
			return SUCCESS;
		}
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		UserDao userDao=(UserDao) context.getBean("userDao");
		FollowDao followDao=(FollowDao) context.getBean("followDao");
		String followedUsername=request.getParameter("followedUsername");
		Follow _follow=followDao.fllowed(username, followedUsername);
		if(_follow!=null){
			followDao.delete(_follow);
			resultMap.put("result", "unfollow");
			resultMap.put("followedUsername", followedUsername);
			return SUCCESS;
		}
		Follow follow=new Follow();
		User followUser=userDao.findUserById(username);
		User followedUser=userDao.findUserById(followedUsername);
		follow.setFollowedUser(followedUser);
		follow.setFollowUser(followUser);
		if(followUser!=null&&followedUser!=null)
			followDao.save(follow);
		resultMap.put("result", "follow");
		resultMap.put("followedUsername", followedUsername);
		Session wsksession=MusicWebSocket.getSessions().get(followedUsername);
		if(wsksession!=null){
			Map<Object,Object> wskMap=new HashMap<Object,Object>();
			wskMap.put("wskInf", "有人关注了你");
			wskMap.put("wskContent", "'"+followUser.getNickname()+"'关注了你");
			JSONObject jsonObject=JSONObject.fromObject(wskMap);
			wsksession.getBasicRemote().sendText(jsonObject.toString());
		}
		
		return SUCCESS;
	}
}
