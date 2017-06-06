package lha.music.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import lha.music.vo.Admin;

public class AdminDao extends  BaseDaoHibernate4<Admin>{

	public Admin findAdminByName(String adminName){
		String hql=String.format("from Admin admin where admin.adminName='%s'",adminName);
		System.out.println(hql);
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		List<Admin> list=session.createQuery(hql).list();
		tran.commit();
		if(list.size()<=0)
			return null;
		else return list.get(0);
	}
}
