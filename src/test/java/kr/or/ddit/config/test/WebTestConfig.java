package kr.or.ddit.config.test;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.user.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/servlet-context.xml", 
									"classpath:kr/or/ddit/config/spring/context-root.xml",
									"classpath:kr/or/ddit/config/spring/context-datasource-test.xml",
									"classpath:kr/or/ddit/config/spring/context-transaction.xml"})	//controller scan : servlet-context.xml
@WebAppConfiguration		//스프링 컨테이너를 구성할 web 기반 application context로 구성 	
public class WebTestConfig {
	
	@Autowired		
	private WebApplicationContext context;
	
	protected MockMvc mockMvc;
	
	@Resource(name="datasource")
	private BasicDataSource datasource;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("/kr/or/ddit/db/init.sql"));
		populator.setContinueOnError(false);	//init.sql을 실행하다 에러가 발생할 경우 중지 
		DatabasePopulatorUtils.execute(populator, datasource);
	}
	//접근제어자 : private (접근 불가), protected(상속받은 녀석들은 접근 가능), default(같은 패키지의 클래스들은 접근 가능), public(제한없음)
	
	@Ignore
	@Test
	public void dummy() {}
	
}
