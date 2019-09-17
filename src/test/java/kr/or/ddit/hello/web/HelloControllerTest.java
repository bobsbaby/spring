package kr.or.ddit.hello.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.config.test.WebTestConfig;

public class HelloControllerTest extends WebTestConfig {
	
	//controller를 테스트 하기 위해 필요한 것 2가지 
	//applicationContext : 스프링 컨테이너 
	//MockMvc : dispatcherServlet (applicationContext 객체를 통해 생성)

	//주입하려고 하는 필드의 타입과 일치할 경우 이름과 관계없이 주입 
	//만약에 주입하려고 하는 필드의 타입과 스프링 빈중에 타입이 일치하는 빈이 2개 이상 존재할 경우 에러 발생 
//	@Autowired		
//	private WebApplicationContext context;
//	
//	private MockMvc mockMvc;
//	
//	@Before
//	public void setup() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//	}
	
	//server(tomcat)가 없는 환경에서 테스트가 가능하다. 
	@Test
	public void helloTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/hello/hello.do").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String msg = (String)mav.getModel().get("msg");
		String userId = (String) mav.getModelMap().get("userId");
		//controller viewName(String)을 리턴하지만, 스프링 프레임워크 내부에서는 viewName을 ModelAndView 객체로 변환해서  사용 
		//또한 컨트롤러 메소드에서는 viewName 뿐만 아니라 ModelAndView 객체, View 갹채 리턴하는게 가능
		//리턴타입이 void 인 경우도 존재(response 객체를 통해 개발자가 직접 응답을 생성하는 것이 가능 )
		
		/***Then***/
		assertEquals("hello/hello",mav.getViewName());
		assertEquals("hello, world", msg);
		assertEquals("brown_helloControll", userId);
	}
	
	@Test
	public void helloTest2() throws Exception {
		mockMvc.perform(get("/hello/hello.do").param("userId", "sally")).andExpect(status().isOk()).andExpect(view().name("hello/hello"))
																		.andExpect(model().attributeExists("msg"))
																		.andExpect(model().attributeExists("userId"))
																		.andExpect(model().attribute("msg", "hello, world"))
																		.andExpect(model().attribute("userId", "sally_helloControll"));
	}

}
