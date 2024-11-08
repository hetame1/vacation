package com.springbootclass.vacation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springbootclass.vacation.answer.Answer;
import com.springbootclass.vacation.answer.AnswerRepository;
import com.springbootclass.vacation.question.Question;
import com.springbootclass.vacation.question.QuestionRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class VacationApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testJpa01() {
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
	}

	// 조회
	@Test
	void testJpa02() {
		// question 테이블에 저장된 레코드를 모두 인출
		// 리포지토리의 findAll 메서드를 사용 
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	// 조회
	@Test
	void testJpa03() {
		Optional<Question> oq = this.questionRepository.findById(1);
		if (oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
	}

	// 조회
	@Test
	void testJpa04() {
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());
	}

	// 조회
	@Test
	void testJpa05() {
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);

		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	// 삭제
	@Test
	void testJpa06() {
		// 1. Question table에 레코드가 2개 있음을 확인
		assertEquals(2, this.questionRepository.count());
		// 2. Question table에서 id가 1인 레코드를 찾아서 삭제
		// 그렇게 하려면 일단 id가 1인 레코드를 인출해야함
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		// 3. Question table에 레코드가 1개 있음을 확인
		assertEquals(1, this.questionRepository.count());
	}

	@Test
	void testJpa07() {
		// Question 테이블에서 id가 2번인 레코드에 대한 답변 레코드를 Answer 테이블에 저장
		Answer answer = new Answer();
		answer.setContent("네 자동으로 생성됩니다.");
		answer.setCreateDate(LocalDateTime.now());

		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		answer.setQuestion(q);

		this.answerRepository.save(answer);
	}

	@Test
	void testJpa08() {
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();

		// 답변 데이터를 통해 질문 데이터를 가져옴
		assertEquals(2, a.getQuestion().getId());
	}

	@Test
	@Transactional 
	// 테스트 메서드에 @Transactional 어노테이션을 추가하여 트랜잭션 범위 내에서 작업을 수행
	// 이 어노테이션을 추가하면 테스트 메서드가 종료될 때까지 트랜잭션이 유지되고, 트랜잭션이 종료되면 데이터가 롤백됨
	// 이렇게 하면 테스트 메서드가 종료된 후에도 데이터가 유지되는 문제를 해결할 수 있음
	void testJpa09() {
		// 질문 데이터를 통해 답변 데이터를 가져옴
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		// 1. 이 질문에 대한 답변을 가져오기
		List<Answer> answerList = q.getAnswerList();

		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}
}
