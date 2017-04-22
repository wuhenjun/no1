<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!-- 右边变化的内容 -->
<div class="data_list">
	<div class="data_list_title">
		<span class="glyphicon glyphicon-cloud-upload"></span>&nbsp;添加云记
	 </div>
	<div class="container-fluid">
	  <div class="row" style="padding-top: 20px;">
	  	<div class="col-md-12">
	  		<c:if test="${ typeInfo.resultCode==-1}">
	  			<h3><a href="noteType">请先添加类别</a></h3>
	  		</c:if>
	  		<c:if test="${typeInfo.resultCode>0}">
	  			<form class="form-horizontal" method="post" action="note.do" onsubmit="return saveNote();">
	  		   		<div class="form-group">
			    		<label for="typeId" class="col-sm-2 control-label">类别:</label>
					    <div class="col-sm-6">
					    	<select id="typeId" class="form-control" name="typeId">
					    		<c:if test="${noteInfo==null}">
									<option value="-1" selected="selected">请选择云记类别...</option>
									<c:forEach items="${typeInfo.resultMsg}" var="type">
										<option value="${type.typeid}">${type.typename}</option>								
									</c:forEach>
								</c:if>
								<c:if test="${noteInfo!=null}">
									<option value="-1" >请选择云记类别...</option>
									<c:forEach items="${typeInfo.resultMsg}" var="type">
										<option value="${type.typeId}" 
											<c:if test="${noteInfo.resultMsg.typeid==type.typeid}">selected="selected"</c:if>>
											${type.typename}</option>								
									</c:forEach>
								</c:if>
							</select>
						
					    </div>
			  		</div>
					<div class="form-group">
						<input type="hidden" name="noteId" value="28">
						<input type="hidden" name="act" value="savenote">
					  <label for="title" class="col-sm-2 control-label">标题:</label>
					  <div class="col-sm-10">					  	
					    <input class="form-control" name="title" id="title" placeholder="云记标题" value="${noteInfo.result.title}">
					  </div>
					 </div>
			  
					  <div class="form-group">
					    <div class="col-sm-12">
					    <textarea id="container" name="content">${noteInfo.result.content}</textarea></div>
						</div>	
						<div class="form-group">
						   <div class="col-sm-offset-6 col-sm-4">
							  <label>
							    <input type="checkbox" name="public" value="yes"/> 是否公开此日志？
							  </label>
						  </div>
					  </div>		 
					  <div class="form-group">
					    <div class="col-sm-offset-6 col-sm-4">
					      <input type="submit" class="btn btn-primary" value="保存">
					      
							<font id="error" color="red"></font>  
					    </div>
					  </div>
					  
				</form>	  		
	  		</c:if>	  		
	  		</div>
	  	</div>
	</div>	
</div>	
<script>
$(function(){
	//UE.getEditor('container');
	 var editor = new UE.ui.Editor({initialFrameHeight:'100%',initialFrameWidth:'100%'});  
     editor.render("container"); 
 }
);
//验证
function saveNote(){
	//验证非空
	
	return true;
}
</script>	
