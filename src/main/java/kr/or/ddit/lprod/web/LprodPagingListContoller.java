package kr.or.ddit.lprod.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.service.LprodServiceImpl;

/**
 * Servlet implementation class LprodPagingListContoller
 */
@WebServlet("/lprodPagingList")
public class LprodPagingListContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ILprodService lprodService;
    
    @Override
    	public void init() throws ServletException {
    		lprodService = new LprodServiceImpl();
    	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//page, pagesize 파라미터 받기 --> 파라미터는 문자 형식으로 
		String pageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("pagesize");
		
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		int pagesize  = pageSizeStr == null ? 5 : Integer.parseInt(pageSizeStr);
		
		Page p = new Page(page, pagesize);
		request.setAttribute("pageVo", p);
		
		//service 객체를 이용하여 getLprodPagingList를 호출 
		Map<String, Object> map = lprodService.getLprodPagingList(p);
		List<LprodVo> lprodList = (List<LprodVo>) map.get("lprodList");
		
		//반환된 제품 리스트를 request객체에 속성으로 저장
		int paginationSize = (Integer) map.get("paginationSize");
		
		request.setAttribute("lprodList", lprodList);
		request.setAttribute("paginationSize", paginationSize);
		//webapp/useruserPagingList.jsp
		request.getRequestDispatcher("/lprod/lprodpagingList.jsp").forward(request, response);
		
	}


}
