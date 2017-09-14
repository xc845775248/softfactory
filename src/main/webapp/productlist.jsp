<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/14
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="commons/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
  <title>商品管理</title>
  <%@ include file="commons/meta.jsp"%>
</head>
<body>
<div style="margin: 10px 30px;">
  <a href="#" class="easyui-linkbutton" iconCls="icon-add"
     onclick="product_obj.showEdit('add')">添加</a>&nbsp;&nbsp;
  <a href="#" class="easyui-linkbutton" iconCls="icon-remove"
     onclick="product_obj.remove()">删除</a>&nbsp;&nbsp;
  <a href="#" class="easyui-linkbutton" iconCls="icon-edit"
     onclick="product_obj.showEdit('edit')">修改</a>

  <!-- 新增商品信息窗口 -->
  <div id="editproduct">

  </div>

  <!-- 雇员列表的工具栏设置 -->
  <div id="searchproductForm" style="padding: 10px;">
    <div style="padding: 0 0 0 6px;">
      商品名:
      <input type="text" id="productsName" class="easyui-textbox"/>
      &nbsp;&nbsp;类别:
      <input id="categories" class="easyui-combobox" name="categories"
             data-options="editable:false,valueField:'categoryId',textField:'categoryName',url:'categoryController_find.html'" />

      <a href="#" class="easyui-linkbutton" iconCls="icon-search"
         onclick=product_obj.search();>查询</a>
    </div>
  </div>

  <!-- 雇员列表显示 -->
  <div style="margin-top: 20px;">
    <table id="productDataGrid">

    </table>

  </div>
</div>
<script type="text/javascript">
  $(function() {
    product_obj = {
      search : function() {//查询
        //获得部门号
        var deptno = $('#categories').combobox('getValue');
        // alert(deptno);
        $("#productDataGrid").datagrid(
            "load",
            {
              productsName : $.trim($("#productsName").val()),
              categoryId : categoryId
            });
      },remove : function(){
        var rows = $("#productDataGrid").datagrid("getSelections");
        if(rows.length > 0) {
          $.messager.confirm("消息","确认真的要删除所选的数据吗",function(flag){
            if(flag){
              var ids = [];
              for(var i=0;i<rows.length;i++){
                ids.push(rows[i].categoryId);
              }
              $.ajax({
                type : "post",
                url : "productController_remove.html",
                data : {
                  ids : ids.join(","),
                },
                beforeSend : function(){
                  $("#productDataGrid").datagrid("loading");
                },
                success : function(data){
                  if(data) {
                    $("#productDataGrid").datagrid("loaded");
                    $("#productDataGrid").datagrid("load");
                    $("#productDataGrid").datagrid("unselectAll");
                    $.messager.show({
                      title : "提示",
                      msg : data + "件商品被删除"
                    });
                    $('#categories').combobox('reload');
                  }
                }
              });
            }
          });
        }else {
          $.messager.alert("警告", "请选中要删除的数据","warning");
        }
      },
      showEdit : function(state){
        var url = "productController_findById.html";
        var info = "";
        var id = 0;
        if(state == 'add') {//新增
          info = "新增雇员信息";
        }else {//修改
          info = "修改雇员信息";
          var rows = $("#productDataGrid").datagrid("getSelections");
          if(rows.length == 1){
            id = rows[0].productsNo;
            url += "?productsNo="+id;
          }else{
            $.messager.alert("警告", "必须选中一行", "warning");
            return;
          }
        }
        $("#editproduct").window({
          title : info,
          width : 550,
          height : 480,
          modal : true,
          minimizable : false,
          href : url,
          onClose : function(){
            $("#productDataGrid").datagrid(
                "reload");
            $('#categories').combobox('reload');
          }
        });
      }
    }

    $("#productDataGrid").datagrid( {
      url : "productController.html",
      title : "商品列表",
      fitColumns : true,
      striped : true,
      rownumbers : true,
      columns : [ [ {
        field : "productsNo",
        title : "商品编号",
        width : 100,
        sortable : true
      }, {
        field : "productsName",
        title : "商品名",
        width : 100,
        sortable : true
      } , {
        field : "productsPrice",
        title : "价格",
        width : 100,
        sortable : true
      } , {
        field : "photoPath",
        title : "图片路径",
        width : 100,
        sortable : true
      } , {
        field : "productsDescn",
        title : "DESCN",
        width : 100,
        sortable : true
      } ,{
        field : "categories",
        title : "类别",
        width : 100,
        sortable : true
      } ,{
        field : "op1",
        title : "操作",
        width : 100,
        formatter : function(value, rowData, rowIndex){
          var productsNo = rowData["productsNo"];
          return "<a href='#' onclick=getproduct("+productsNo+")>查看</a>"
        }
      } ] ],
      toolbar : "#searchproductForm",
      pagination : true,
      pageSize : 5,
      pageList : [ 5, 10, 15, 20, 50 ],
      sortName : "productsNo",
      sortOrder : "asc",
    });
  });

  //查看指定菜单
  function getproduct(productsNo){
    $("#editproduct").window({
      title : "查看商品详情",
      width : 550,
      height : 480,
      modal : true,
      minimizable : false,
      href : "productController_view.html?productsNo="+productsNo
    });
  }
</script>

</body>
</html>
