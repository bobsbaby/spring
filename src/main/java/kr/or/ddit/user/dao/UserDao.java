package kr.or.ddit.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.User;

//class 명에서 맨 첫글자를 소문자로 변경한 문자열이 스프링 빈 이름으로 기본 설정됨
//다른 이름의 스프링 빈 이름으로 등록을 하려면 속성 설정  @Repository("설정하고자 하는 스프링 빈 이름")
@Repository()
public class UserDao implements IUSerDao{

	/**
	* Method : getUserList
	* 작성자 : PC-05
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Override
	public List<User> getUserList() {
		List<User> userList = new ArrayList<User>();
		userList.add(new User("brown"));
		userList.add(new User("cony"));
		userList.add(new User("sally"));
		return userList;
	}
	
	
}