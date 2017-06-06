package lha.music.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import lha.music.dao.SongDao;
import lha.music.vo.Song;
@Component
@Scope("prototype")
public class AddSongAction extends ActionSupport{

	private Map<Object,Object> resultMap=new HashMap<Object,Object>();

	public Map<Object, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<Object, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public String execute() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		String songname=request.getParameter("songname");
		String singer=request.getParameter("singer");
		String album=request.getParameter("album");
		String songUrl=request.getParameter("songurl");
		Song song=new Song();
		song.setSongName(songname);
		song.setSinger(singer);
		song.setAlbum(album);
		song.setPopular(0);
		song.setSongUrl(songUrl);
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		SongDao songDao=(SongDao) context.getBean("songDao");
		songDao.save(song);
		resultMap.put("result", "success");
		return SUCCESS;
	}
}
