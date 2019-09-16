package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.user.model.User;

public class UserDaoTest extends RootTestConfig{
	//userDao를 테스트 하기 위해 필요한 것
	//db 연결, 트랜잭션, dao
	@Resource(name="userDao")
	private IUSerDao userDao;
	
	private String userId = "brownTest";
	
	@Test
	public void getUserListTest() {
		/***Given***/
		
		/***When***/
		List<User> userList = userDao.getUserList();
		/***Then***/
		assertTrue(userList.size() > 104);
	}
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";

		
		/***When***/
		User userVo = userDao.getUser(userId);

		/***Then***/
		assertEquals("브라운", userVo.getUserNm());
	}
	
	/**
	* Method : getUserListOnlyHalf
	* 작성자 : PC-05
	* 변경이력 :
	* Method 설명 : 멤버 50개의 데이터만 조회 테스트~~~~
	*/
	@Test
	public void getUserListOnlyHalf() {
		/***Given***/
		/***When***/
		List<User> userList = userDao.getUserListOnlyHalf();

		/***Then***/
		assertEquals(50, userList.size());
	}
	
	/**
	* Method : getUserPagingListTest
	* 작성자 : PC-05
	* 변경이력 :
	* Method 설명 :사용자 페이징 리스트 조회 테스트
	*/
	@Test
	public void getUserPagingListTest() {
		/***Given***/
		Page page = new Page();
		page.setPage(3);
		page.setPagesize(10);
		/***When***/
		List<User> userlist = userDao.getUserPagingList(page);
		/***Then***/
		assertEquals(10, userlist.size());
	}
	
	/**
	* Method : getUserTotalCnt
	* 작성자 : PC-05
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 전체 사용자 건수 조회 
	*/
	@Test
	public void getUserTotalCnt() {
		/***Given***/
		

		/***When***/
		int totalCnt = userDao.getUserTotalCnt();
		/***Then***/
		assertEquals(105, totalCnt);
	}
	
	/**
	* Method : insertUserTest
	* 작성자 : PC-05
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	 * @throws ParseException 
	*/
	@Test
	public void insertUserTest() throws ParseException {
		/***Given***/
		User user = new User();
		user.setUserId(userId);
		user.setUserNm("브라운테스트");
		user.setAlias("곰테스트");
		user.setPass("brownTest1234");
		user.setAddr1("대전광역시 중구 중앙로 중앙로 76");
		user.setAddr2("영민빌딩 2층 DDIT");
		user.setZipcode("34940");
		user.setReg_dt(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-08"));
		
		/***When***/
		
		int insertCnt = userDao.insertUser(user);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}

}
