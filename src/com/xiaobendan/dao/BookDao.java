package com.xiaobendan.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * 1.����ͼ����Ų��Ҽ۸�
	 */
	public Integer queryPrice(String isbn){
		String sql = "select price from book where isbn=?";
		Integer price = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
		return price;
	}
	/**
	 * 2.�޸Ŀ��ÿ�ν���֮�����
	 */
	public void updateStoke(String isbn){
		String sql = "update book_stock set stock=stock-1 where isbn=?";
		jdbcTemplate.update(sql, isbn);
	}
	/**
	 * 3.�����˻����
	 * @param user
	 * @param price
	 */
	public void updateBalance(String user,Integer price){
		String sql="update account set balance=balance-? where username=?";
		jdbcTemplate.update(sql, price,user);
	}
	
	public void updatePrice(String isbn,Integer price){
		String sql ="update book set price=? where isbn=?";
		jdbcTemplate.update(sql, price,isbn);
	}
}
