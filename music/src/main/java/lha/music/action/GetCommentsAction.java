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

import lha.music.dao.CommentDao;
import lha.music.dao.FollowDao;
import lha.music.dao.UserDao;
import lha.music.vo.Comment;

@Component
@Scope("prototype")
public class GetCommentsAction extends ActionSupport{

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
		Integer songId=Integer.parseInt(request.getParameter("songId"));
		String username=(String) session.getAttribute("username");
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		UserDao userDao=(UserDao) context.getBean("userDao");
		FollowDao followDao=(FollowDao) context.getBean("followDao");
		CommentDao commentDao=(CommentDao) context.getBean("commentDao");
		List<Comment> _comments=commentDao.findBySong(songId);
		List<Object> comments=new ArrayList<Object>();
		for(int i=0;i<_comments.size();i++){
			Comment _comment=_comments.get(i);
			String nickname=_comment.getUser().getNickname();
			String _username=_comment.getUser().getUsername();
			String commentTime=formatDate(_comment.getCommentTime());
			String commentContent=_comment.getCommentContent();
			Map<Object,Object> comment=new HashMap<Object,Object>();
			comment.put("username", _username);
			comment.put("nickname", nickname);
			comment.put("commentTime", commentTime);
			comment.put("commentContent", commentContent);
			if(username==null||_username.equals(username)||followDao.fllowed(username,_username)!=null){
				comment.put("followState","true");
			}else{
				comment.put("followState","false");
			}
			comments.add(comment);
		}
		resultMap.put("comments", comments);
		return SUCCESS;
	}
}
