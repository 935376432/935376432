<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE-edge,chrome=1">
  <meta name="vewport" content="width=device-width, initial-scale=1">
  <link href="${ctx}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${ctx}/resources/css/style.css" rel="stylesheet">
  <script src="${ctx}/resources/jquery/jquery.min.js" type="text/javascript"></script>
  <script src="${ctx}/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  <title>我的文件列表</title>
</head>
<body>
  <div class="container">
    <div class="panel panel-default">
      <div class="panel-heading text-center">
        <h3>我的文件列表</h3>
      </div>
      <div class="panel-body">
        <a class="btn btn-link" href="${ctx}"> <span
          class="glyphicon glyphicon-chevron-left"></span>返回</a> <br> <br>
        <h4>我上传的文件列表</h4>
      </div>
      <table class="table table-hover table-condensed">
        <thead>
          <tr>
            <th width="50%">文件名</th>
            <th width="40%">长度</th>
            <th width="10%">动作</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${fileList}" var="dataFile" varStatus="loopCounter">
          <tr>
            <td><c:out value="${dataFile.name}" /></td>
            <td><c:choose>
              <c:when test="${(dataFile.size < 1024)}">
                ${dataFile.size / (1024 * 1.0)} Bytes
              </c:when>
              <c:when test="${(dataFile.size >= 1024) && (dataFile.size < 1024 * 1024)}">
                <fmt:formatNumber value="${dataFile.size / (1024 * 1.0)}" maxFractionDigits="2" /> KB
              </c:when>
              <c:when test="${(dataFile.size >= 1024 * 1024)}">
                <fmt:formatNumber value="${dataFile.size / (1024 * 1024 * 1.0)}" maxFractionDigits="2" /> MB
              </c:when>
            </c:choose></td>
            <td><a class="btn btn-primary" href="${ctx}/delete/${dataFile.name}"> <span
              class="glyphicon glyphicon-trash"></span> 删除
            </a></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>