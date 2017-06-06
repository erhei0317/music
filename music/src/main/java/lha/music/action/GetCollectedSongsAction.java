package lha.music.action;

import java.util.ArrayList;
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

import lha.music.dao.SongCollectionDao;
import lha.music.vo.Song;

@Component
@Scope("prototype")
public class GetCollectedSongsAction extends ActionSupport{

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
		String username=(String) session.getAttribute("username");
		if(username==null){
			resultMap.put("result", "unlogin");
			return SUCCESS;
		}
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		SongCollectionDao songCollectionDao=(SongCollectionDao) context.getBean("songCollectionDao");
		List<Song> _songs=songCollectionDao.findSongsByUser(username);
		List<Object> songs=new ArrayList<Object>();
		for(int i=0;i<_songs.size();i++){
			Song _song=_songs.get(i);
			Map<Object,Object> song=new HashMap<Object,Object>();
			song.put("songid", _song.getSongId());
			song.put("songname", _song.getSongName());
			song.put("singer", _song.getSinger());
			song.put("album", _song.getAlbum());
			song.put("songurl", _song.getSongUrl());
			song.put("collected", "yes");
			songs.add(song);
			}
		resultMap.put("songs", songs);
		return SUCCESS;
	}
}
