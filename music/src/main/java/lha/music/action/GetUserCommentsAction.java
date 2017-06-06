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
import lha.music.vo.Comment;
import lha.music.vo.Song;

@Component
@Scope("prototype")
public class GetUserCommentsAction extends ActionSupport{

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
    
    public String getCommentInf(String commentContent){
    	if(commentContent.length()<=7){
    		return commentContent;
    	}else{
    		String commentInf=commentContent.substring(0,7)+"....";
    		return commentInf;
    	}
    }
    
    public String getSongnameInf(String songname){
    	if(songname.length()<=7){
    		return songname;
    	}else{
    		String songnameInf=songname.substring(0,7)+"....";
    		return songnameInf;
    	}
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
		CommentDao commentDao=(CommentDao) context.getBean("commentDao");
		List<Comment> _comments=commentDao.findByUser(username);
		List<Object> userComments=new ArrayList<Object>();
		for(int i=0;i<_comments.size();i++){
			Comment _comment=_comments.get(i);
			Date _commentTime=_comment.getCommentTime();
			Song commentSong=_comment.getSong();
			String commentContent=_comment.getCommentContent();
			int commentId=_comment.getCommentId();
			String commentTime=formatDate(_commentTime);
			String songname=commentSong.getSongName();
			Map<Object,Object> userComment=new HashMap<Object,Object>();
			userComment.put("commentId", commentId);
			userComment.put("commentTime", commentTime);
			userComment.put("commentContent", commentContent);
			userComment.put("songname", songname);
			userComment.put("commentInf", getCommentInf(commentContent));
			userComment.put("songnameInf", getSongnameInf(songname));
			userComments.add(userComment);
		}
		resultMap.put("userComments", userComments);
		return SUCCESS;
	}
}
