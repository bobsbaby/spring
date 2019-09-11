package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfilingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(ProfilingAspect.class);
	
	public void before(JoinPoint joinPoint) {
		logger.debug("porfilingAspect.before()");
	}
	
	public void after(JoinPoint joinPoint) {
		logger.debug("porfilingAspect.after()");
	}
	
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		//시작 시간 
		long stDt = System.nanoTime();
		
		//메소드 실행 
		Object[] args = joinPoint.getArgs();
		Object reobj = joinPoint.proceed(args);
		
		//종료 시간
		long edDt = System.nanoTime();
		logger.debug("edDt - sdDt : {}", edDt-stDt);
		
		return reobj;
	}
}
