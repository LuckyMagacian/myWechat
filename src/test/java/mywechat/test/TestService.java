package mywechat.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestService {
	private ApplicationContext ac;
	@Before
	public void init(){
		ac=new ClassPathXmlApplicationContext("xml/spring-mvc.xml");
	}
	@Test
	public void test(){
		System.out.println(ac);
	}
}
