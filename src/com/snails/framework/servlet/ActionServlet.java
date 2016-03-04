package com.snails.framework.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.snails.framework.action.Action;
import com.snails.framework.action.ActionManager;
import com.snails.framework.action.ActionMapping;
import com.snails.framework.action.ActionMappingManager;

/**
 * action���ܿ�������������".action"��β�����󶼻ᱻ���servlet���أ������뵽����д���
 */
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ActionMappingManager amm = null;
       
    public ActionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * ��ȡ���������������".action"��β����뵽����д���
	 * ���򲻴���
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// amm ��init�����г�ʼ��
			ActionMapping am = amm.getActionMapping(getActionName(request));
			//  ��ȡactionʵ��
			Action action = ActionManager.createAction(am.getActionClassName());
			//  ִ��ʵ�����ҵ���߼�
			String result = action.execute(request, response);
			if (StringUtils.isBlank(result)) {
				response.sendError(404, "δ����Action��Ӧ��inputԪ�ء�");
				return;
			}
			
			Map<String, String> resultMap = am.getResult(result);
			//  map��key��ʱд��
			if (Boolean.parseBoolean(resultMap.get("REDIRECT"))) {
				response.sendRedirect(resultMap.get("RESULT_CONTENT"));
			} else {
				request.getRequestDispatcher(resultMap.get("RESULT_CONTENT")).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����URL��ȡaction������
	 * @param request ����
	 * @return
	 */
	private String getActionName(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String actionPath = request.getRequestURI().substring(contextPath.length());
		
		return actionPath.substring(1, actionPath.lastIndexOf(".")).trim();
	}

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//  ��ȡ�����������������Ϣ
		String configFiles = config.getInitParameter("configFiles");
		if (StringUtils.isBlank(configFiles)) {
			try {
				throw new Exception("û�����������������κ�action�����ļ���");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			//  ��ȥ���ָ������ļ���Ŀո�����У�
			this.amm = new ActionMappingManager(configFiles.replaceAll("\\s*", "").split(","));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("================ Action���ü������ ===================");
	}
	
	public static void main(String[] args) {
		System.out.println("1232".split(",")[0]);
	}
}
