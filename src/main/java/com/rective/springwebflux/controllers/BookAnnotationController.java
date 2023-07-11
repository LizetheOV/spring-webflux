/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.rective.springwebflux.controllers;

import com.rective.springwebflux.models.Book;
import com.rective.springwebflux.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Project: spring-web-flux
 * Package: com.rective.springwebflux.controllers
 * <p>
 * User: LOvandoV
 * Date: 11/7/2023
 * Time: 07:28
 * <p>
 */

@RestController
public class BookAnnotationController {

  @Autowired
  private BookService bookService;

  @GetMapping("/books/{id}")
  public Mono<Book> findById(@PathVariable Long id) {

    return bookService.findById(id);

  }

  @GetMapping("/books")
  public Flux<Book> findAll() {

    return bookService.findAll(); //.delayElements(Duration.of(1, ChronoUnit.SECONDS));

  }

  @PostMapping("/books/create")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Book> findById(@RequestBody Mono<Book> book) {

    return book.flatMap(bookService::create);

  }


  @PutMapping("/books/update")
  public Mono<Book> update(@RequestBody Mono<Book> book) {

    return book.flatMap(bookService::create);

  }

  @DeleteMapping("/books/{id}")
  public Mono<Void> delete(@PathVariable Long id) {

    return bookService.deleteById(id);

  }

  @DeleteMapping("/books")
  public Mono<Void> deleteAll() {

    return bookService.deleteAll();

  }

}
