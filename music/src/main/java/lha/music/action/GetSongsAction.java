package lha.music.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import lha.music.dao.SongCollectionDao;
import lha.music.dao.SongDao;
import lha.music.vo.Song;

@Component
@Scope("prototype")
public class GetSongsAction extends ActionSupport{

	private Map<Object,Object> resultMap=new HashMap<Object,Object>();

	public Map<Object, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<Object, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public String execute() throws Exception{
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		String username=(String) ServletActionContext.getRequest().getSession().getAttribute("username");
		SongDao songDao=(SongDao) context.getBean("songDao");
		SongCollectionDao songCollectionDao=(SongCollectionDao) context.getBean("songCollectionDao"); 
		List<Object> songs=new ArrayList<Object>();
		List<Song> songList=songDao.getSongsOrdered();
		for(int i=0;i<songList.size();i++){
			Song _song=songList.get(i);
			Map<Object,Object> song=new HashMap<Object,Object>();
			song.put("songid", _song.getSongId());
			song.put("songname", _song.getSongName());
			song.put("singer", _song.getSinger());
			song.put("album", _song.getAlbum());
			song.put("popular", _song.getPopular());
			song.put("songurl", _song.getSongUrl());
			if(username==null){
				song.put("collected", "no");
			}else{
				if(songCollectionDao.findCollected(_song.getSongId(), username)!=null){
					song.put("collected", "yes");
				}else{
					song.put("collected", "no");
				}
			}
			songs.add(song);
		}
		resultMap.put("songs", songs);
		return SUCCESS;
	}
}
