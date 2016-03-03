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
		//  简单判断，如果用户名和密码都是admin则登录成
		if (username.equals(password)) {
			request.setAttribute("username", username);
			return SUCCESS;
		} else {
			return "fail";
		}
	}

}
