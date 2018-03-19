package com.xiaobendan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *@Description
 * 
 *@author zhanghongwei
 *@createDate 2018��3��16�� ����4:16:04
 *@version 1.0.0
 */
@Service
public class OtherService {
	@Autowired
	private BookService bookService;
	
	/**
	 * ��һ�������ڱ�����񷽷������е�ʱ�����ǿ��Կ�����Щ������лع�
	 * ͨ��propagation���п��ơ������뷽���������н������ã��ǵ�ǰ����
	 */
	@Transactional()
	public void multx(){
		//1��REQUIRED�����ڵ�ǰ��������
		//2.REQUIRES_NEW,���뿪��һ���µ��������ڱ�������������򽫵�ǰ�������
		bookService.checkOut("ISBN-002", "Tom");
		//1��REQUIRED�����ڵ�ǰ��������
		//2.REQUIRES_NEW,���뿪��һ���µ��������ڱ�������������򽫵�ǰ�������		
		bookService.updatePrice("ISBN-002", 1001);
	}
}
