package lha.music.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lha.music.vo.Comment;
import lha.music.vo.SongCollection;

@Component
@Scope("singleton")
public class CommentDao extends BaseDaoHibernate4<Comment>{

	public List<Comment> findBySong(Integer songId){
		String hql=String.format("from Comment comment where comment.song.songId=%s", songId);
		System.out.println(hql);
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		List<Comment> comments=session.createQuery(hql).list();
		tran.commit();
		return comments;
	}
	
	public List<Comment> findByUser(String username){
		String hql=String.format("from Comment comment where comment.user.username='%s'", username);
		System.out.println(hql);
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		List<Comment> comments=session.createQuery(hql).list();
		tran.commit();
		return comments;
	}
	
	public void deleteById(int commentId){
		String hql=String.format("delete from Comment where commentId=%s", commentId);
		System.out.println(hql);
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		session.createQuery(hql).executeUpdate();
		tran.commit();

	}
}
