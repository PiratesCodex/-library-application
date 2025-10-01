package com.java.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.app.entity.Book;
import com.java.app.entity.LibraryEnums.BookStatus;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

//	 List<Book> findByCategoryContainingIgnoreCaseAndStatusAndAuthorContainingIgnoreCaseAndNameContainingIgnoreCase(
//		        String category, BookStatus status, String author, String name
//		    );
//}
	List<Book> findByTakenBy_UserId(Long userId);

	@Query("""
			SELECT b FROM Book b
			WHERE (:category IS NULL OR LOWER(b.category) LIKE LOWER(CONCAT('%', :category, '%')))
			AND (:status IS NULL OR b.status = :status)
			AND (:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%')))
			AND (:name IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%')))
			""")
	List<Book> searchBooks(@Param("category") String category, @Param("status") BookStatus status,
			@Param("author") String author, @Param("name") String name);
}