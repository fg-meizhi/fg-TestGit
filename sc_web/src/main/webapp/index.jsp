<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String path = request.getContextPath(); %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <%--<meta charset="utf-8">--%>
    <meta http-equiv="Content-Type" content="text/html charset=utf-8">
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="css/style.css">
</head>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/controller.js"></script>
<script type="text/javascript">
    var path = '<%=path %>';
</script>

</head>
<body>

<div class="wrapper">
    <div class="header">IBM中行网银项目组服务器管理</div>
    <div class="content">
        <p class="tips">历史操作</p>
        <dl class="history">
        </dl>
    </div>
    <div class="footer tac">--版权归IBM中行网银项目组所有--</div>
</div>

</body>
</html>
