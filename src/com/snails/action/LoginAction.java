package com.snails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snails.framework.action.Action;

public class LoginAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//  ���жϣ�����û��������붼��admin���¼��
		if (username.equals(password)) {
			request.setAttribute("username", username);
			return SUCCESS;
		} else {
			return "fail";
		}
	}

}
