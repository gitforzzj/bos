<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
	
<script type="text/javascript">
	function doAdd(){
		$('#addStaffWindow').window("open");
		
	}
	
	function doEdit(){
		var rows = $("#grid").datagrid("getSelections");
		if(rows!=null&&rows.length!=1){
			$.messager.alert("提示信息","请选择一个需要修改的取派员!","warning");
		}else{
			$('#editStaffWindow').window("open");
			$("#editStaffWindow").form("load",rows[0]);
		}
	}
	
	function doDelete(){
		var rows = $("#grid").datagrid("getSelections");
		if(rows.length==0){
			$.messager.alert("提示信息","请选择需要删除的取派员!","warning");
		}else{
			$.messager.confirm("删除确认","你确定要删除选中的取派员吗?",function(r){
				if(r){
					var array = new Array();
					for(var i=0;i<rows.length;i++){
						var staff = rows[i];
						var id = staff.id;
						array.push(id);
					}
					var ids = array.join(",");
					location.href = "staffAction_deleteBatch.action?ids="+ids;
				}
			});
		}
	}
	
	function doRestore(){
		var rows = $("#grid").datagrid("getSelections");
		if(rows.length==0){
			
			$.messager.alert("提示信息","请选择需要还原的取派员!","warning");
		}else{
			if(rows[0].deltag==0){
				$.messager.alert("提示信息","所选取派员存在，不需要还原!","warning");
			}else{
				$.messager.confirm("还原确认","你确定要还原选中的取派员吗?",function(r){
					if(r){
						var array = new Array();
						for(var i=0;i<rows.length;i++){
							var staff = rows[i];
							var id = staff.id;
							array.push(id);
						}
						var ids = array.join(",");
						location.href = "staffAction_doRestore.action?ids="+ids;
					}
						
					
				});
			}
			
		}
	}
	var toolbar ='#easyui_toolbar';
	//工具栏
	/* var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doS
	},  {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, 
	
	{
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},
	
	{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}]; */
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否删除',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已删除";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	function doSearch(value,name){ //用户输入用户名,点击搜素,触发此函数  
        $("#grid").datagrid({  
            title:'searchBox',  
            iconCls:'icon-ok',  
            pageList:[5,10,15,20],  
            nowrap:true,  
            striped:true,  
            collapsible:true,  
            toolbar:"#easyui_toolbar",  
            url:'staffAction_findByNameOrPhone.action?id='+value, //触发此action,带上参数searcValue  
            loadMsg:'数据加载中......',  
            fitColumns:true,//允许表格自动缩放,以适应父容器  
            sortName:'userId',  
            sortOrder:'asc',  
            remoteSort:false,  
            columns : columns,  
            pagination : true, 
			fit:true,
            rownumbers : true  
        })  
    }  
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [1,5,10,15,20,25],
			pagination : true,
			toolbar : toolbar,
			url : "staffAction_pageQuery.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 修改取派员窗口
		$('#editStaffWindow').window({
	        title: '修改取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		
		
	});

	function doDblClickRow(rowIndex, rowData){
		$('#editStaffWindow').window("open");
		$("#editStaffWindow").form("load",rowData);
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
    	 <div  id="easyui_toolbar" region="north" border="false"  
                style="border-bottom: 1px solid #ddd; border-left: 5px;height: 32px; padding: 10px 10px; background: #fafafa;">  
                
                <div id="tb" style="float: left;">  
                    <input id="searchtype"  class="easyui-searchbox"  prompt="输入姓名或手机号"  searcher="doSearch" style="width: 180px; vertical-align: middle;"></input>   
                </div>  
                <div class="datagrid-btn-separator"></div>  
                <div style="float: left;">  
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="doAdd()">新增</a>  
                </div>  
  
                <div class="datagrid-btn-separator"></div>  
                
                <div style="float: left;">  
                    <a href="#" class="easyui-linkbutton" plain="true"  
                        icon="icon-edit" onclick="doEdit()">修改</a>  
                </div> 
  		

  				
                <div class="datagrid-btn-separator"></div> 
  					
                <div style="float: left;">  
                    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-save" onclick="doRestore()">还原</a>  
                </div>  
  
                <div class="datagrid-btn-separator"></div>  
  
                <div style="float: left;">  
                    <a href="#" class="easyui-linkbutton" plain="true"  
                        icon="icon-remove" onclick="doDelete()">删除</a>  
                </div>  
  
            </div>  
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addStaffForm" action="staffAction_add.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td>
							<script type="text/javascript">
								$(function(){
									$("#save").click(function(){
										var v=$("#addStaffForm").form("validate");
										if(v){
											$("#addStaffForm").submit();
										}
									});
									
									var reg = /^1[3|4|5|7|8][0-9]{9}$/;
									$.extend($.fn.validatebox.defaults.rules, { 

										telephone: { 

										validator: function(value, param){ 

										return reg.test(value); 

										}, 

										message: '手機號輸入有誤!' 

										} 

										}); 

								})
							</script>
						<input type="text" data-options="validType:'telephone'" name="telephone" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	
	
	<div class="easyui-window" title="对收派员进行添加或者修改" id="editStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-edit" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editStaffForm" action="staffAction_edit.action" method="post">
			<input type="hidden" name="id"/>
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td>
							<script type="text/javascript">
								$(function(){
									$("#edit").click(function(){
										var v=$("#editStaffForm").form("validate");
										if(v){
											$("#editStaffForm").submit();
										}
									});
									
									var reg = /^1[3|4|5|7|8][0-9]{9}$/;
									$.extend($.fn.validatebox.defaults.rules, { 

										telephone: { 

										validator: function(value, param){ 

										return reg.test(value); 

										}, 

										message: '手機號輸入有誤!' 

										} 

										}); 

								})
							</script>
						<input type="text" data-options="validType:'telephone'" name="telephone" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	
	
	
</body>
</html>	