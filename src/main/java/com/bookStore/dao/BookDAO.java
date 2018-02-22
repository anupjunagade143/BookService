package com.bookStore.dao;

import java.util.List;

import com.bookStore.doc.book;

public interface BookDAO {

	List<book> getAllBooks();

	book searchBook();

	int addBook(book bookDetails);

	int updateBook(book bookDetails);

	int deleteBook(book bookDetails);
}
