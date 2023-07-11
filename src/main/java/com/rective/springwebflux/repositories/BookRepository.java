package com.rective.springwebflux.repositories;

import com.rective.springwebflux.models.Book;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Project: spring-web-flux
 * Package: com.rective.springwebflux.repositories
 * <p>
 * User: LOvandoV
 * Date: 7/7/2023
 * Time: 14:35
 * <p>
 */


@Repository
public class BookRepository {

  private static Map<Long, Book> database;

  public BookRepository() {

    database = new ConcurrentHashMap<>();

    database.put(1L, new Book(1L, "Libro 1", "Autor 1"));
    database.put(2L, new Book(2L, "Libro 2", "Autor 2"));
    database.put(3L, new Book(3L, "Libro 3", "Autor 3"));
    database.put(4L, new Book(4L, "Libro 4", "Autor 4"));
    database.put(5L, new Book(5L, "Libro 5", "Autor 5"));
    database.put(6L, new Book(6L, "Libro 6", "Autor 6"));
    database.put(7L, new Book(7L, "Libro 7", "Autor 7"));

  }

  public Mono<Book> findById(Long id) {

    return Mono.just(database.get(id));

  }

  public Flux<Book> findAll() {

    return Flux.fromIterable(database.values());

  }

  public Mono<Boolean> existsById(Long id) {

    return database.containsKey(id) ? Mono.just(true) : Mono.just(false);

  }

  public Mono<Book> save(Book book) {

    if (book.getId() == null) {

      Long id = database.values().stream().mapToLong(Book::getId).max().orElse(0L);
      book.setId(id);

    }

    database.put(book.getId(), book);

    return Mono.just(book);

  }

  public Mono<Void> deleteById(Long id) {

    database.remove(id);
    return Mono.empty();

  }

  public Mono<Void> deleteAll() {

    database.clear();
    return Mono.empty();

  }

}
