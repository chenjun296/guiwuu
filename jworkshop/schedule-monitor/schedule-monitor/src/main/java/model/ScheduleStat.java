/**
 * @(#) ScheduleStat.java Created on 2010-12-30 ����11:51:44
 * Copyright (c) 2010 by Taobao.com.
 */
package model;


/**
 * 
 * �����ƣ�ScheduleStat
 * ��������ͳ������bean
 * �����ˣ�jishao
 * ����ʱ�䣺2010-12-30 ����11:51:44
 * 
 */
public class ScheduleStat {

	private String ip;
	private String taskType;
	private String ownSign;
	private String nextRunStartTime;
	private String nextRunEndTime;  
	private long fetchDataNum;//��ȡ��������
	private long fetchDataCount;//��ȡ����
	private long dealDataSucess;//����ɹ���������
	private long dealDataFail;//����ʧ�ܵ�������
	private long dealSpendTime;//�����ܺ�ʱ,û����ͬ�������ܴ���һ�������
	private long otherCompareCount;//����ȽϵĴ���
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getOwnSign() {
		return ownSign;
	}
	public void setOwnSign(String ownSign) {
		this.ownSign = ownSign;
	}
	public String getNextRunStartTime() {
		return nextRunStartTime;
	}
	public void setNextRunStartTime(String nextRunStartTime) {
		this.nextRunStartTime = nextRunStartTime;
	}
	public String getNextRunEndTime() {
		return nextRunEndTime;
	}
	public void setNextRunEndTime(String nextRunEndTime) {
		this.nextRunEndTime = nextRunEndTime;
	}
	public long getFetchDataNum() {
		return fetchDataNum;
	}
	public void setFetchDataNum(long fetchDataNum) {
		this.fetchDataNum = fetchDataNum;
	}
	public long getFetchDataCount() {
		return fetchDataCount;
	}
	public void setFetchDataCount(long fetchDataCount) {
		this.fetchDataCount = fetchDataCount;
	}
	public long getDealDataSucess() {
		return dealDataSucess;
	}
	public void setDealDataSucess(long dealDataSucess) {
		this.dealDataSucess = dealDataSucess;
	}
	public long getDealDataFail() {
		return dealDataFail;
	}
	public void setDealDataFail(long dealDataFail) {
		this.dealDataFail = dealDataFail;
	}
	public long getDealSpendTime() {
		return dealSpendTime;
	}
	public void setDealSpendTime(long dealSpendTime) {
		this.dealSpendTime = dealSpendTime;
	}
	public long getOtherCompareCount() {
		return otherCompareCount;
	}
	public void setOtherCompareCount(long otherCompareCount) {
		this.otherCompareCount = otherCompareCount;
	}
}
