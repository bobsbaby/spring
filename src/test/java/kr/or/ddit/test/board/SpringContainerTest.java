package kr.or.ddit.test.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.test.board.dao.IBoardDao;
import kr.or.ddit.test.board.service.IBoardService;
import kr.or.ddit.test.ioc.CollectionBean;

//스프링 환경을 이용한 junit 테스트
//@Test 메소드가 실행되기 전에 제공한 조립 설명서를 바탕으로 스프링 컨테이너를 구축
//@Resource를 이용하여 junit 클래스의 필드에 스프링 빈을 손쉽게 주입받을 수 있다.

//@RunWith - junit테스팅중 spring 환경 , @ContextConfiguration 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/spring/ioc/application-context-test.xml")
public class SpringContainerTest {

		private static final Logger logger = LoggerFactory.getLogger(SpringContainerTest.class);
	//name : 스프링 빈 이름
	//boardDao라는 이름의 스프링 빈을 스프링 컨테이너로부터 찾아 boardDao 필드에 주입
	@Resource(name="boardDao")
	private IBoardDao boardDao;

	/**
	 * Method : boardDaoBeantest
	 * 작성자 : PC-05
	 * 변경이력 :
	 * Method 설명 : boardDao 스프링빈이 정상적으로 생성되는지 테스트
	 */
	@Test
	public void boardDaoBeantest() {
		/***Given***/


		/***When***/

		/***Then***/
		assertNotNull(boardDao);
	}

	//boardService 스프링 빈이 정상적으로 생성되는지 테스트 
	@Resource(name="boardService")
	private IBoardService boardService;
	/**
	 * Method : boardServiceBeantest
	 * 작성자 : PC-05
	 * 변경이력 :
	 * Method 설명 : boardService 스프링 빈이 정상적으로 생성되는지 테스트 
	 */

	@Test
	public void boardServiceBeantest() {
		/***Given***/

		/***When***/

		/***Then***/
		assertNotNull(boardService);
	}

	//스프링 빈 scope 테스트를 위한 스프링빈 주입 
	@Resource(name="boardDaoS1")
	private IBoardDao boardDaoS1;

	@Resource(name="boardDaoS1")
	private IBoardDao boardDaoS1_1;

	@Resource(name="boardDaoS2")
	private IBoardDao boardDaoS2;

	@Resource(name="boardDaoS2")
	private IBoardDao boardDaoS2_1;

	//boardDaoP 스프링빈은 prototype scope
	@Resource(name="boardDaoP")
	private IBoardDao boardDaop;

	@Resource(name="boardDaoP")
	private IBoardDao boardDaop2;

	/**
	 * Method : SpringBeanScopeTest
	 * 작성자 : PC-05
	 * 변경이력 :
	 * Method 설명 : 스프링 빈 스코프 테스트 
	 */
	@Test
	public void SpringBeanScopeTest() {
		/***Given***/

		/***When***/

		/***Then***/
		//같은 이름의 스프링 빈 singleton test
		//스프링에서는 스프링 빈 이름을 기준으로 하나의 객체가 생성
		logger.debug("boardDaoS1 : {}", boardDaoS1);
		logger.debug("boardDaoS1_1 : {}", boardDaoS1_1);
		assertEquals(boardDaoS1, boardDaoS1_1);
		assertEquals(boardDaoS2, boardDaoS2_1);


		//디자인 패턴 관점에서 보면 boardDaoS1과 boardDaoS2는 같은 클래스로부터 생성되었으므로 같아야 하지만, 스프링 컨테이너에서 관리되는 scope 개념에 따라 다른 객체로 관리된다. 
		assertNotEquals(boardDaoS1, boardDaoS2);

		//boardDaoP, boardDaoP2는 boardDaoP라는 이름의 prototype scope를 갖는 스프린 빈. prototype 정의에 따라 두 필드에 주입된 객체는 서로 다른 객체이다.
		logger.debug("boardDaop : {}", boardDaop);
		logger.debug("boardDaop2 : {}", boardDaop2);
		assertNotEquals(boardDaop, boardDaop2);
	}
	
	@Resource(name="collectionBean")
	private CollectionBean collectionBean;
	
	/**
	* Method : collectionBeantest
	* 작성자 : PC-05
	* 변경이력 :
	* Method 설명 : collection 객체의 동적 생성-주입 
	*/
	@Test
	public void collectionBeantest() {
		/***Given***/

		/***When***/

		/***Then***/
		logger.debug("collectionBean.getList() : {}", collectionBean.getList());
		logger.debug("collectionBean.getSet() : {}", collectionBean.getSet());
		logger.debug("collectionBean.getMap() : {}", collectionBean.getMap());
		logger.debug("collectionBean.getProperties() : {}", collectionBean.getProperties());
		assertNotNull(collectionBean.getList());
		assertNotNull(collectionBean.getSet());
		assertNotNull(collectionBean.getMap());
		assertNotNull(collectionBean.getProperties());
	}
	
}
