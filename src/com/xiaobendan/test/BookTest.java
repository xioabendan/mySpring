package com.xiaobendan.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiaobendan.dao.BookDao;
import com.xiaobendan.service.BookService;
import com.xiaobendan.service.OtherService;

public class BookTest {
	ApplicationContext ioc = new ClassPathXmlApplicationContext("config/applicationContext.xml");
	@Test
	public void test01() {
		BookDao dao = ioc.getBean(BookDao.class);
		System.out.println(dao.queryPrice("ISBN-001"));
	}
	
	@Test
	public void test02(){
		BookService service = ioc.getBean(BookService.class);
		//IOC�����б�������������Ĵ������
		service.checkOut("ISBN-001", "Tom");
		System.out.println(service.getClass());//���ص�Ӧ����һ���������
		//class com.xiaobendan.service.BookService$$EnhancerBySpringCGLIB$$aa5d8677
	}
	@Test
	public void test03(){
		OtherService service = ioc.getBean(OtherService.class);
		service.multx();
	}
}
