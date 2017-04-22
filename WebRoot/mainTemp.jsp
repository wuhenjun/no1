<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="statics/css/note.css" rel="stylesheet">
<link href="statics/bootstrap3/css/bootstrap.css" rel="stylesheet">
<link href="statics/sweetAlert/css/sweetalert2.min.css" rel="stylesheet">
<!-- 配置文件 -->
<script type="text/javascript" src="statics/UEditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="statics/UEditor/ueditor.all.js"></script>
<script src="statics/sweetAlert/js/sweetalert2.min.js"></script>
<script type="text/javascript" src="statics/js/jquery-3.1.0.min.js"></script>
<script src="statics/bootstrap3/js/bootstrap.js"></script>
<style type="text/css">
  body {
       padding-top: 60px;
       padding-bottom: 40px;
     }
</style>
<script type="text/javascript">
	$(function(){
		//选中导航条出现特殊效果
		function daohang(){
			var str = window.location.href;
			if(str == "http://localhost:8080/shangyun/user.do"){
				$('#zhuye').attr('class','active');
			}
			str = str.substring(str.indexOf("user.do"))
			if(str.indexOf("queryNoteByDate") != -1 || str.indexOf("queryNoTeTypeBytypename") != -1){
				$('#rizhi').attr('class','active');
			}
			if(str.indexOf("addtype") != -1){
				$('#leixing').attr('class','active');
			}
			/* if((str.indexOf("user.do") == -1 && str.indexOf("note.do") == -1) || str.length == 0){
				$('#zhuye').attr('class','active');
			} */
			$('#ul01 li').each(function(){
				var str1 = $(this).children("a").attr("href");
				if(str.indexOf(str1) != -1){
					$(this).attr('class','active');
				}else if(str1.indexOf("notelist")!=-1 && str.indexOf("queryriji")!=-1){
					$(this).attr('class','active');
				}
			})
		}
		daohang();
	})
</script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" style="font-size:25px" href="">尚云笔记</a>
    </div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav" id='ul01'>
        <li id="zhuye"><a href="user.do?act=zhuye"><i class="glyphicon glyphicon-cloud"></i>&nbsp;主&nbsp;&nbsp;页</a></li>
        <li><a href="note.do?act=save"><i class="glyphicon glyphicon-pencil"></i>&nbsp;发表云记</a></li>
        <li id='leixing'><a href="user.do?act=type"><i class="glyphicon glyphicon-list"></i>&nbsp;类别管理</a></li>
        <li><a href="user.do?act=info"><i class="glyphicon glyphicon-user"></i>&nbsp;个人中心</a>
       <li id="rizhi"><a href="user.do?act=notelist"><i class="glyphicon glyphicon-user"></i>&nbsp;个人日志</a>
      </li></ul>
      <form class="navbar-form navbar-right" role="search" action="user.do">
        <div class="form-group">
          <input type="hidden" name="act" value="searchKey">
          <input type="text" name="val" class="form-control" placeholder="搜索云记">
        </div>
        <button type="submit" class="btn btn-default">查询</button>
      </form>      
    </div>
  </div>
</nav>
<div class="container">
	<div class="row-fluid">
		<div class="col-md-3">
			<div class="data_list">
				<div class="data_list_title"><span class="glyphicon glyphicon-user"></span>&nbsp;个人中心&nbsp;&nbsp;&nbsp;&nbsp;<a href="user.do?act=logout">退出</a></div>
				<div class="userimg">
					<img src="user.do?act=image&img=${userInfo.resultMsg.pic}">
				</div>
				<div class="nick"><span style="color: red">昵称：</span>${userInfo.resultMsg.nickname}</div>
				<div class="mood"><span style="color: blue">心情：</span>(${userInfo.resultMsg.mood})</div>
			</div>	
			<div class="data_list">
				<div class="data_list_title">
					<span class="glyphicon glyphicon-calendar">
					</span>&nbsp;云记存档
				</div>
				
				<div>
					<ul class="nav nav-pills nav-stacked">
					
					 <c:choose>
					 	<c:when test="${yunjicundang.totalMsg == 0 }">
					 		暂无云记存档
					 	</c:when>
					 	<c:otherwise>
					 		<c:forEach var="list" items="${yunjicundang.pageMsg }">
								<li><a href="user.do?act=queryNoteByDate&notedate=${list.strpubtime}">${list.strpubtime} <span class="badge">${list.count}</span></a></li>
					 		</c:forEach>	
						</c:otherwise>
					 </c:choose>
					</ul>						
				</div>
				
			</div>		
			<div class="data_list">
				<div class="data_list_title">
					<span class="glyphicon glyphicon-list-alt">
					</span>&nbsp;云记类别 
				</div>
				
				<div>
					<ul class="nav nav-pills nav-stacked">
					<c:choose>
					 	<c:when test="${yunjileixing.totalMsg == 0 }">
					 		暂无云记存档
					 	</c:when>
					 	<c:otherwise>
					 		<c:forEach var="list" items="${yunjileixing.pageMsg }">
								<li><a href="user.do?act=queryNoTeTypeBytypename&typename=${list.typename}">${list.typename}<span class="badge">${list.count}</span></a></li>
					 		</c:forEach>	
						</c:otherwise>
					 </c:choose>
					 
						
					 
						
					 
					</ul>						
				</div>
				
			</div>			
		</div>
	</div>
	<div class="col-md-9">
		<jsp:include page="${change}"></jsp:include>
	</div>
</div>
</body>
</html>