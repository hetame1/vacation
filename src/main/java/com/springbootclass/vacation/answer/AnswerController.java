package com.springbootclass.vacation.answer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import com.springbootclass.vacation.question.Question;
import com.springbootclass.vacation.question.QuestionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/answer")
@Controller
public class AnswerController {
  private final QuestionService questionService;
  private final AnswerService answerService;

  @PostMapping("/create/{id}")
  public String createAnswer(@PathVariable("id") Integer id, @RequestParam(value = "content") String content) {
    /*
     * 답변을 저장, 저장하기 위해서는 이 답변이 참조하는 질문 객체가 필요
     * 답변 저장 전에 질문 객체를 불러와야 함
    */
    Question question = this.questionService.getQuestion(id);
    this.answerService.create(question, content);
    return "redirect:/question/detail/" + id;
  }

  @GetMapping("/delete/{id}")
  public String deleteAnswer(@PathVariable("id") Integer id) {
    this.answerService.delete(id);
    return "redirect:/question/detail/" + id;
  }
}
