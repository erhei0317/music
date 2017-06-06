package lha.music.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import lha.music.dao.UserDao;
import lha.music.vo.User;
@Component("loginService")
public class LoginService {

	public boolean login(String userName,String password){
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		UserDao userDao=(UserDao) context.getBean("userDao");
		User user=userDao.findUserById(userName);
		if(user==null)
			return false;
		if(password.equals(user.getPassword()))
			return true;
		return false;
	}
	
	
}
