package com.springbootclass.vacation.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QuestionController {

  private final QuestionService questionService;

  @GetMapping("/question/list")
  public String list(Model model) {
    // DB Table QUESTION 에서 질문 목록을 가져와 뷰 파일에 전달
    // DB 테이블에 접근하려면 Repository가 필요, 여기서는 QuestionRepository
    // SpringBoot 에서 이 프레임워크가 관리하는 자바 객체가 있다
    // 이 객체들을 Spring Bean 이라고 한다
    // 이 Spring Bean 들은 DI(Dependency Injection) 받아 사용한다
    // DI 받는 방법은 우리는 두 가지를 다룬다
    // 1. @Autowired 어노테이션을 사용하는 방법
    // 2. 생성자를 통한 방법
    // Spring Boot의 Model 객체에 데이터를 넣어주면, 자동으로 이 데이터는 뷰에 전달
    List<Question> questionList = this.questionService.getList();
    model.addAttribute("questionList", questionList);
    return "question_list";
  }

  @GetMapping("/question/detail/{id}")
  public String detail(Model model, @PathVariable("id") Integer id) {
    Question question = this.questionService.getQuestion(id);
    model.addAttribute("question", question);
    return "question_detail";
  }

}