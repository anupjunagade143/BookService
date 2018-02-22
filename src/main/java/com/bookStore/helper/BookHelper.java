package com.bookStore.helper;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.bookStore.dao.BookRepository;
import com.bookStore.doc.book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BookHelper {

	@Autowired
	DataSource dataSource;

	@Autowired
	BookRepository bookRepository;

	public String getAllBooks() {
		String jsonBookDetails = null;
		// System.out.println("Our DataSource is = " + dataSource);
		Iterable<com.bookStore.doc.book> booklist = bookRepository.findAll();
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonBookDetails = mapper.writeValueAsString(booklist);
		} catch (JsonProcessingException e) {
			e.printStackTrace(); // todo: update logging later using logback.xml
		}
		return jsonBookDetails;
	}

	public String searchBook(String searchName) {
		String result = null;
		String bookDesc = bookRepository.searchBook(searchName);
		if (bookDesc == null) {
			result = "no book exist with name:" + searchName;
		} else {
			result = "Book found for name:" + searchName + " with description as:" + bookDesc;
		}
		return result;
	}

	public String searchBookForDetails(String searchName) {
		String jsonBookDetails = null;
		List<book> bookDetails = bookRepository.findByName(searchName);
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonBookDetails = mapper.writeValueAsString(bookDetails);
		} catch (JsonProcessingException e) {
			e.printStackTrace(); // todo: update logging later using logback.xml
		}
		return jsonBookDetails;
	}

	public void deleteBookByName(String name) {
		bookRepository.deleteBookByName(name);
	}

	public void addBook() {
		book tempBook = createTemporaryBook();
		bookRepository.saveAndFlush(tempBook);
	}

	private book createTemporaryBook() {
		book tempBook = new book("biology", "human anatomy", "james novak", 7);
		return tempBook;
	}

	public void updateBook(String name) {
		List<book> bookDetails = bookRepository.findByName(name);
		if (!CollectionUtils.isEmpty(bookDetails)) {
			book updateBook = bookDetails.get(0);
			updateBook.setDescription("The origin of species");
			bookRepository.saveAndFlush(updateBook);
		}
	}

	public boolean isValid(book bookObj) {
		boolean isValid = true;
		if (StringUtils.isEmpty(bookObj.getName()) || StringUtils.isEmpty(bookObj.getDescription())) {
			isValid = false;
		}
		return isValid;
	}

	public void persistBook(book bookObj) {
		bookRepository.saveAndFlush(bookObj);
	}
}
