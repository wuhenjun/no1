<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="data_list">
	<div class="data_list_title">
		<span class="glyphicon glyphicon-eye-open"></span>&nbsp;查看云记 
	</div>
	<div>
		<c:choose>
			<c:when test="${noteInfo.resultCode==-1}">
				<h3><a href="note">没有此云记，请添加</a></h3>
			</c:when>
			<c:otherwise>
			
				<div class="note_title"><h2>${noteInfo.result.title }</h2></div>
				<div class="note_info">
					发布时间：『<fmt:formatDate value="${noteInfo.result.pubTime }" pattern="yyyy年MM月dd日HH点mm分ss秒" /> 』&nbsp;&nbsp;云记类别：${noteInfo.result.typeName}
				</div>
				<div class="note_content">
					<p>${noteInfo.result.content}</p>
				</div>
				<div class="note_btn">
					<button class="btn btn-primary" type="button">修改</button>
					<button class="btn btn-danger" type="button">删除</button>
				</div>	
			</c:otherwise>	
		</c:choose>	
	</div>	
</div>