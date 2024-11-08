package com.springbootclass.vacation.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.springbootclass.vacation.question.Question;

@RequiredArgsConstructor
@Service
public class AnswerService {
  private final AnswerRepository answerRepository;

  /*
   * 이 답변이 참조하는 질문 객체와
   * 답변 내용을 입력 인자 받아, 새로운 답변을 DB에 저장하는 메서드
  */  
  public void create(Question question, String content) {
    Answer answer = new Answer();
    
    answer.setContent(content);
    answer.setCreateDate(LocalDateTime.now());
    answer.setQuestion(question);

    this.answerRepository.save(answer);
  }
}
