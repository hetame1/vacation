package com.springbootclass.vacation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
  // http://localhost:8080/sbb 요청이 발생하면
  // 그 URL과 매핑된 MainController 클래스의 index 메서드를 실행

  @GetMapping("/")
  public String root() {
      return "redirect:/question/list";
  }
  
  @GetMapping("/sbb")
  public String index() {
    return "index";
  }
}
