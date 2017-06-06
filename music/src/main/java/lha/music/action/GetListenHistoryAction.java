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

import lha.music.dao.ListenHistoryDao;
import lha.music.vo.ListenHistory;
import lha.music.vo.Song;

@Component
@Scope("prototype")
public class GetListenHistoryAction extends ActionSupport{

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
		ListenHistoryDao listenHistoryDao=(ListenHistoryDao) context.getBean("listenHistoryDao");
		List<ListenHistory> _listenHistories=listenHistoryDao.findHistoryByUser(username);
		List<Object> listenHistories=new ArrayList<Object>();
		for(int i=0;i<_listenHistories.size();i++){
			Map<Object,Object> listenHisory=new HashMap<Object,Object>();
			Song song=_listenHistories.get(i).getSong();
			listenHisory.put("songid", song.getSongId());
			listenHisory.put("songurl", song.getSongUrl());
			listenHisory.put("songname", song.getSongName());
			listenHisory.put("singer", song.getSinger());
			listenHisory.put("album", song.getAlbum());
			listenHisory.put("time", _listenHistories.get(i).getListenTime());
			listenHistories.add(listenHisory);
		}
		resultMap.put("histories", listenHistories);
		return SUCCESS;
	}
}
