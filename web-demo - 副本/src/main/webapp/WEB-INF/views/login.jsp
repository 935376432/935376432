<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:url value="/login" var="loginUrl"/>
<!DOCTYPE html>
<%-- <html lang="<%= RequestContextUtils.getLocale(request).toString() %>" class="no-js" ng-app="shtermApp"> --%>
<html class="no-js" ng-app="shtermApp">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE-edge,chrome=1">
  <meta name="vewport" content="width=device-width, initial-scale=1">
  <title>web-demo</title>

  <link href="${ctx}/resources/css/style.css"  rel="stylesheet">
  <link href="${ctx}/resources/verdors/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
  <script type="text/javascript">


  </script>
</head>

<body style="background-color: #c4c5d8;">

  <div style="margin-top: 200px; margin-left: auto; margin-right: auto; width: 400px;">
    <div>
      <c:url value="/login" var="loginUrl" />
      <form action="${loginUrl}" method="post"  style="background-color: white;
    height: 300px;">
        <c:if test="${param.error != null}">
          <p>用户名或密码错误。</p>
        </c:if>
        <c:if test="${param.logout != null}">
          <p>已经登出。</p>
        </c:if>
        <p>
          <label for="username">用户名</label> <input type="text" id="username" name="username" />
        </p>
        <p>
          <label for="password">密码</label> <input type="password" id="password" name="password" />
        </p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit" class="btn" style="margin-bottom: 40px;
              width: 360px;
              height: 42px;
              line-height: 42px;
              background: #00cfe2;
              border: none;
              font-size: 12px;
              color: #fff;
              margin-left: auto;
              margin-right: auto;
              border-radius: 30px;
              padding: 0px;
              display: block;">Log in</button>

      </form>
    </div>
  </div>



<script>
  /* $(function () {
    $('[name=f]').focus()
  }) */
</script>
</body>
</html>