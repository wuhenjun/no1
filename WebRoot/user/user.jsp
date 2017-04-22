<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 右边变化的内容 -->
<script type="text/javascript" src="statics/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#nickName").bind("blur",function(){
			var value = $("#nickName").val();
			$.ajax({
				type:"post",
				url:"user.do",
				data:{"act":"checkNickName","nick":value},
				success:function(text){
					if(text == "✔"){
						$("#Msg").text(text);
						$("#btn").attr("disabled",false);
					}else{
						$("#Msg").text(text);
						$("#btn").attr("disabled","disabled");
					}
				}
			})
		})
	})

</script>
<div class="data_list">
	<div class="data_list_title"><span class="glyphicon glyphicon-edit"></span>&nbsp;个人中心 </div>
	<div class="container-fluid">
	  <div class="row" style="padding-top: 20px;">
	  	<div class="col-md-8">
	  		<form class="form-horizontal" method="post" action="userupdate.do" 
	  			enctype="multipart/form-data" onsubmit="return checkUser();">
			  <div class="form-group">
			  	<input type="hidden" name="act" value="save">
			    <label for="nickName" class="col-sm-2 control-label">昵称:</label>
			    <div class="col-sm-3">
			      <input class="form-control" name="nick" id="nickName" placeholder="昵称" value="${userInfo.resultMsg.nickname }">
			  		<span id="Msg"></span>
			    </div>
			    <label for="img" class="col-sm-2 control-label">头像:</label>
			    <div class="col-sm-5">
			    	<input type="file" id="img" name="img">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="mood" class="col-sm-2 control-label">心情:</label>
			    <div class="col-sm-10">
			      <textarea class="form-control" name="mood" id="mood" rows="3">${userInfo.resultMsg.mood}</textarea>
			    </div>
			  </div>			 
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="submit" id="btn" class="btn btn-success" disabled="disabled">修改</button>&nbsp;&nbsp;<span style="color:red" id="msg"></span>
			    </div>
			    <span id="Msg">${error01}</span>
			  </div>
			</form>
	  	</div>
  		<div class="col-md-4"><img style="width:260px;height:200px" src="user.do?act=image&img=${userInfo.resultMsg.pic}"></div>
	  </div>
	</div>	
</div>




