<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tabs</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>

</head>
<body class="easyui-layout">
	<!-- 使用div元素描述每个区域 -->
	<div title="XXX管理系统" style="height: 100px" data-options="region:'north'">北部区域</div>
	<div title="系统菜单" style="width: 200px" data-options="region:'west'">
		<!-- 制作accordion折叠面板 
			fit:true----自适应(填充父容器)
		-->
		<div class="easyui-accordion" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div data-options="iconCls:'icon-cut'" title="面板一">
				<a id="but1" class="easyui-linkbutton">添加一个选项卡</a>
				<script type="text/javascript">
					$(function(){
						//页面加载完成后，为上面的按钮绑定事件
						$("#but1").click(function(){
							//判断“系统管理”选项卡是否存在
							var e = $("#mytabs").tabs("exists","系统管理");
							if(e){
								//已经存在，选中就可以
								$("#mytabs").tabs("select","系统管理");
							}else{
								//调用tabs对象的add方法动态添加一个选项卡
								$("#mytabs").tabs("add",{
									title:'系统管理',
									iconCls:'icon-edit',
									closable:true,
									content:'<iframe frameborder="0" height="100%" width="100%" src="https://www.baidu.com"></iframe>'
								});
							}
						});
					});
				</script>
			</div>
			<div title="面板二">
				<!-- 展示ztree效果:使用标准json数据构造ztree -->
				<ul id="ztree1" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						var setting={};
						var zNodes=[
								{"name":"节点一","children":[
									{"name":"节点一_1"},
									{"name":"结点一_2"}
								]},
								{"name":"节点二"},
								{"name":"节点三"}
							];
						$.fn.zTree.init($("#ztree1"),setting,zNodes);
					});
				</script>
			</div>
			<div title="面板三">
				<ul id="ztree2" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						var setting2={
								data: {
									simpleData: {
										enable: true
									}
								}
						};
						var zNodes2=[
								{"id":1,pId:"0","name":"节点一"},
								{"id":2,pId:"1","name":"节点二"},
								{"id":3,pId:"2","name":"节点三"}
							];
						$.fn.zTree.init($("#ztree2"),setting2,zNodes2);
					});
				</script>
			</div>
			<div title="面板四">
				<ul id="ztree3" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						var setting3={
								data: {
									simpleData: {
										enable: true
									}
								},
								callback: {
									onClick: function(event,treeId,treeNode){
										if(treeNode.page != undefined){
											var e = $("#mytabs").tabs("exists",treeNode.name);
											if(e){
												$("#mytabs").tabs("select",treeNode.name);
											}else{
												$("#mytabs").tabs("add",{
													title:treeNode.name,
													closable:true,
													content:'<iframe frameborder="0" height="100%" width="100%" src="'+treeNode.page+'"></iframe>'
													});
											}
										}
									}
								}
						};
						var url = "${pageContext.request.contextPath}/json/menu.json";
						$.post(url,{},function(data){
							//调用API初始化ztree
							$.fn.zTree.init($("#ztree3"), setting3, data);
						},'json');
				});
				</script>
			</div>
		</div>
	</div>
	<div data-options="region:'center'">
		<!-- 制作一个tabs选项卡面板 -->
		<div id="mytabs" class="easyui-tabs" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div data-options="closable:true,iconCls:'icon-cut'" title="面板一">1111</div>
			<div data-options="closable:true" title="面板二">2222</div>
			<div title="面板三">3333</div>
		</div>
	</div>
	<div style="width: 100px" data-options="region:'east'">东部区域</div>
	<div style="height: 50px" data-options="region:'south'">南部区域</div>
</body>
</html>