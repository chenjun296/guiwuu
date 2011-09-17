/**
 * @(#) WebServiceClient.java Created on 2010-12-27 ����06:28:23
 * Copyright (c) 2010 by Taobao.com.
 */

import java.lang.reflect.Field;
import java.util.List;

import model.ScheduleClient;
import model.ScheduleStat;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.spring.annotation.SpringBeanByName;

import utils.MonitorUtils;

import com.taobao.pamirs.schedule.ScheduleServer;

import control.ScheduleMonitorManager;
import control.WebServiceClient;
import control.dao.IScheduleMonitorDAO;

/**
 * 
 * �����ƣ�WebServiceClient
 * ������������
 * �����ˣ�jishao
 * ����ʱ�䣺2010-12-27 ����06:28:23
 * 
 */
@ContextConfiguration(locations = { "classpath:db.xml" })
public class WebServiceClientTest extends AbstractJUnit4SpringContextTests {

	@SpringBean(value = "scheduleMonitorDAO")
	private IScheduleMonitorDAO scheduleMonitorDAO;

	@SpringBeanByName
	public void setScheduleMonitorDAO(IScheduleMonitorDAO scheduleMonitorDAO) {
		this.scheduleMonitorDAO = scheduleMonitorDAO;
	}

	@Test
	public void test_manager() throws Exception {
		int threadNum = 3;
		for (int i = 0; i < threadNum; i++) {
			ScheduleMonitorManager manager = new ScheduleMonitorManager();
			List<ScheduleStat> ss = manager.getMonitorStats();
			if (CollectionUtils.isEmpty(ss)) {
				System.out.println("no results!");
				System.exit(0);
			}
			for (ScheduleStat s : ss) {
				System.out.println("---------------------");
				System.out.println(s.getDealDataSucess());
				System.out.println(s.getFetchDataCount());
				System.out.println("---------------------");
			}
			Thread.sleep(5000);
		}
	}

	@Test
	public void test_client() throws Exception {
		ScheduleClient a = WebServiceClient.getScheduleClientsByWebService()
				.get(0);
		System.out.println(a.getClient().queryQueueCount(
				a.getTaskTypeList().get(0)));
		System.out.println(a.getClient().loadTaskTypeBaseInfo(
				a.getTaskTypeList().get(0)));
		ScheduleServer sm = a.getClient().selectAllValidScheduleServer(
				a.getTaskTypeList().get(0)).get(0);
		String desc = sm.getDealInfoDesc();
		desc = desc.substring(desc.lastIndexOf("FetchDataCount"));
		System.out.println(desc);
		String[] statStrings = desc.split(",");
		String[] tars = new String[] { "fetchDataNum", "fetchDataCount",
				"dealDataSucess", "dealDataFail", "dealSpendTime",
				"otherCompareCount" };
		ScheduleStat stat = new ScheduleStat();

		for (String s : statStrings) {
			System.out.println(s);
			String[] ss = s.split("=");
			for (String t : tars) {
				if (ss[0].equalsIgnoreCase(t)) {
					new MonitorUtils().setComponentsValue(stat, t, ss[1]);
				}
			}
		}
		stat.setIp(sm.getIp());
		stat.setNextRunStartTime(sm.getNextRunStartTime());
		stat.setNextRunEndTime(sm.getNextRunEndTime());
		stat.setTaskType(sm.getTaskType());
		stat.setOwnSign(sm.getOwnSign());
		System.out.println(stat);
	}

	public void setComponentsValue(Object o, String n, Object v) {
		Field[] fields = o.getClass().getDeclaredFields();
		for (int i = 0, len = fields.length; i < len; i++) {
			String varName = fields[i].getName();
			try {
				// ��ȡԭ���ķ��ʿ���Ȩ�� 
				boolean accessFlag = fields[i].isAccessible();
				// ��ȡ�ڶ���f������fields[i]��Ӧ�Ķ����еı��� 
				if (n.equals(varName)) {
					// �޸ķ��ʿ���Ȩ�� 
					fields[i].setAccessible(true);
					fields[i].setLong(o, Long.parseLong((String) v));
					System.out
							.println("����Ķ����а���һ�����µı�����" + varName + " = " + o);
				}
				// �ָ����ʿ���Ȩ�� 
				fields[i].setAccessible(accessFlag);
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void test() throws Exception {
		List<ScheduleServer> ss = scheduleMonitorDAO.getScheduleServer();
		System.out.println(ss.get(0).getIp());
	}
}
