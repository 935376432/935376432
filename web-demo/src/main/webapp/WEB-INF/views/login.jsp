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

<body style="background-color: white;">

  <div style="margin-top: 200px; margin-left: auto; margin-right: auto; width: 400px;border: 1px solid #d3d6d8;">
    <div>


      <c:url value="/login" var="loginUrl" />
      <form action="${loginUrl}" method="post" style="height:350px">
        <!-- head -->
        <div style="height:10%;background-color: cornflowerblue;">

        </div>
        <div style="height:10%;">
          <c:if test="${param.error != null}">
            <p>用户名或密码错误。</p>
          </c:if>
          <c:if test="${param.logout != null}">
            <p>已经登出。</p>
          </c:if>
        </div>

        <!-- body -->
        <div style="height:60%;">
          <div>
            <div>
              <span style="width: 80px; display: inline-block;text-align: center;">用户名</span>
              <span>
                <input type="text" id="username" name="username" class="input-style"/>
              </span>
            </div>
          </div>

          <div >
            <div>
              <span style="width: 80px; display: inline-block;text-align: center;">密码</span>
              <span>
                <input type="password" id="password" name="password" class="input-style"/>
              </span>
            </div>
          </div>
          <%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
        </div>

        <!-- foot -->
        <div style="height:20%;">
          <button type="submit" class="btn btn-style" >登录</button>
        </div>

      </form>
        </div>
    </div>




<script>
  /* $(function () {
    $('[name=f]').focus()
  }) */
</script>
<style>
.input-style{
    border-radius: 6px;
    width: 300px;
    margin-top: 10px;
}

.btn-style{
    margin-bottom: 40px;
    width: 100px;
    height: 35px;
    line-height: 30px;
    background: #00cfe2;
    border: none;
    font-size: 12px;
    color: #fff;
    margin-left: auto;
    margin-right: auto;
    border-radius: 30px;
    padding: 0px;
    display: block;
}

</style>
</body>
</html>