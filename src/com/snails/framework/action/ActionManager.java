package com.snails.framework.action;

/**
 * ����action���õ�class����ֵ����õ���Ӧ����
 */
public class ActionManager {
	
	@SuppressWarnings("rawtypes")
	public static Action createAction(String className) {
		
		try {
			Class clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
			//  �жϵ�ǰ�߳��Ƿ������˸�action
			if (null == clazz) {
				clazz = Class.forName(className);
			}
			
			return (Action) clazz.newInstance();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
