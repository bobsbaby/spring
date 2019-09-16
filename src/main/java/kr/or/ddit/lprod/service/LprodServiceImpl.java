package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.repository.ILprodDao;
import kr.or.ddit.lprod.repository.LprodDao;

@Service
public class LprodServiceImpl implements ILprodService{
	
	@Resource(name="lprodDao")
	private ILprodDao lprodDao;

	public LprodServiceImpl() {
		
	}
	
	public LprodServiceImpl(ILprodDao lprodDao) {
		lprodDao = new LprodDao();
	}

	@Override
	public List<LprodVo> getLprodList() {
		
		List<LprodVo> lprodList = lprodDao.getLprodList();
		return lprodList;
	}

	@Override
	public Map<String, Object> getLprodPagingList(Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<LprodVo> lprodList = lprodDao.getLprodPagingList(page);
		int totalCnt = lprodDao.getlprodTotalCnt();
		
		map.put("lprodList", lprodList);
		map.put("paginationSize", (int)Math.ceil((double) totalCnt / page.getPagesize()));
		
		return map;
	}

	
}
