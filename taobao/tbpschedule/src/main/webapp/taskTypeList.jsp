<%@page import="com.taobao.pamirs.schedule.MonitorBean"%>
<%@page import="com.taobao.pamirs.schedule.TaskQueueInfo"%>
<%@page import="com.taobao.pamirs.schedule.ScheduleTaskType"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%
    String isManager= request.getParameter("manager");
%>
<html>
<head>
<title>���������嵥</title>
</head>
<body style="font-family: Arial,"����";margin: 0;padding:0; background-color:#FFFFFF; overflow:auto;SCROLLBAR-FACE-COLOR:#E4EFF1;SCROLLBAR-HIGHLIGHT-COLOR:#8CB2E3;SCROLLBAR-SHADOW-COLOR:#8CB2E3;SCROLLBAR-3DLIGHT-COLOR:#F8F9FD;SCROLLBAR-ARROW-COLOR:#8CB2E3;SCROLLBAR-TRACK-COLOR:#F8F9FD;SCROLLBAR-DARKSHADOW-COLOR:#F8F9FD;">
	<%if("true".equals(isManager)){%>

	������������ �������ƣ�
	<input type="text" id="taskType" width="30"> ����Bean:
	<input type="text" id="dealBean" width="30"> ����ID:
	<input type="text" id="queueIds" width="30">
	<input type="button" value="����" onclick="createTaskType();" width="30">
	<%}%>
	<table border="1" style="border-COLLAPSE: collapse; display: block;">
		<tr>
			<th>���</th>
			<th>��������</th>
			<th>������Bean</th>
			<th>����Ƶ��(��)</th>
			<th>�������(��)</th>
			<th>�߳���</th>
			<th>ÿ�λ�ȡ������</th>
			<th>ÿ��ִ������</th>
			<th>û������ʱ����ʱ��(��)</th>
			<th>����ģʽ</th>
			<th>ÿ�δ��������ݺ�����ʱ��(��)</th>
			<th>�����������Ϣʱ��(Сʱ)</th>
			<th>ִ�п�ʼʱ��</th>
			<th>ִ�н���ʱ��</th>
			<th>�������</th>
		</tr>
		<%
List<ScheduleTaskType> taskTypes =  MonitorBean.getScheduleConfigCenterClient().getAllTaskTypeBaseInfo();
String queueIds ="";
for(int i=0;i<taskTypes.size();i++){
%>
		<tr onclick="openDetail('<%=taskTypes.get(i).getBaseTaskType()%>')">
			<td><%=(i+1)%></td>
			<td><%=taskTypes.get(i).getBaseTaskType()%></td>
			<td><%=taskTypes.get(i).getDealBeanName()%></td>
			<td><%=taskTypes.get(i).getHeartBeatRate()/1000.0 %></td>
			<td><%=taskTypes.get(i).getJudgeDeadInterval()/1000.0 %></td>
			<td><%=taskTypes.get(i).getThreadNumber()%></td>
			<td><%=taskTypes.get(i).getFetchDataNumber() %></td>
			<td><%=taskTypes.get(i).getExecuteNumber() %></td>
			<td><%=taskTypes.get(i).getSleepTimeNoData()==0?"--":taskTypes.get(i).getSleepTimeNoData()/1000.0%></td>
			<td><%=taskTypes.get(i).getProcessorType()%></td>
			<td><%=taskTypes.get(i).getSleepTimeInterval() == 0?"--":taskTypes.get(i).getSleepTimeInterval()/1000.0%></td>
			<td><%=taskTypes.get(i).getExpireOwnSignInterval()%></td>
			<td><%=taskTypes.get(i).getPermitRunStartTime()==null?"--":taskTypes.get(i).getPermitRunStartTime()%></td>
			<td><%=taskTypes.get(i).getPermitRunEndTime()==null?"--":taskTypes.get(i).getPermitRunEndTime()%></td>
			<%
		List<TaskQueueInfo> queueList =MonitorBean.getScheduleConfigCenterClient().loadAllQueue(taskTypes.get(i).getBaseTaskType());
		queueIds ="";
		for(int j=0;j<queueList.size();j++){
			if(j>0){
				queueIds = queueIds+ ",";
			}
			queueIds = queueIds + queueList.get(j).getTaskQueueId();
		}
		%>
			<td><%=queueIds%></td>
		</tr>
		<%
}
%>
	</table>
	<br />
	<b> ������������Ϣ���������ƣ�</b>
	<input type="text" id="baseTaskType" width="30">

	<%if("true".equals(isManager)){%>

	<input type="button" value="�����������Ϣ" onclick="clearTaskType();"
		width="30">
	<input type="button" value="ɾ������" onclick="deleteTaskType();"
		width="30">

	<%}%>
	<br />
	<br />
	<iframe id="taskDetail" height="70%" width="100%"></iframe>
</body>
</html>
<script>
	function openDetail(baseTaskType) {
		document.all("baseTaskType").value = baseTaskType;
		document.all("taskDetail").src = "taskTypeInfo.jsp?baseTaskType="
				+ baseTaskType;
	}
	function createTaskType() {
		var taskType = document.all("taskType").value;
		var dealBean = document.all("dealBean").value;
		var queueIds = document.all("queueIds").value;
		var url = "taskType=" + taskType + "&dealBean=" + dealBean
				+ "&queueIds=" + queueIds;
		url = "createTaskType.jsp?aciton=createTaskType&" + url;
		document.all("taskDetail").src = url;
	}
	function clearTaskType() {
		var taskType = document.all("baseTaskType").value;
		var url = "taskType=" + taskType;
		url = "createTaskType.jsp?aciton=clearTaskType&" + url;
		document.all("taskDetail").src = url;
	}
	function deleteTaskType() {
		var taskType = document.all("baseTaskType").value;
		var url = "taskType=" + taskType;
		url = "createTaskType.jsp?aciton=deleteTaskType&" + url;
		document.all("taskDetail").src = url;
	}
</script>