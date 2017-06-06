package lha.music.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import lha.music.vo.SongCollection;

public class SongCollectionDaoTest {

	@Test
	public void test() {
		SongCollection song=new SongCollectionDao().findCollected(1, "15325865182");
		if(song==null){
			System.out.println("null");
		}else{
			System.out.println(""+song.getCollectionId());
		}
	}

}
