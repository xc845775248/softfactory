<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/14
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="commons/taglib.jsp"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
  <title>菜单管理</title>
  <%@ include file="commons/meta.jsp"%>
</head>
<body>
<style>
  .input {
    width: 200px;
    height: 20px;
    border: 1px solid #95B8E7;
  }

  .btn {
    width: 100px;
    height: 20px;
    border: 1px solid #95B8E7;
  }
</style>
<form action="" method="post" id="empForm">
  <table width="500" height="198" id="empTable"
         style="margin: 10px auto;">
    <tr>
      <td width="25" height="35" style="">
        &nbsp;
      </td>
      <td width="117">
        <div align="right">
          产品号：
        </div>
      </td>
      <td width="269">
        ${Products.productsNo }
      </td>
      <td width="69">
        &nbsp;
      </td>
    </tr>
    <tr>
      <td height="35">
        &nbsp;
      </td>
      <td>
        <div align="right">
          产品名：
        </div>
      </td>
      <td>
        ${Products.productsName }
      </td>
      <td>
        &nbsp;
      </td>
    </tr>

    <tr>
      <td height="35">
        &nbsp;
      </td>
      <td>
        <div align="right">
          价格：
        </div>
      </td>
      <td>
        ${Products.productsPrice }
      </td>
      <td>
        &nbsp;
      </td>
    </tr>
    <tr>
      <td height="35">
        &nbsp;
      </td>
      <td>
        <div align="right">
          图片路径：
        </div>
      </td>
      <td>
        <spring:eval expression="Products.photoPath"/>
      </td>
      <td>
        &nbsp;
      </td>
    </tr>
    <tr>
      <td width="25" height="35" style="">
        &nbsp;
      </td>
      <td width="117">
        <div align="right">
          产品DESCN：
        </div>
      </td>
      <td width="269">
        ${Products.productsDescn }
      </td>
      <td width="69">
        &nbsp;
      </td>
    </tr>
    <tr>
      <td height="35">
        &nbsp;
      </td>
      <td>
        <div align="right">
          所属类名：
        </div>
      </td>
      <td>
        ${Products.categories.categoryName }
      </td>
      <td>
        &nbsp;
      </td>
    </tr>

    <tr>
      <td>
        &nbsp;
      </td>
      <td colspan="2">
        <div align="center">
          <a href='javascript:closeWindowOfproductView()' class="easyui-linkbutton">返回</a>
        </div>
      </td>
      <td height="20">
        &nbsp;
      </td>
    </tr>
  </table>
</form>
<script type="text/javascript">
  function closeWindowOfEmpView(){
    $("#editEmp").window("close",true);
  }
</script>


</body>
</html>
