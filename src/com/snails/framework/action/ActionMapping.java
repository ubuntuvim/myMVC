package com.snails.framework.action;

import java.util.HashMap;
import java.util.Map;

/**
 * ÿ��ActionMapping�����Ӧ��һ��action���á�
 * 
 * <action name="login" class="com.snails.action.LoginAction">
 * 		<result name="input">index.jsp</result>
 * 		<result name="success">success.jsp</result>
 * 	</action> 
 */
public class ActionMapping {
	
	private String actionName;
	private String actionClassName;
	//  result���飬����һ����result��name����ֵ������������result��ǩ�ڵ�����
//	private Map<String, String> resultMap = new HashMap<String, String>();
	
	//  result���飬����һ����result��name����ֵ��������Ҳ��һ��map������result��ǩ�ڵ�����������redirect��ֵ
	private Map<String, Map<String, String>> resultMap = new HashMap<String, Map<String, String>>();
	
	/**
	 * ��ȡ��ǩ <result name="resultName" redirect="true">xxx.jsp</result> ������ xxx.jsp
	 * @param result ��ǩname����ֵ
	 * @return result��ǩ������
	 */
	public Map<String, String> getResult(String resultName) {
		return resultMap.get(resultName);
	}
	
	public void setResultMap(String resultNameAttr, Map<String, String> result) {
		this.resultMap.put(resultNameAttr, result);
	} 
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionClassName() {
		return actionClassName;
	}
	public void setActionClassName(String actionClassName) {
		this.actionClassName = actionClassName;
	}
	
}
