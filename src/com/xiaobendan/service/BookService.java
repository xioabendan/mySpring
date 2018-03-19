package com.xiaobendan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaobendan.dao.BookDao;

@Service
public class BookService {
	@Autowired
	private BookDao dao;
	
	/**
	 * 结账
	 * 
	 * @Transactional这是一个注解告诉Spring这是一个事物方法
	 * （方法内部只要有一个sql报错整体回滚）
	 * 底层实现方式就是AOP原理
	 * 可以更加细致的进行事物控制，使用的是Transactional内部参数
	 * 		isolation：事物隔离级别
	 * 		propagation：事物的传播属性
	 * 		readOnly:告诉这个事物在数据库是否只读操作，默认false。如果为true则会进行一些优化提高速度
	 * 		timeout:指定方法的超时属性，如果方法超出指定时间，方法停止自动回滚
	 * 			timeout奇怪的地方就是你把sql操作结束之后的超时不会报错也不会回滚
	 * 默认只要事物方法中出现任何异常都会回滚，可以控制什么异常是否可回滚
	 * 默认只是运行时异常才进行回滚
	 * 		检查异常：只要不是运行时异常都是检查异常
	 * 		非检查异常：运行时异常
	 * 		noRollbackFor={xx.class,...}可以指定哪些异常不回滚
	 * 		指定哪些异常回滚rollbackFor=
	 * 
	 */
	@Transactional(timeout=3,propagation=Propagation.REQUIRES_NEW)
	public void checkOut(String isbn,String userName){
		//借书，更新库存
		dao.updateStoke(isbn);
		//查询书本价格
		Integer price = dao.queryPrice(isbn);
		int i =10/0;
		//扣款
		dao.updateBalance(userName, price);
		System.out.println("结账成功！！！");
	}
	/**
	 * 修改图书价格
	 * @param isbn
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updatePrice(String isbn,Integer price){
		dao.updatePrice(isbn, price);
		System.out.println("价格更新成功！");
	}
}
