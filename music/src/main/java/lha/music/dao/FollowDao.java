package lha.music.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lha.music.vo.Follow;
import lha.music.vo.SongCollection;
import lha.music.vo.User;


public class FollowDao extends BaseDaoHibernate4<Follow>{

	public Follow fllowed(String follwUser,String follwedUSer){
		String hql=String.format("from Follow follow where follow.followUser.username='%s' and follow.followedUser.username='%s'", follwUser,follwedUSer);
		System.out.println(hql);
		Session session=this.getSessionFactory().openSession();
		Transaction tran=session.beginTransaction();
		List<Follow> follows=session.createQuery(hql).list();
		tran.commit();
		session.close();
		if(follows.size()==0)
			return null;
		else{
			return follows.get(0);
		}		
	}
	
	public List<User> findFollwed(String follwUser){
		String hql=String.format("select follow.followedUser from Follow follow where follow.followUser.username='%s'", follwUser);
		System.out.println(hql);
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		List<User> followeds=session.createQuery(hql).list();
		tran.commit();
		return followeds;
	}
	
	public List<User> findFollw(String follwedUser){
		String hql=String.format("select follow.followUser from Follow follow where follow.followedUser.username='%s'", follwedUser);
		System.out.println(hql);
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		List<User> followeds=session.createQuery(hql).list();
		tran.commit();
		return followeds;
	}
}
