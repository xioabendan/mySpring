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
	 * ����
	 * 
	 * @Transactional����һ��ע�����Spring����һ�����﷽��
	 * �������ڲ�ֻҪ��һ��sql��������ع���
	 * �ײ�ʵ�ַ�ʽ����AOPԭ��
	 * ���Ը���ϸ�µĽ���������ƣ�ʹ�õ���Transactional�ڲ�����
	 * 		isolation��������뼶��
	 * 		propagation������Ĵ�������
	 * 		readOnly:����������������ݿ��Ƿ�ֻ��������Ĭ��false�����Ϊtrue������һЩ�Ż�����ٶ�
	 * 		timeout:ָ�������ĳ�ʱ���ԣ������������ָ��ʱ�䣬����ֹͣ�Զ��ع�
	 * 			timeout��ֵĵط��������sql��������֮��ĳ�ʱ���ᱨ��Ҳ����ع�
	 * Ĭ��ֻҪ���﷽���г����κ��쳣����ع������Կ���ʲô�쳣�Ƿ�ɻع�
	 * Ĭ��ֻ������ʱ�쳣�Ž��лع�
	 * 		����쳣��ֻҪ��������ʱ�쳣���Ǽ���쳣
	 * 		�Ǽ���쳣������ʱ�쳣
	 * 		noRollbackFor={xx.class,...}����ָ����Щ�쳣���ع�
	 * 		ָ����Щ�쳣�ع�rollbackFor=
	 * 
	 */
	@Transactional(timeout=3,propagation=Propagation.REQUIRES_NEW)
	public void checkOut(String isbn,String userName){
		//���飬���¿��
		dao.updateStoke(isbn);
		//��ѯ�鱾�۸�
		Integer price = dao.queryPrice(isbn);
		int i =10/0;
		//�ۿ�
		dao.updateBalance(userName, price);
		System.out.println("���˳ɹ�������");
	}
	/**
	 * �޸�ͼ��۸�
	 * @param isbn
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updatePrice(String isbn,Integer price){
		dao.updatePrice(isbn, price);
		System.out.println("�۸���³ɹ���");
	}
}
