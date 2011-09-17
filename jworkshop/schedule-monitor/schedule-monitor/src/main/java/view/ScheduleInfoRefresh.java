/**
 * @(#) ScheduleInfoRefresh.java Created on 2011-1-6 ����03:37:27
 * Copyright (c) 2011 by Taobao.com.
 */
package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.WebServiceClient;

/**
 * 
 * �����ƣ�ScheduleInfoRefresh
 * ��������ˢ��schedule���񻺴�
 * �����ˣ�jishao
 * ����ʱ�䣺2011-1-6 ����03:37:27
 * 
 */
@SuppressWarnings("serial")
public class ScheduleInfoRefresh extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		synchronized (this) {
			WebServiceClient.getScheduleClientsByWebService();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
