
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
������������ʷ��Ϣ
</title>
</head>
<body bgcolor="#ffffff">

<%
String baseTaskType =  request.getParameter("baseTaskType");
String ownSign =  request.getParameter("ownSign");
String ip =  request.getParameter("ip");
String orderStr =  request.getParameter("orderStr");
%>

   <table border="1" style=";border-COLLAPSE: collapse;display:block;">
   <tr>
   <th nowrap>���</th>
   <th>��������</th>
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
   List<ScheduleServer> serverList = MonitorBean.getScheduleConfigCenterClient()
        .selectHistoryScheduleServer(baseTaskType,ownSign,ip,orderStr);
   for(int j =0;j<serverList.size();j++){
   %>
	   <tr>
	   <td><%=(j+1) %></td>
	   <td><%=serverList.get(j).getBaseTaskType()%></td>	  
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
</body>
</html>
