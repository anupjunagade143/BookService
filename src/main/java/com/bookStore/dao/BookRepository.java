package com.bookStore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookStore.doc.book;

@Repository
public interface BookRepository extends CrudRepository<book, Long> {

	@Query("select description from book b where b.name = :name")
	public String searchBook(@Param("name") String name);

	public List<book> findByName(String searchName);

	@Modifying
	@Transactional
	@Query("delete from book b where b.name = :name")
	void deleteBookByName(@Param("name") String name);

	public void saveAndFlush(book tempBook);

}