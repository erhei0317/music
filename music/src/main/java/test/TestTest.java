package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class TestTest {


	
	@Test
	public void testMain2() {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
	}
}
