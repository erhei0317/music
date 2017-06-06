package lha.music.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import lha.music.dao.CommentDao;
import lha.music.dao.SongDao;
import lha.music.dao.UserDao;
import lha.music.vo.Comment;
import lha.music.vo.Song;
import lha.music.vo.User;

@Component
@Scope("prototype")
public class PostCommentAction extends ActionSupport{

	private Map<Object,Object> resultMap=new HashMap<Object,Object>();

	public Map<Object, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<Object, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public Date parseTime(String dateStr){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date time=null;
		try {
			time=df.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
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
		CommentDao commentDao=(CommentDao) context.getBean("commentDao");
		SongDao songDao=(SongDao) context.getBean("songDao");
		String songId=request.getParameter("songId");
		String _commentTime=request.getParameter("commentTime");
		String commentContent=request.getParameter("commentContent");
		Comment comment=new Comment();
		Song song=songDao.findSongById(songId);
		User user=userDao.findUserById(username);
		comment.setSong(song);
		comment.setUser(user);
		comment.setCommentTime(parseTime(_commentTime));
		comment.setCommentContent(commentContent);
		commentDao.save(comment);
		Map<Object,Object> _comment=new HashMap<Object,Object>();
		_comment.put("username", username);
		_comment.put("nickname", user.getNickname());
		_comment.put("commentTime", _commentTime);
		_comment.put("commentContent", commentContent);
		_comment.put("commentContent", commentContent);
		_comment.put("followState", "true");
		resultMap.put("comment", _comment);
		return SUCCESS;
	}
}
