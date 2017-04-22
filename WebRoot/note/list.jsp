<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="data_list">
	<div class="data_list_title"><span class="glyphicon glyphicon glyphicon-th-list"></span>&nbsp;
		<c:if test="${vs!=null}">『${vs}』</c:if>云记列表 
	</div>
	<div class="note_datas">
		<c:choose>
			<c:when test="${pageInfo.resultCode==-1}"><h3><a href="note">请发布云记</a></h3></c:when>
			<c:otherwise>
				<ul>
					<c:forEach items="${pageInfo.result.data}" var="note">
					<li>『<fmt:formatDate value="${note.pubTime}" pattern="yyyy年MM月dd日"/>』&nbsp;&nbsp;<a href="note?act=view&noteId=${note.noteId}">${note.title}</a> </li>
					</c:forEach>
				</ul>
				<nav style="text-align: center">
				  <ul class="pagination">
				  	<c:if test="${pageInfo.result.hasPre}">
				  	 	<li><a href="main?act=${act}&vv=${vv}&vs=${vs}&cur=${pageInfo.result.currentPage-1}"><span>&laquo;</span></a></li>	
				  	 </c:if>
				  	 <c:forEach var="idx" begin="${pageInfo.result.startPage}" end="${pageInfo.result.endPage}">
				  	 	<li<c:if test="${pageInfo.result.currentPage==idx}"> class='active'</c:if>>
				  	 	<a href="main?act=${act}&vv=${vv}&vs=${vs}&cur=${idx}">${idx}</a></li>
				  	 </c:forEach>		   			
				    <c:if test="${pageInfo.result.hasNext}">
				  	 	<li><a href="main?&act=${act}&vv=${vv}&vs=${vs}&cur=${pageInfo.result.currentPage+1}"><span>&raquo;</span></a></li>	
				  	 </c:if>					  	 			   	    
				  </ul>
				</nav>	
			</c:otherwise>
		</c:choose>		
	</div>
				
</div>