package lha.music.action;

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

import lha.music.dao.SongCollectionDao;
import lha.music.dao.SongDao;
import lha.music.dao.UserDao;
import lha.music.vo.Song;
import lha.music.vo.SongCollection;
import lha.music.vo.User;


@Component
@Scope("prototype")
public class CollectionSongAction extends ActionSupport{

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
		SongDao songDao=(SongDao) context.getBean("songDao");
		Song song=songDao.findSongById(songId);
		UserDao userDao=(UserDao) context.getBean("userDao");
		SongCollectionDao songCollectionDao=(SongCollectionDao) context.getBean("songCollectionDao");
		User user=userDao.findUserById(username);
		SongCollection _songCollection=songCollectionDao.findCollected(Integer.parseInt(songId), username);
		if(_songCollection==null){
			SongCollection songCollection=new SongCollection();
			songCollection.setSong(song);
			songCollection.setUser(user);
			songCollectionDao.save(songCollection);
			resultMap.put("result", "已收藏");
		}else{
			songCollectionDao.delete(_songCollection);
			resultMap.put("result", "取消收藏");
		}
		
		return SUCCESS;
	}
}
