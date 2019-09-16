package kr.or.ddit.prod.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProdDao implements IProdDao{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<Map> getDetailInfo(String Lprod_gu) {
		List<Map> list = sqlSession.selectList("prod.getDetailInfo", Lprod_gu);
		return list;	
	}
}
