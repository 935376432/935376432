<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html >
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE-edge,chrome=1">
  <meta name="vewport" content="width=device-width, initial-scale=1">
  <link href="${ctx}/resources/css/style.css"  rel="stylesheet">
  <!-- bootstrap  css -->
  <%-- <link href="${ctx}/resources/verdors/bootstrap/css/bootstrap.min.css" rel="stylesheet"> --%>
  <link href="${ctx}/resources/verdors/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
  <%-- <link href="${ctx}/resources/verdors/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet"> --%>
  <!-- jquery css -->
  <link href="${ctx}/resources/verdors/jquery-confirm-master/dist/jquery-confirm.min.css" rel="stylesheet">

  <%-- <link href="${ctx}/resources/css/test.css" rel="stylesheet"> --%>


  <%-- <script src="${ctx}/resources/verdors/jquery/3.2.1/jquery.js" type="text/javascript"></script>
  <script src="${ctx}/resources/verdors/angularjs/angular.js" type="text/javascript"></script>
  <script src="${ctx}/resources/verdors/angularjs-router/angular1/angular_1_router.js" type="text/javascript"></script>
  <script src="${ctx}/resources/verdors/angularjs-router/angular1/ng_route_shim.js" type="text/javascript"></script>
  <script src="${ctx}/resources/verdors/lodash/lodash.js" type="text/javascript"></script>
  <script src="${ctx}/resources/verdors/restangular/restangular.min.js" type="text/javascript"></script>
  <script src="${ctx}/resources/verdors/angularjs-router/0.2.18/angular-ui-router.js" type="text/javascript"></script>
  <script src="${ctx}/resources/app/app.js" type="text/javascript"></script>
  <script src="${ctx}/resources/app/components/main.js" type="text/javascript"></script>
  <script src="${ctx}/resources/app/components/demo/haerbin.js" type="text/javascript"></script>
  <script src="${ctx}/resources/app/components/demo/hangzhou.js" type="text/javascript"></script>
  <!-- bootstrap  js -->
  <script src="${ctx}/resources/verdors/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="${ctx}/resources/verdors/bootstrap-3.3.7-dist/js/bootstrap.js" type="text/javascript"></script>
  <!-- jquery js -->
  <script src="${ctx}/resources/verdors/jquery-confirm-master/dist/jquery-confirm.min.js" type="text/javascript"></script> --%>

  <script src="${ctx}/resources/app/thirdpartycombine.js" type="text/javascript"></script>

  <title>首页</title>
</head>
<body ng-app="webModule">

  <div ng-controller="indexMain as ctrl">
    <!-- <h1>test</h1>
    <button ng-click="ctrl.test()">跳转</button>
    <h1>{{ctrl.testbb}}</h1> -->
    <ji-main></ji-main>
  </div>

</body>
</html>
