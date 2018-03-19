package com.xiaobendan.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * 1.按照图书序号查找价格
	 */
	public Integer queryPrice(String isbn){
		String sql = "select price from book where isbn=?";
		Integer price = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
		return price;
	}
	/**
	 * 2.修改库存每次借阅之后调用
	 */
	public void updateStoke(String isbn){
		String sql = "update book_stock set stock=stock-1 where isbn=?";
		jdbcTemplate.update(sql, isbn);
	}
	/**
	 * 3.更新账户余额
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
