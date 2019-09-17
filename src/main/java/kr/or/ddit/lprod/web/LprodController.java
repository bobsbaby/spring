package kr.or.ddit.lprod.web;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.service.ILprodService;

@Controller
public class LprodController {
	
	@Resource(name="lprodServiceImpl")
	private ILprodService lprodService;
	
	@RequestMapping(path = "lprodList", method = RequestMethod.GET) 
	public String lprodList(Model model) {
		model.addAttribute("lprodList", lprodService.getLprodList());
		return "lprod/lprodList";
	}
	
	@RequestMapping(path="lprodPagingList", method = RequestMethod.GET)
	public String lprodPagingList(Page page, Model model) {
		model.addAttribute("pageVo", page);
		model.addAttribute("lprodList", lprodService.getLprodPagingList(page));
		return "lprod/lprodPagingList";
	}
	
}
