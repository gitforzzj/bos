<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>layout</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>

</head>
<body>
	<table id="mytable">
	</table>
	<script type="text/javascript">
		$(function(){
			$("#mytable").datagrid({
				columns:[[
					{title:'编号',field:'id',checkbox:true},
					{title:'姓名',field:'name'},
					{title:'年龄',field:'age'},
					{title:'地址',field:'address'}
				]],
			url:'${pageContext.request.contextPath}/json/datagrid_data.json',
			rownumbers:true,
			singleSelect:true,
			toolbar:[
				{text:'添加',iconCls:'icon-add',
					handler:function(){
						alert('add...');
					}	
				},
				{text:'删除',iconCls:'icon-remove'},
				{text:'删除',iconCls:'icon-edit'},
				{text:'删除',iconCls:'icon-search'}
			],
			pagination:true,
			pageList:[3,5,7,10]
			});
		});
	</script>
</body>
</html>