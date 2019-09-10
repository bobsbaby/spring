package kr.or.ddit.test.board;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.test.board.dao.BoardDao;
import kr.or.ddit.test.board.dao.IBoardDao;
import kr.or.ddit.test.board.service.BoardService;
import kr.or.ddit.test.board.service.IBoardService;

public class Main {
	public static void main (String[] args) {
		//기존 객체 생성 방법 : new 연산자 이용 
		IBoardDao dao = new BoardDao();
		IBoardService boardService = new BoardService();
		boardService.setBoardDao(dao);
		
		//1. spring ioc 컨테이너를 이용한 객체 생성 
		//객체를 만드는 설명서를 spring 한테 위임 (application-context-test 파일) 
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:kr/or/ddit/spring/ioc/application-context-test.xml");
		
		//2. 스프링 컨테이너 (==ioc 컨테이너)로 부터 원하는 객체를 요청 : DL(dependency lookup)
		IBoardDao cDao = (IBoardDao) context.getBean("boardDao");
		cDao.getBoardList();
		
		//클래스 정보를 인자로 넘겨줘 형변환 할 필요 없음
		IBoardService cService = context.getBean("boardService", IBoardService.class);
		cService.getBoardList();
		
		IBoardService cServiceCo = context.getBean("boardServiceCo", IBoardService.class);
		cServiceCo.getBoardList();
	}
}
