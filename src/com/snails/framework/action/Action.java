package com.snails.framework.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

	public String INPUT = "input";
	public String ERROR = "error";
	public String SUCCESS = "success";
	
	/**
	 * ����ҵ���߼�������ÿ��Action�඼��Ҫ��д�˷������������������ļ���Ӧ��ҳ�������ַ���
	 * ���緵�� 'input'��
	 * <result name="input">index.jsp</result>
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
