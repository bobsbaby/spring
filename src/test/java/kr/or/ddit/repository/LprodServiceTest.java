package kr.or.ddit.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.service.LprodServiceImpl;

public class LprodServiceTest extends RootTestConfig{
	
	@Resource(name="lprodServiceImpl")
	private ILprodService lprodService;
	
	@Test
	public void getLprodListTest() {
		/***Given***/

		/***When***/
		List<LprodVo> lprodList = lprodService.getLprodList();
		/***Then***/
		assertEquals(10, lprodList.size());
	}
	
	/**
	* Method : getLprodPagingListTest
	* 작성자 : PC-05
	* 변경이력 :
	* Method 설명 :전체 제품 그룹 건수 조회 
	*/
	@Test 
	public void getLprodPagingListTest() {
		/***Given***/
		Page page = new Page();
		page.setPage(1);
		page.setPagesize(2);

		/***When***/
		Map<String, Object> map = lprodService.getLprodPagingList(page);
		List<LprodVo> lprodList = (List<LprodVo>) map.get("lprodList");
		int PaginationSize = (Integer) map.get("paginationSize");
		/***Then***/
		assertEquals(2, lprodList.size());
	}
	
	/**
	* Method : getlprodTotalCnt
	* 작성자 : PC-05
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 전체 제품그룹 수 조회 
	*/
	@Test
	public void getlprodTotalCnTest() {
		/***Given***/
		int totalCnt = 10;
		int pagisize = 2;

		/***When***/
		double paginationSize = Math.ceil((double)(totalCnt/pagisize));
		
		/***Then***/
		assertEquals(10, totalCnt);
	}
	

}
