package com.snails.framework.action;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * ��ȡaction�������ļ�����ת����ActionMapping����
 */
public class ActionMappingManager {
	
	// ����һ����ÿ��action��ǩ��name����ֵ������������ת�����action��ǩ����
	Map<String, ActionMapping> actionMappings = new HashMap<String, ActionMapping>();
	
	public ActionMappingManager() {	}
	// ��ȡ�����ļ�������
	public ActionMappingManager(String[] configFiles) throws Exception {
		//  У�����
		if (null == configFiles || configFiles.length <= 0) {
			throw new Exception("��ָ��action�������ļ���");
		}
		// ����action�����ļ�����������ʼ����actionMappings��
		for (String cfgFile : configFiles) {
			readCfg(cfgFile);
		}
	}
	
	/**
	 * ����action������ȡaction����
	 * @param actionName ����
	 * @return ActionMapping
	 * @throws Exception 
	 */
	public ActionMapping getActionMapping(String actionName) throws Exception {
		if (StringUtils.isBlank(actionName)) {
			try {
				throw new Exception("���actionName������Ϊ�գ�");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ActionMapping am = this.actionMappings.get(actionName);
		if (null == am) {
			throw new Exception("û��Action["+actionName+"]�����ã�");
		}
		
		return am;
	}
	
	/**
	 * ��ȡaction���ã������õ�ActionMapping map��
	 * @param cfgFile action����
	 */
	@SuppressWarnings("rawtypes")
	private void readCfg(String cfgFile) {

		try {
			//  ����action����
//			InputStream is = this.getClass().getResourceAsStream(cfgFile);
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(cfgFile);
			 // ����saxReader����  
	        SAXReader reader = new SAXReader();  
	        // ͨ��read������ȡһ���ļ� ת����Document����  
	        Document doc = reader.read(is);   //new File("src/dom4j/sida.xml")
	        //��ȡ���ڵ�Ԫ�ض���  
			Element actions = doc.getRootElement();
			//  ����ÿ��action��ǩ
			Iterator it = actions.elementIterator();
			while (it.hasNext()) {
				//  actionת��ΪActionMapping
				ActionMapping am = new ActionMapping();
				Element action = (Element) it.next();
				// ��ȡaction��ǩ������name��class
				String actionName = action.attributeValue("name");
				String actionClassName = action.attributeValue("class");
				
				am.setActionName(actionName);
				am.setActionClassName(actionClassName);
				
				// ����action�µ�result��ǩ
				Iterator results = action.elementIterator();
				while (results.hasNext()) {
					Element result = (Element) results.next();
					// ��ȡresult��ǩ������name������
					String resultName = result.attributeValue("name");
					String redirect = result.attributeValue("redirect");
					String resultContent = result.getTextTrim();
					if (StringUtils.isBlank(resultContent))
						resultContent = "input";
					
					//  ����result��ǩֵ
					Map<String, String> rs = new HashMap<String, String>();
					rs.put("RESULT_CONTENT", resultContent);
					rs.put("REDIRECT", redirect);
					am.setResultMap(resultName, rs);
				}
				
				actionMappings.put(am.getActionName(), am);
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		    
		String cfgFile = "E:\\html2pdf_codes\\snails\\src\\com\\snails\\framework\\config\\snails-actions.xml";
		new ActionMappingManager().readCfg(cfgFile);
	}
}
