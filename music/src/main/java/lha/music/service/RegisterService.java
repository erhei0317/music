package lha.music.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import lha.music.dao.UserDao;
import lha.music.vo.User;

@Component
public class RegisterService {

	public boolean register(User user){
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		UserDao userDao=(UserDao) context.getBean("userDao");
		User _user=userDao.findUserById(user.getUsername());
		if(_user!=null)
			return false;
		userDao.save(user);
		return true;
	}
	
	public boolean existUser(String username){
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		UserDao userDao=(UserDao) context.getBean("userDao");
		User _user=userDao.findUserById(username);
		if(_user!=null)
			return true;
		return false;
	}
}
