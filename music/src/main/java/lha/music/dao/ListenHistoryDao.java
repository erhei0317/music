package lha.music.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lha.music.vo.ListenHistory;

@Component
@Scope("singleton")
public class ListenHistoryDao extends BaseDaoHibernate4<ListenHistory>{

	public ListenHistory getHistory(Integer songId,String username){
		String hql=String.format("from ListenHistory listenHistory where listenHistory.user.username='%s' and listenHistory.song.songId=%s",username,songId);
		System.out.println(hql);
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		List<ListenHistory> listenHistory=session.createQuery(hql).list();
		tran.commit();
		if(listenHistory.size()==0){
			return null;
		}else{
			return listenHistory.get(0);
		}
	}
	
	public List<ListenHistory> findHistoryByUser(String username){
		String hql=String.format("from ListenHistory listenHistory where listenHistory.user.username='%s' ",username);
		System.out.println(hql);
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		List<ListenHistory> listenHistories=session.createQuery(hql).list();
		tran.commit();
		return listenHistories;
	}
}
