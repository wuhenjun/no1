<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<style>
	.nav1{
		text-align: center;
	}
</style>
  
<div class="data_list">
	<div class="data_list_title">
		<span class="glyphicon glyphicon-list"></span>&nbsp;类型列表
		<span class="noteType_add">
			<button class="btn btn-sm btn-success" type="button" id="addBtn">添加类别</button>
		</span>		
	 </div>
	<div>
	<c:choose>
		<c:when test="${pageinfo.totalMsg == 0}">
			<h3>暂无类别信息</h3>
		</c:when>
		<c:otherwise>
			<table class="table table-hover table-striped ">
		 		<tbody id="tr01">
			 		<tr >
			 			<th>编号</th>
			 			<th>类型</th>
			 			<th>操作</th>
			 		</tr>
			 		<c:forEach var="list" items="${pageinfo.pageMsg}">
			 			<tr id="tr${list.typeid }">
					 			<td>${list.typeid }</td>
					 			<td>${list.typename}</td>
					 			<td>
					 			<button class="btn btn-primary" type="button">修改</button>&nbsp;
					 			<button class="btn btn-danger del" type="button">删除</button>
					 			
					 			</td>
				 			</tr>	
			 		</c:forEach>
			 	</tbody>
			 </table>
			 <nav class="nav1">
			  <ul class="pagination" id="li01">
			  <c:if test="${pageinfo.totalMsg > 0}">
			 		<c:if test="${pageinfo.pageNum > 1}">
			  			<li><a href='user.do?act=type&pageNum=${1}'>首页</a></li>
			  		</c:if>
			  		<c:if test="${pageinfo.pageNum > 1}">
			  			<li ><a href='user.do?act=type&pageNum=${pageinfo.pageNum-1}'>&laquo;</a></li>
			  		</c:if>
			  	<c:forEach var="page" begin="${pageinfo.startdaohang}" end="${pageinfo.enddaohang}">	  		
			  		<c:choose>
			  			<c:when test="${pageinfo.pageNum == page}">
			  				<li class="active"><a href='user.do?act=type&pageNum=${page}'>${page}</a></li>
			  			</c:when>
			  			<c:otherwise>
			  				<li><a href='user.do?act=type&pageNum=${page}'>${page}</a></li>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:forEach>
			  	    <c:if test="${pageinfo.pageNum < pageinfo.totalPageNum}">
			  	    	<li ><a href='user.do?act=type&pageNum=${pageinfo.pageNum+1}'>&raquo;</a></li>
			  		</c:if>
			  		<c:if test="${pageinfo.pageNum < pageinfo.totalPageNum}">
			  			<li><a href='user.do?act=type&pageNum=${pageinfo.totalPageNum}'>末页</a></li>
			  		</c:if>  
			  </c:if>
			   <!--  <li ><a id='a1' href="#">&laquo;</a></li>
				    <li><a href="#">1</a></li>
				    <li><a href="#">2</a></li>
				    <li><a href="#">3</a></li>
				    <li><a href="#">4</a></li>
				    <li><a href="#">5</a></li>
			    <li ><a id='a2' href="#">&raquo;</a></li> -->
			  </ul>
			</nav>
		</c:otherwise>
	</c:choose>
	 	
	</div>
</div>	
<!-- 模态框 -->
<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">添加类别</h4>
      </div>
      <div class="modal-body">
      	<form class="form-horizontal">
         <div class="form-group">
         	<input type="hidden" id="typeId" name="typeId" />
		    <label for="typeName" class="col-sm-2 control-label">名称:</label>
		    <div class="col-sm-10">
		    	<input type="text" class="form-control" name="typeName" 
		    	id="typeName" placeholder="类型名称">
		    	<span id="ctn" style="color: red;font-size: 10px"></span>
		    </div>
		  </div>
		 </form>
      </div>
      <div class="modal-footer">
        <button type="button" disabled="disabled" class="btn btn-success" id="bs1">
   		<span class="glyphicon glyphicon-floppy-saved"></span>&nbsp;&nbsp;保存</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
	$(function(){
		//模态框的添加功能
		$('#addBtn').click(function(){
			$('.modal-title').text('添加类别');
			$('#typeName').val("");
			//显示模态框
			$('#myModal').modal('show');
		})
		//通过Ajax验证类别名称是否唯一
		$('#typeName').blur(function(){
			//获取文本框中的值
			var value = $('#typeName').val();
			$.getJSON({
				url:"user.do",
				data:{"act":"checkTypeName","typename":value},
				success:function(data){
					//获取JSON对象,查看结果码
					if(data.resultCode == 1){
						$('#bs1').attr("disabled",false);
						$('#bs1').attr('style','background:green')
						$('#ctn').text("");
					}else{
						$('#bs1').attr("disabled",true);
						$('#bs1').attr('style','background:red')
						$('#ctn').text(data.resultStringMsg);
					}
				}
			})
		})
		//关闭模态框
		$('#bs1').click(function(){

			if($('.modal-title').text() == "修改信息"){
				//获取文本框中的值
				var value = $('#typeName').val();
				//获取typeid
				var typeid = $('#typeId').val();
				$.ajax({
					type:"post",
					url:"user.do",
					data:{"act":"updatetypename","tname":value,"typeid":typeid},
					success:function(data){
						if(data == 1){
							$('#myModal').modal('hide');
							$('#update'+typeid).text(value);
						}
					}
				})
			}else{
				//获取文本框中的值
				var value = $('#typeName').val();
				//将数据返回后台保存，并且显示在前台
				window.location.href = "/shangyun/user.do?act=addtype&type_name="+value;
				//$('#myModal').modal('hide');
			}
		})
		
		//修改数据
		$('.btn.btn-primary').click(function(){
			//获取父节点的id值，获取typeid
			var typeid = this.parentNode.parentNode.id.substring(2);
			//动态的绑定指定行的id值
			$($(this).parent().parent().children('td')[1]).attr('id','update'+typeid);
			//修改提示信息
			$('.modal-title').text('修改信息');
			//获取值
			var typename = $($(this).parent().parent().children('td')[1]).text();
			//将原值带过去
			$('#typeName').val(typename);
			//将typeid号存在隐藏域中
			$('#typeId').val(typeid);
			//显示模态框
			$('#myModal').modal('show');
		})
		//删除数据
		$('.btn.btn-danger.del').click(function(){
			//获取父节点的id值，获取typeid
			var typeid = this.parentNode.parentNode.id.substring(2);
			//获取值
			var typename = $($(this).parent().parent().children('td')[1]).text();
			//获取当前页码
			var pageNum = ${pageinfo.pageNum};
			swal({title: "操作提示",   //弹出框的title
			       text: "确定删除【"+typename+"】吗？",  //弹出框里面的提示文本
			       type: "warning",    //弹出框类型
			       showCancelButton: true, //是否显示取消按钮
			       confirmButtonColor: "#DD6B55",//确定按钮颜色
			       cancelButtonText: "取消",//取消按钮文本
			       confirmButtonText: "确定"//确定按钮上面的文档
			     }).then(function(isConfirm) {
			    	 if(isConfirm==true){ //删除
			    		 //通过ajax 删除
			    		$.post('user.do',
								{'act':'del','tid':typeid},
								function(data){						
									//console.log(data.resultCode);	
								if(data==-1){ //存在子记录
									swal('操作失败!','此记录不能删除','error'); 	 												
								}else if(data==1){ //删除成功 移除当前行
									swal('操作成功!','已成功删除数据','success');
									window.location.href = "/shangyun/user.do?act=type&pageNum="+pageNum;	
								} 								
							}); 
			    	 }
			    	 
			     });
		})
		
	})

</script>

