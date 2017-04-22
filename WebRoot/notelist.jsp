<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
   <style>
	.nav1{
		text-align: center;
	}
</style>

<script type="text/javascript">
	$(function(){
		var num = '${notes.whopage}';
		if(num == 2){
			$('#ddd').text('${notes.pageMsg[0].strpubtime}'+'的日志')
		}else if(num == 3){
			$('#ddd').text('${notes.pageMsg[0].typename}')
		}
		//删除数据
		$('.btn.btn-danger.del').click(function(){
		
		 var noteid = $(this).attr('id')
			swal({title: "操作提示",   //弹出框的title
			       text: "确定删除此日志吗？",  //弹出框里面的提示文本
			       type: "warning",    //弹出框类型
			       showCancelButton: true, //是否显示取消按钮
			       confirmButtonColor: "#DD6B55",//确定按钮颜色
			       cancelButtonText: "取消",//取消按钮文本
			       confirmButtonText: "确定"//确定按钮上面的文档
			     }).then(function(isConfirm) {
			    	 if(isConfirm==true){ //删除
			    		 //通过ajax 删除
			    		$.post('user.do',
								{'act':'delnote','noteid':noteid},
								function(data){						
									//console.log(data.resultCode);	
								if(data==-1){ //存在子记录
									swal('操作失败!','此记录不能删除','error'); 	 												
								}else if(data==1){ //删除成功 移除当前行
									swal('操作成功!','已成功删除数据','success');
									if(num == 2){
										window.location.href = "/shangyun/user.do?act=queryNoteByDate&pageNum=${notes.pageNum}&notedate=${notes.pageMsg[0].strpubtime}";	
									}else if(num == 3){
										window.location.href = "/shangyun/user.do?act=queryNoTeTypeBytypename&pageNum=${notes.pageNum}&typename=${yunjileixing.pageMsg[0].typename}";	
									}else{
										window.location.href = "/shangyun/user.do?act=notelist&&pageNum=${notes.pageNum}";	
									}
								} 								
							}); 
			    	 }
			    	 
			     });
		})
	})

</script>
<div class="data_list">
	<div class="data_list_title" id="ddd"><span class="glyphicon glyphicon-edit"></span>&nbsp;个人日志 </div>
<c:choose>
 	<c:when test="${notes.totalMsg == 0 }">
 		<h3>没有日志，快去发表日志吧！</h3>
 	</c:when>
 	<c:otherwise>
<table class="table table-striped table-hover">
	<tbody>
		 <tr>
		 	<th>
		 		日记类别
		 	</th>
		 	<th>
		 		日记题目
		 	</th>
		 	<th>
		 		发布时间
		 	</th>
		 	<th>
		 		操作
		 	</th>
		 </tr>
		 
		 	
		 		<c:forEach var="list" items="${notes.pageMsg}">
			 		<tr>
			 			<td>${list.typename }</td>
			 			<td><a href="user.do?act=queryriji&noteid=${list.noteid}" >${list.title }</a></td>
			 			<td>${list.strpubtime }</td>
			 			<td><button class="btn btn-danger del" type="button" id='${list.noteid}'>删除</button></td>
			 		</tr>			
			 	</c:forEach>
			 	
			 	   </tbody>
			</table>
		 	
 
 <nav class="nav1">
			  <ul class="pagination" id="li01">
			  <c:if test="${notes.totalMsg > 0}">
			 		<c:if test="${notes.pageNum > 1}">
			 			<c:if test="${notes.whopage == 1}">
			  				<li><a href='user.do?act=notelist&pageNum=${1}'>首页</a></li>
			  			</c:if>
			  			<c:if test="${notes.whopage == 2}">
			  				<li><a href='user.do?act=queryNoteByDate&pageNum=${1}&notedate=${notes.pageMsg[0].strpubtime}'>首页</a></li>
			  			</c:if>
			  			<c:if test="${notes.whopage == 3}">
			  				<li><a href='user.do?act=queryNoTeTypeBytypename&pageNum=${1}&typename=${yunjileixing.pageMsg[0].typename}'>首页</a></li>
			  			</c:if>
			  		</c:if>
			  		<c:if test="${notes.pageNum > 1}">
			  			<c:if test="${notes.whopage == 1}">
			  				<li ><a href='user.do?act=notelist&pageNum=${notes.pageNum-1}'>&laquo;</a></li>
			  			</c:if>
			  			<c:if test="${notes.whopage == 2}">
			  				<li ><a href='user.do?act=queryNoteByDate&pageNum=${notes.pageNum-1}&notedate=${notes.pageMsg[0].strpubtime}'>&laquo;</a></li>
			  			</c:if>
			  			<c:if test="${notes.whopage == 3}">
			  				<li ><a href='user.do?act=queryNoTeTypeBytypename&pageNum=${notes.pageNum-1}&typename=${yunjileixing.pageMsg[0].typename}'>&laquo;</a></li>
			  			</c:if>
			  		</c:if>
			  	<c:forEach var="page" begin="${notes.startdaohang}" end="${notes.enddaohang}">	  		
			  		<c:choose>
			  			<c:when test="${notes.pageNum == page}">
			  				<c:if test="${notes.whopage == 1}">
			  				<li class="active"><a href='user.do?act=notelist&pageNum=${page}'>${page}</a></li>
			  			</c:if>
			  			<c:if test="${notes.whopage == 2}">
			  				<li class="active"><a href='user.do?act=queryNoteByDate&pageNum=${page}&notedate=${notes.pageMsg[0].strpubtime}'>${page}</a></li>
			  			</c:if>
			  			<c:if test="${notes.whopage == 3}">
			  				<li class="active"><a href='user.do?act=queryNoTeTypeBytypename&pageNum=${page}&typename=${yunjileixing.pageMsg[0].typename}'>${page}</a></li>
			  			</c:if>
			  			</c:when>
			  			<c:otherwise>
			  				<c:if test="${notes.whopage == 1}">
			  				<li><a href='user.do?act=notelist&pageNum=${page}'>${page}</a></li>
			  			</c:if>
			  			<c:if test="${notes.whopage == 2}">
			  				<li><a href='user.do?act=queryNoteByDate&pageNum=${page}&notedate=${notes.pageMsg[0].strpubtime}'>${page}</a></li>
			  			</c:if>
			  			<c:if test="${notes.whopage == 3}">
			  				<li><a href='user.do?act=queryNoTeTypeBytypename&pageNum=${page}&typename=${yunjileixing.pageMsg[0].typename}'>${page}</a></li>
			  			</c:if>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:forEach>
			  	    <c:if test="${notes.pageNum < notes.totalPageNum}">
			  	    	<c:if test="${notes.whopage == 1}">
			  				<li ><a href='user.do?act=notelist&pageNum=${notes.pageNum+1}'>&raquo;</a></li>
			  			</c:if>
			  			<c:if test="${notes.whopage == 2}">
			  				<li ><a href='user.do?act=queryNoteByDate&pageNum=${notes.pageNum+1}&notedate=${notes.pageMsg[0].strpubtime}'>&raquo;</a></li>
			  			</c:if>
			  			<c:if test="${notes.whopage == 3}">
			  				<li ><a href='user.do?act=queryNoTeTypeBytypename&pageNum=${notes.pageNum+1}&typename=${yunjileixing.pageMsg[0].typename}'>&raquo;</a></li>
			  			</c:if>
			  		</c:if>
			  		<c:if test="${notes.pageNum < notes.totalPageNum}">
			  			<c:if test="${notes.whopage == 1}">
			  				<li><a href='user.do?act=notelist&pageNum=${notes.totalPageNum}'>末页</a></li>
			  			</c:if>
			  			<c:if test="${notes.whopage == 2}">
			  				<li><a href='user.do?act=queryNoteByDate&pageNum=${notes.totalPageNum}&notedate=${notes.pageMsg[0].strpubtime}'>末页</a></li>
			  			</c:if>
			  			<c:if test="${notes.whopage == 3}">
			  				<li><a href='user.do?act=queryNoTeTypeBytypename&pageNum=${notes.totalPageNum}&typename=${yunjileixing.pageMsg[0].typename}'>末页</a></li>
			  			</c:if>
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