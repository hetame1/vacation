package com.springbootclass.vacation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HelloLombok {
  private String hello;
  private int lombok;

  // public String getHello() {
  //   return this.hello;
  // }

  // public int getLombok() {
  //   return this.lombok;
  // }

  // public void setHello(String hello) {
  //   this.hello = hello;
  // }

  // public void setLombok(int lombok) {
  //   this.lombok = lombok;
  // }

  public static void main(String[] args) {
    // HelloLombok helloLombok = new HelloLombok();
    HelloLombok helloLombok = new HelloLombok("hello", 5000);

    // helloLombok.setHello("hello");
    // helloLombok.setLombok(5000);

    System.out.println(helloLombok.getHello());
    System.out.println(helloLombok.getLombok());
  }
}
