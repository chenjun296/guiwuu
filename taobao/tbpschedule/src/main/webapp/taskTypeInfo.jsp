
<%@page import="com.taobao.pamirs.schedule.MonitorBean"%>
<%@page import="com.taobao.pamirs.schedule.TaskQueueInfo"%>
<%@page import="com.taobao.pamirs.schedule.ScheduleServer"%>
<%@page import="com.taobao.pamirs.schedule.ScheduleTaskTypeRunningInfo"%>
<%@page import="com.taobao.pamirs.schedule.ScheduleTaskType"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=GB2312" %>
<html>
<head>
<title>
����������ϸ��Ϣ
</title>
</head>
<body bgcolor="#ffffff">

<%
String baseTaskType =  request.getParameter("baseTaskType");
List<ScheduleTaskTypeRunningInfo> taskTypeRunningInfoList = MonitorBean.getScheduleConfigCenterClient().getAllTaskTypeRunningInfo(baseTaskType);
if(taskTypeRunningInfoList.size() ==0){
%>
���� <%=baseTaskType%>����û����������Ϣ
<%	
}else{
%>
<table border="1" style="border-COLLAPSE: collapse;display:block;">
<%
for(int i=0;i<taskTypeRunningInfoList.size();i++){
%>
<tr style="background-color:#F3F5F8;color:#013299;">
<td>
	<%=taskTypeRunningInfoList.get(i).getTaskType()%> -- <%=taskTypeRunningInfoList.get(i).getOwnSign()%>   
</td>
</tr>
<tr>
<td>
   <table border="1" style="background-color:#FFF475;border-COLLAPSE: collapse;display:block;">
   <tr>
   <th nowrap>���</th>
   <th>������</th>
   <th>��</th>
   <th>IP��ַ</th>
   <th>��������</th>
   <th nowrap>�߳�</th>
   <th>ע��ʱ��</th>
   <th>����ʱ��</th>
   <th nowrap>�汾</th>
   <th nowrap>�´ο�ʼ</th>
   <th nowrap>�´ν���</th>
   <th>JMX</th>
   <th>��������</th>
   
   </tr>
   <%
   List<ScheduleServer> serverList = MonitorBean.getScheduleConfigCenterClient().selectAllValidScheduleServer(taskTypeRunningInfoList.get(i).getTaskType());
   for(int j =0;j<serverList.size();j++){
   %>
	   <tr>
	   <td><%=(j+1) %></td>
	   <td nowrap><%=serverList.get(j).getUuid()%></td>
	   <td><%=serverList.get(j).getOwnSign()%></td>	  
	   <td nowrap><%=serverList.get(j).getIp()%></td>	  
	   <td nowrap><%=serverList.get(j).getHostName()%></td>	
	   <td><%=serverList.get(j).getThreadNum()%></td>	
	   <td nowrap><%=serverList.get(j).getRegisterTime()%></td>	
	   <td nowrap><%=serverList.get(j).getHeartBeatTime()%></td>	
	   <td><%=serverList.get(j).getVersion()%></td>	
	   <td nowrap><%=serverList.get(j).getNextRunStartTime() == null?"--":serverList.get(j).getNextRunStartTime()%></td>	
	   <td nowrap><%=serverList.get(j).getNextRunEndTime()==null?"--":serverList.get(j).getNextRunEndTime()%></td>
	   <td nowrap><%=serverList.get(j).getJmxUrl()%></td>	
	   <td nowrap><%=serverList.get(j).getDealInfoDesc()%></td>	
	   </tr>      
   <%
   }
   %>
   </table> 
</td>
</tr>
<!-- ������Ϣ -->
<tr>
<td>
   <table border="1" style="border-COLLAPSE: collapse;display:block;">
   <tr>
   <th>����</th>
   <th>��ǰ������</th>
   <th>���������</th>
   
   </tr>
   <%
	List<TaskQueueInfo> queueList =MonitorBean.getScheduleConfigCenterClient().loadAllQueue(taskTypeRunningInfoList.get(i).getTaskType());
   for(int j =0;j<queueList.size();j++){
   %>
	   <tr>
	   <td><%=queueList.get(j).getTaskQueueId()%></td>
	   <td><%=queueList.get(j).getCurrentScheduleServer()%></td>
	   <td><%=queueList.get(j).getRequestScheduleServer()==null?"--":queueList.get(j).getRequestScheduleServer()%></td>	   
	   </tr>      
   <%
   }
   %>
   </table> 
</td>
</tr>
<%
}
%>
</table>
<%
}
%>
</body>
</html>
