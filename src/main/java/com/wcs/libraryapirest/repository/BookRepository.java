package com.wcs.libraryapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wcs.libraryapirest.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	Book findByTitleContainingOrDescriptionContaining(String text, String otherText);
}
