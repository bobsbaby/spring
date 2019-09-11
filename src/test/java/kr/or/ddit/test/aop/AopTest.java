package kr.or.ddit.test.aop;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/spring/aop/application-context-aop-test.xml")
public class AopTest {
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Test
	public void aopTest() {
		/***Given***/

		/***When***/
		userService.getUserList();
		/***Then***/
		assertNotNull(userService);
	}

}
