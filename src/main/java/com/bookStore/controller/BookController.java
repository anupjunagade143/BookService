package com.bookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookStore.doc.book;
import com.bookStore.helper.BookHelper;

@Controller
public class BookController {
	/* For testing purpose we can execute below method is order */

	@Autowired
	public BookHelper bookHelper;

	/**
	 * welcome page
	 * 
	 * @return
	 */
	// http://localhost:8080/
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Welcome to Spring boot book service";
	}

	/**
	 * get all books from DB
	 * 
	 * @return
	 */
	// http://localhost:8080/getAllBooks
	@RequestMapping("/getAllBooks")
	@ResponseBody
	public String getAllBooks() {
		return bookHelper.getAllBooks();
	}

	/**
	 * search book name in DB
	 * 
	 * @param name
	 * @return
	 */
	// http://localhost:8080/searchBook/physics
	@RequestMapping("/searchBook/{name}")
	@ResponseBody
	public String searchBook(@PathVariable("name") String name) {
		String bookDesc = bookHelper.searchBook(name);
		return bookDesc;
	}

	/**
	 * search for book name in DB and get all the details of the book
	 * 
	 * @param name
	 * @return
	 */
	// http://localhost:8080/searchBookForDetails/maths
	@RequestMapping("/searchBookForDetails/{name}")
	@ResponseBody
	public String searchBookForDetails(@PathVariable("name") String name) {
		String bookDetails = bookHelper.searchBookForDetails(name);
		return bookDetails;
	}

	/**
	 * delete the book from DB
	 * 
	 * @param name
	 */
	// http://localhost:8080/deleteBookByName/maths
	@RequestMapping("/deleteBookByName/{name}")
	@ResponseBody
	public void deleteBookByName(@PathVariable("name") String name) {
		bookHelper.deleteBookByName(name);
	}

	/**
	 * Add new book and its details to DB . This is temporary code where we are
	 * mocking up an object and inserting it in DB.
	 * 
	 */
	// http://localhost:8080/addBook/
	@RequestMapping("/addBook")
	@ResponseBody
	public void addBook() {
		bookHelper.addBook();
	}

	// http://localhost:8080/persistBook/ [ Header Content-Type = application/json]
	/*
	 * Sample Body message : {"name":"art","description":"learn
	 * drawing","author":"Mark Andy","rating":7}
	 * 
	 */
	@RequestMapping(value = "/persistBook", method = RequestMethod.POST)
	public ResponseEntity<String> persistBook(@RequestBody book bookObj) {
		if (bookHelper.isValid(bookObj)) {
			bookHelper.persistBook(bookObj);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
	}

	/**
	 * Update book details in DB
	 * 
	 * @param name
	 */
	// http://localhost:8080/updateBook/biology
	@RequestMapping("/updateBook/{name}")
	@ResponseBody
	public void updateBook(@PathVariable("name") String name) {
		bookHelper.updateBook(name);
	}

}