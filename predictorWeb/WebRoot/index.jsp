<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <form name="form1" method="post" action="/predictorWeb/servlet/Predictor">
    <tr bgcolor="#FFFFFF">
      <!--<td width="119" height="25"><div align="right">appname：</div></td>	-->
      <td colspan="2">&nbsp;&nbsp;<input name="uname" type="text" id="uname" size="30">input the appname</td>
    </tr>
    <tr bgcolor="#FFFFFF">
       <td height="25">&nbsp;</td>
       <td colspan="2">&nbsp;&nbsp;<input type="submit" name="Submit2" value="predict">&nbsp;&nbsp;
     &nbsp;&nbsp;   <input type="reset" name="Submit" value="reset">        </td>
    </tr>
    </form>
  </body>
</html>
