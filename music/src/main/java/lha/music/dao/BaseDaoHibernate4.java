package lha.music.dao; 

import java.io.Serializable;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseDaoHibernate4<T> implements BaseDao<T> {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	public SessionFactory getSessionFactory(){
		return this.sessionFactory;
	}
	
	public BaseDaoHibernate4(){
		
	}
	@SuppressWarnings("unchecked")
	public T get(Class<T> entityClazz, Serializable id) {
		// TODO Auto-generated method stub
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		T tt=(T) session.get(entityClazz, id);
		tran.commit();
		return tt;
	}

	public Serializable save(T entity) {
		// TODO Auto-generated method stub
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		Serializable s=session.save(entity);
		tran.commit();
		return s;
	}

	public void update(T entity) {
		// TODO Auto-generated method stub
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		session.update(entity);
		tran.commit();
	}

	public void delete(T entity) {
		// TODO Auto-generated method stub
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		session.delete(entity);
		tran.commit();
	}

	public void delete(Class<T> entityClazz, Serializable id) {
		// TODO Auto-generated method stub
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		session.createQuery("delete "+
		entityClazz.getSimpleName()+" en where en.id=?0").setParameter("0", id).executeUpdate();
		tran.commit();
	}

	public List<T> findAll(Class<T> entityClazz) {
		// TODO Auto-generated method stub
		return find("select en from "+entityClazz.getSimpleName()+" en");
	}

	public long findCount(Class<T> entityClazz) {
		// TODO Auto-generated method stub
		List<?> l=find("select count(*) from "+entityClazz.getSimpleName());
		if(l!=null&&l.size()==1){
			return (Long)l.get(0);
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	protected List<T> find(String hql){
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		List<T> list=(List<T>)session.createQuery(hql).list();
		tran.commit();
		return list;
	}

	@SuppressWarnings("unchecked")
	protected List<T> find(String hql,Object... params){
		Session session=getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		Query query=session.createQuery(hql);
		for(int i=0, len=params.length;i<len;i++){
			query.setParameter(i+"", params[i]);
		}
		List<T> list=(List<T>)query.list();
		tran.commit();
		return list;
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql,int pageNo,int pageSize){
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		List<T> list=session.createQuery(hql)
				.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
		tran.commit();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql,int pageNo,int pageSize,Object...params){
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		Query query=session.createQuery(hql);
		for(int i=0,len=params.length;i<len;i++){
			query.setParameter(i+"", params[i]);
		}
		List<T> list=query.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
		tran.commit();
		return list;
	}
}
