package lha.music.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lha.music.vo.User;

@Component
@Scope("singleton")
public class UserDao extends BaseDaoHibernate4<User>{

	public User findUserById(String userName){
		String hql=String.format("from User user where user.username='%s'",userName);
		System.out.println(hql);
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		List<User> users=session.createQuery(hql).list();
		tran.commit();
		if(users.size()==0)
			return null;
		else
			return users.get(0);
	}
}
