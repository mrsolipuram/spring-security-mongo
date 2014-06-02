/**
 * 
 */
package com.uchoice.log;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

/**
 * @author madhava
 * 
 */
public class ULogger {

	private Logger log = null;


	public void before(JoinPoint jpt) {
		log = Logger.getLogger(jpt.getTarget().getClass());
		log.debug(jpt.getSignature().getName()
				+ ": method is executing in class :"
				+ jpt.getTarget().getClass().getName());
	}

	public void afterReturning(JoinPoint jpt) {
		log = Logger.getLogger(jpt.getTarget().getClass());
		log.debug(jpt.getSignature().getName()
				+ ": methd is executed in class :"
				+ jpt.getTarget().getClass().getName());
	}

	public void afterThrowing(Throwable e) {
		StackTraceElement st = e.getStackTrace()[0];
		log = Logger.getLogger(st.getClassName());
		log.info(e.getClass() + " raised check log file");
		log.info("Class: " + st.getClassName() + " Method : "
				+ st.getMethodName() + " line : " + st.getLineNumber());
		log.error("Exception:", e);
	}
}
