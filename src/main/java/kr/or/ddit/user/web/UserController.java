package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.mvc.util.model.FileInfo;
import kr.or.ddit.user.model.User;
import kr.or.ddit.user.model.UserValidator;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.FileUtil;

@RequestMapping("user/")
@Controller
public class UserController {


	@Resource(name = "userService")
	private IUserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Method : userView
	 * 작성자 : PC-05
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 상세화면 요청
	 */
	//사용자가 localhost:8081/spring/user/view.do
	@RequestMapping("view.do")
	public String userView() {
		logger.debug("userController.userView()");
		return "user/view";

		// prefix  + viewName + suffix
		// WEB-INF/view/user/view.jsp
	}

	/**
	 * Method : userList
	 * 작성자 : PC-05
	 * 변경이력 :
	 * @param model
	 * @return
	 * Method 설명 : 사용자 전체 리스트 조회
	 */
	@RequestMapping(path = "userList", method = RequestMethod.GET)
	public String userList(Model model) {
		//사용자 정보 전체 조회 
		model.addAttribute("userList", userService.getUserList());

		return "user/userList";	
	}

	@RequestMapping(path="userListOnlyHalf", method = RequestMethod.GET)
	public String userListOnlyHalf(Model model) {
		model.addAttribute("list", userService.getUserListOnlyHalf());

		return "user/userListOnlyHalf";
	}
	//	@GetMapping("userList")
	@RequestMapping(path="userPagingList", method = RequestMethod.GET)
	//	public String userPagingList(Page page, Model model) {
	public String userPagingList(@RequestParam(name = "page", defaultValue = "1") int p, @RequestParam(defaultValue = "10") int pagesize, Model model) {
		//		page = page == 0 ? 1 : page;
		//		pagesize = pagesize == 0 ? 10 : pagesize;
		//		
		Page page = new Page(p, pagesize);


		model.addAttribute("pageVo", page);

		Map<String, Object> resultMap = userService.getUserPagingList(page);
		//		List<User> userList = (List<User>) resultMap.get("userList");
		//		
		//		int paginationSize = (Integer)resultMap.get("pagenationSize");
		//		model.addAttribute("userList", userList);
		//		model.addAttribute("pagenationSize", paginationSize);
		model.addAllAttributes(resultMap);

		return "user/userPagingList";


	}



	/**
	 * Method : getUser
	 * 작성자 : PC-05
	 * 변경이력 :
	 * @param model
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 상세조회 
	 */
	@RequestMapping(path = "user", method = RequestMethod.GET)
	public String getUser(Model model, String userId) {

		model.addAttribute("user", userService.getUser(userId));

		return "user/user";

	}

	/**
	 * Method : getPicture
	 * 작성자 : PC-05
	 * 변경이력 :
	 * @param model
	 * @param userId
	 * @param response
	 * @throws IOException
	 * Method 설명 : 사진 출력하는 메서드 
	 */
	@RequestMapping(path = "userPicture")
	public void getPicture(Model model, String userId, HttpServletResponse response) throws IOException {
		User user = userService.getUser(userId);
		ServletOutputStream sos = response.getOutputStream();
		File picture = new File(user.getRealfilename());
		FileInputStream fis = new FileInputStream(picture);
		byte[] buff = new byte[512];
		int len = 0;
		while( (len = fis.read(buff, 0, 512)) != -1 ) {
			sos.write(buff, 0, len);
		}
		fis.close();
	}

	/**
	 * Method : userFormView
	 * 작성자 : PC-05
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 등록 화면 요청
	 */
	@RequestMapping(path = "userForm", method = RequestMethod.GET)
	public String userFormView() {
		return "user/userForm";
	}

	/**
	 * Method : userFormForm
	 * 작성자 : PC-05
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 등록 요청
	 */
	@RequestMapping(path = "userForm", method = RequestMethod.POST)
	public String userFormForm(User user,BindingResult result, @RequestPart("picture") MultipartFile picture) {

		new UserValidator().validate(user, result);

		//아이디 유효성 검증에 적합하지 않으면 userform화면으로 이동
		if(result.hasErrors()) {
			return "user/userForm";
		}
		else { 
			FileInfo fileInfo = FileUtil.getFileInfo(picture.getOriginalFilename());

			//첨부된 파일이 있을 경우만 업로드 처리
			if (picture.getSize() >0) {
				try {
					picture.transferTo(fileInfo.getFile());
					user.setFilename(fileInfo.getOriginalFileName());		//originalfilename
					user.setRealfilename(fileInfo.getFile().getPath());
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
			// 사랑스러운 소리언니 ♥♥♥♥♥
			int insertCnt = userService.insertUser(user);

			if(insertCnt ==1) 
				//redirect
				return "redirect:/user/user?userId=" + user.getUserId();
			else
				//forward (사용자 등록 화면으로 이동)
				return "user/userForm";
		}
	}
	
	@RequestMapping(path = "userUpdate", method = RequestMethod.POST)
	public String userUpdate(User user, BindingResult result, @RequestPart("picture")MultipartFile picture) {
		new UserValidator().validate(user, result);

		//아이디 유효성 검증에 적합하지 않으면 userform화면으로 이동
		if(result.hasErrors()) {
			return "user/UserUpdate";
		}
		else { 
			
			FileInfo fileInfo = FileUtil.getFileInfo(picture.getOriginalFilename());

			//첨부된 파일이 있을 경우만 업로드 처리
			if (picture.getSize() >0) {
				try {
					picture.transferTo(fileInfo.getFile());
					user.setFilename(fileInfo.getOriginalFileName());		//originalfilename
					user.setRealfilename(fileInfo.getFile().getPath());
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			else { 
				user.setFilename(userService.getUser(user.getUserId()).getFilename());
				user.setRealfilename(userService.getUser(user.getUserId()).getRealfilename());
			}
			
			// 사랑스러운 소리언니 ♥♥♥♥♥
			int updateCnt = userService.updateUser(user);
			logger.debug(updateCnt + "");

			if(updateCnt ==1) 
				//redirect
				return "redirect:/user/user?userId=" + user.getUserId();
			else
				//forward (사용자 등록 화면으로 이동)
				return "user/userUpdate";
		}
	}
	
	@RequestMapping(path = "userUpdate", method = RequestMethod.GET)
	public String getUpdate(Model model, String userId) {

		model.addAttribute("user", userService.getUser(userId));
		return "user/userUpdate";

	}
	
	@RequestMapping(path="userPagingListAjax", method = RequestMethod.GET)
	public String userPagingListAjax(@RequestParam(name = "page", defaultValue = "1") int p, @RequestParam(defaultValue = "10") int pagesize, Model model) {
		Page page = new Page(p, pagesize);


		model.addAttribute("pageVo", page);
		
		
		Map<String, Object> resultMap = userService.getUserPagingList(page);
		model.addAllAttributes(resultMap);

		return "jsonView";
	}
	
	@RequestMapping(path = "userPagingListAjaxView")
	public String userPagingListAjaxView() {
		return "user/userPagingListAjaxView";
	}
	
	
	@RequestMapping(path="userPagingListHtmlAjax", method = RequestMethod.GET)
	public String userPagingListHtmlAjax(@RequestParam(defaultValue = "1") int p,
					@RequestParam(defaultValue = "10") int pagesize, Model model) {
		Page page = new Page(p, pagesize);

		model.addAttribute("pageVo", page);
		
		
		Map<String, Object> resultMap = userService.getUserPagingList(page);
		model.addAllAttributes(resultMap);

		return "user/userPagingListHtmlAjax";
	}
	
}


