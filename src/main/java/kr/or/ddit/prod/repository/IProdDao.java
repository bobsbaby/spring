package kr.or.ddit.prod.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface IProdDao {
	List<Map> getDetailInfo(String Lprod_gu);
}
