package test;

import static org.junit.Assert.*;

import org.junit.Test;

import lha.music.dao.SongDao;

public class SongDaoTest {

	@Test
	public void testGetSongsOrdered() {
		AA aa=new AA();
		change(aa);
		System.out.println(aa.a);
	}
	
	public class AA{
		public int a;
	};
	
	public void change(AA a){
		a.a=5;
	}
}
