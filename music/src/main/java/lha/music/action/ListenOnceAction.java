package lha.music.action;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import lha.music.dao.ListenHistoryDao;
import lha.music.dao.SongDao;
import lha.music.dao.UserDao;
import lha.music.vo.ListenHistory;
import lha.music.vo.Song;
import lha.music.vo.User;

@Component
@Scope("prototype")
public class ListenOnceAction extends ActionSupport{


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
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		System.out.println("名称"+Arrays.toString(context.getBeanDefinitionNames()));
		SongDao songDao=(SongDao) context.getBean("songDao");
		Song song=songDao.findSongById(songId);
		if(username!=null){
			UserDao userDao=(UserDao) context.getBean("userDao");
			ListenHistoryDao listenHistoryDao=(ListenHistoryDao) context.getBean("listenHistoryDao");
			ListenHistory _listenHistory=listenHistoryDao.getHistory(Integer.parseInt(songId), username);
			if(_listenHistory==null){
				User user=userDao.findUserById(username);
				ListenHistory listenHistory=new ListenHistory();
				listenHistory.setUser(user);
				listenHistory.setSong(song);
				listenHistory.setListenTime(1);
				listenHistoryDao.save(listenHistory);
			}else{
				_listenHistory.setListenTime(_listenHistory.getListenTime()+1);
				listenHistoryDao.update(_listenHistory);
			}
			
		}
		
		song.setPopular(song.getPopular()+1);
		songDao.update(song);
		resultMap.put("result", "success");
		return SUCCESS;
	}
}
