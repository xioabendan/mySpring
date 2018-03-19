package com.xiaobendan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *@Description
 * 
 *@author zhanghongwei
 *@createDate 2018年3月16日 下午4:16:04
 *@version 1.0.0
 */
@Service
public class OtherService {
	@Autowired
	private BookService bookService;
	
	/**
	 * 当一个事务在别的事务方法中运行的时候，我们可以控制哪些事务进行回滚
	 * 通过propagation进行控制。在引入方法的事物中进行设置，非当前事物
	 */
	@Transactional()
	public void multx(){
		//1、REQUIRED运行在当前的事务内
		//2.REQUIRES_NEW,必须开启一个新的事物，如果在别的事物内运行则将当前事物挂起
		bookService.checkOut("ISBN-002", "Tom");
		//1、REQUIRED运行在当前的事务内
		//2.REQUIRES_NEW,必须开启一个新的事物，如果在别的事物内运行则将当前事物挂起		
		bookService.updatePrice("ISBN-002", 1001);
	}
}
