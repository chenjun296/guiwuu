/**
 * @(#) ScheduleMonitorDAO.java Created on 2010-12-29 ����02:14:10
 * Copyright (c) 2010 by Taobao.com.
 */
package control.dao;

import java.util.List;

import com.taobao.pamirs.schedule.ScheduleServer;

/**
 * 
 * �����ƣ�ScheduleMonitorDAO
 * �����������ݿ�DAO�ӿ�
 * �����ˣ�jishao
 * ����ʱ�䣺2010-12-29 ����02:14:10
 * 
 */
public interface IScheduleMonitorDAO {

	/**
	 * ��ȡ���з�������Ϣ
	 * @param taskInfo
	 * @throws Exception
	 */
	public List<ScheduleServer> getScheduleServer() throws Exception;

}
