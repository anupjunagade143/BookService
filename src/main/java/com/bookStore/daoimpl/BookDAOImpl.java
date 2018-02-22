package com.bookStore.daoimpl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.bookStore.dao.BookDAO;
import com.bookStore.doc.book;


public class BookDAOImpl implements BookDAO {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<book> getAllBooks() {
		return null;
	}

	@Override
	public book searchBook() {

		return null;
	}

	@Override
	public int addBook(book bookDetails) {

		return 0;
	}

	@Override
	public int updateBook(book bookDetails) {

		return 0;
	}

	@Override
	public int deleteBook(book bookDetails) {

		return 0;
	}

}
