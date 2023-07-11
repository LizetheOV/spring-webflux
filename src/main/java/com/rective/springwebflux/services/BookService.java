package com.rective.springwebflux.services;

import com.rective.springwebflux.models.Book;
import com.rective.springwebflux.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Project: spring-web-flux
 * Package: com.rective.springwebflux.services
 * <p>
 * User: LOvandoV
 * Date: 11/7/2023
 * Time: 07:19
 * <p>
 */

@Service
public class BookService {


  @Autowired
  private BookRepository repository;

  public Mono<Book> findById(Long id) {

    return this.repository.findById(id);

  }

  public Flux<Book> findAll() {

    return this.repository.findAll();

  }

  public Mono<Boolean> existsById(Long id) {

    return this.repository.existsById(id);

  }

  public Mono<Book> create(Book book) {

    if (book.getId() != null)

      return Mono.error(new IllegalArgumentException("Id no debe ser nulo"));


    return this.repository.save(book);

  }

  public Mono<Book> update(Book book) {

    if (book.getId() == null)

      return Mono.error(new IllegalArgumentException("Id debe ser nulo"));


    return this.repository.existsById(book.getId())
        .flatMap(exist -> exist ?
            this.repository.save(book)
            : Mono.error(new IllegalArgumentException("Id del libro debe exitir"))
        );

  }

  public Mono<Void> deleteById(Long id) {

    return this.repository.deleteById(id);

  }

  public Mono<Void> deleteAll() {

    return this.repository.deleteAll();

  }

}
