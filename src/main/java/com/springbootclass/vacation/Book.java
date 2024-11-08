package com.springbootclass.vacation;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class Book {
  private String title;
  private String author;

  public static void main(String[] args) {
    // Book book = new Book();
    // book.setTitle("Spring");
    // book.setAuthor("Hello");

    Book book = new Book("Spring", "Hello");
    
    System.out.println(book.getTitle());
    System.out.println(book.getAuthor());
  }
}
