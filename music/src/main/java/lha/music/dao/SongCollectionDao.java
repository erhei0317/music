package lha.music.dao;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lha.music.vo.Song;
import lha.music.vo.SongCollection;
@Component
@Scope("singleton")
public class SongCollectionDao extends BaseDaoHibernate4<SongCollection>{

	public SongCollection findCollected(Integer songId,String username){
		String hql=String.format("from SongCollection songCollection where songCollection.song.songId=%s and songCollection.user.username='%s'", songId,username);
		System.out.println(hql);
		Session session=this.getSessionFactory().openSession();
		Transaction tran=session.beginTransaction();
		List<SongCollection> songCollections=session.createQuery(hql).list();
		tran.commit();
		session.close();
		if(songCollections.size()==0)
			return null;
		else{
			return songCollections.get(0);
		}		
	}
	
	public List<Song> findSongsByUser(String username){
		String hql=String.format("from SongCollection songCollection where songCollection.user.username='%s'",username);
		System.out.println(hql);
		Session session=this.getSessionFactory().getCurrentSession();
		Transaction tran=session.beginTransaction();
		List<SongCollection> songCollections=session.createQuery(hql).list();
		tran.commit();
		List<Song> songs=new ArrayList<Song>();
		for(int i=0;i<songCollections.size();i++){
			songs.add(songCollections.get(i).getSong());
		}
		return songs;
	}
}
