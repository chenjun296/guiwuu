/**
 * @(#) WebServiceClient.java Created on 2010-12-27 ����06:28:23
 * Copyright (c) 2010 by Taobao.com.
 */
package control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ScheduleClient;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.taobao.pamirs.schedule.IScheduleClient;
import com.taobao.pamirs.schedule.ScheduleServer;

import control.dao.IScheduleMonitorDAO;

/**
 * 
 * �����ƣ�WebServiceClient
 * ���������ɼ�schedule web����ӿ�
 * �����ˣ�jishao
 * ����ʱ�䣺2010-12-27 ����06:28:23
 * 
 */

public class WebServiceClient {

	private static transient Log logger = LogFactory
			.getLog(WebServiceClient.class);
	private static IScheduleMonitorDAO scheduleMonitorDAO;

	private static List<ScheduleClient> clientList;

	public static List<ScheduleClient> getClientsInstance() {
		if (clientList == null) {
			clientList = Collections.synchronizedList(new ArrayList<ScheduleClient>());
		}
		return clientList;
	}

	public static Map servTasks = new HashMap<String, List<String>>();
	
	public static List<ScheduleClient> getScheduleClientsByWebService() {
		getClientsInstance().clear();
		List<ScheduleServer> ssList = null;
		try {
			ssList = scheduleMonitorDAO.getScheduleServer();
		} catch (Exception e) {
			logger.error("��ѯschedule service��Ϣ����!", e);
		}
		if (CollectionUtils.isEmpty(ssList) || ssList.isEmpty()) {
			logger.warn("�������������δ�����Чserver����!");
		} else {
			ssList = filterServerList(ssList);
			for (ScheduleServer ss : ssList) {
				ScheduleClient client = new ScheduleClient();
				IScheduleClient webClient = instanceClient(ss.getIp());
				client.setIp(ss.getIp());
				client.setClient(webClient);
				client
						.setTaskTypeList((List<String>) servTasks.get(ss
								.getIp()));
				getClientsInstance().add(client);
			}
		}
		return clientList;
	}

	/**
	 * filterServerList(���˷�������Ϣ��ȥ���ظ��Ļ���)
	 * @param ssList
	 * @return List
	 * @author jishao
	 * @since 2010-12-30 ����11:13:37
	 */
	public static List<ScheduleServer> filterServerList(
			List<ScheduleServer> ssList) {
		List<ScheduleServer> fserverList = new ArrayList<ScheduleServer>();
		for (ScheduleServer ss : ssList) {
			boolean isMuti = false;
			List<String> taskTypes = new ArrayList<String>();
			for (ScheduleServer fs : fserverList) {
				if (fs.getIp().equals(ss.getIp())) {
					isMuti = true;
					taskTypes.add(ss.getTaskType());
					continue;
				}
			}
			if (!isMuti || fserverList.isEmpty()) {
				fserverList.add(ss);
				taskTypes.add(ss.getTaskType());
				servTasks.put(ss.getIp(), taskTypes);
			}
			if (isMuti
					&& !((List<String>) servTasks.get(ss.getIp())).contains(ss
							.getTaskType())) {
				taskTypes.add(ss.getTaskType());
				servTasks.put(ss.getIp(), taskTypes);
			}
		}
		return fserverList;
	}

	/**
	 * instanceClient(ʵ������Ч������WEB����ͻ���)
	 * @param ip
	 * @return IScheduleClient
	 * @author jishao
	 * @since 2010-12-30 ����11:15:16
	 */
	public static IScheduleClient instanceClient(String ip) {
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean
				.setServiceClass(com.taobao.pamirs.schedule.IScheduleClient.class);
		factoryBean.setAddress("http://" + ip + "/ScheduleWebService");
		IScheduleClient client = (IScheduleClient) factoryBean.create();
		return client;
	}

	public void setScheduleMonitorDAO(IScheduleMonitorDAO scheduleMonitorDAO) {
		WebServiceClient.scheduleMonitorDAO = scheduleMonitorDAO;
	}

}
