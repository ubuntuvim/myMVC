package com.snails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snails.framework.action.Action;

public class LoginAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("=================================");
		request.setAttribute("test", "²âÊÔÊý¾Ý´«µÝ¡£");
		
		return SUCCESS;
	}

}
