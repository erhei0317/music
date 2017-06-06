package lha.music.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lha.music.vo.Song;
@Component
@Scope("singleton")
public class SongDao extends BaseDaoHibernate4<Song>{

	public List<Song> getSongsOrdered(){
		String hql="from Song song order by song.popular desc";
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		List<Song> songs=session.createQuery(hql).list();
		return songs;
	}
	
	public Song findSongById(String songId){
		String hql=String.format("from Song song where song.songId=%s",songId);
		System.out.println(hql);
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		List<Song> songs=session.createQuery(hql).list();
		tran.commit();
		if(songs.size()==0)
			return null;
		else
			return songs.get(0);
	}
}
