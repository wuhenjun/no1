<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<style>

p {
    margin: 0 0 1em;
}
.nav1{
		text-align: center;
	}
.comment {
    overflow: hidden;
    padding: 0 0 1em;
    border-bottom: 1px solid #ddd;
    margin: 0 0 1em;
    *zoom: 1;
}

.comment-img {
    float: left;
    margin-right: 33px;
    border-radius: 5px;
    overflow: hidden;
}

.comment-img img {
    display: block;
}

.comment-body {
    overflow: hidden;
}

.comment .text {
    padding: 10px;
    border: 1px solid #e5e5e5;
    border-radius: 5px;
    background: #fff;
}

.comment .text p:last-child {
    margin: 0;
}

.comment .attribution {
    margin: 0.5em 0 0;
    font-size: 14px;
    color: #666;
}

/* Decoration */

.comments,
.comment {
    position: relative;
}

.comments:before,
.comment:before,
.comment .text:before {
    content: "";
    position: absolute;
    top: 0;
    left: 65px;
}

.comments:before {
    width: 3px;
    top: -20px;
    bottom: -20px;
    background: rgba(0,0,0,0.1);
}

.comment:before {
    width: 9px;
    height: 9px;
    border: 3px solid #fff;
    border-radius: 100px;
    margin: 16px 0 0 -6px;
    box-shadow: 0 1px 1px rgba(0,0,0,0.2), inset 0 1px 1px rgba(0,0,0,0.1);
    background: #ccc;
}

.comment:hover:before {
    background: orange;
}

.comment .text:before {
    top: 18px;
    left: 78px;
    width: 9px;
    height: 9px;
    border-width: 0 0 1px 1px;
    border-style: solid;
    border-color: #e5e5e5;
    background: #fff;
    -webkit-transform: rotate(45deg);
    -moz-transform: rotate(45deg);
    -ms-transform: rotate(45deg);
    -o-transform: rotate(45deg);
}
</style>

<div class="jumbotron">
  <h4>
	 <c:if test="${note.resultCode == 1}">
		 类别：${note.resultMsg.typename}
	 </c:if>
	 <c:if test="${note.resultCode == 2}">
		 昵称：${note.resultMsg.nickname}
	 </c:if>
  </h4>
  <h2 style="color: red">${note.resultMsg.title}</h2>
  <p><h6>发布时间：${note.resultMsg.pubtime}</h6></p>
</div>
<div class="form-control" name="mood" id="mood" style="height: 300px">${note.resultMsg.content}</div>
<br><br>

<c:if test="${pinglun.totalMsg == 0}">暂无评论，快去抢沙发吧！</c:if>
<c:if test="${pinglun.totalMsg > 0}">
<section class="comments">
	<c:forEach var="list" items="${pinglun.pageMsg}">
    <article class="comment">
      <a class="comment-img" href="#non">
        <img src="user.do?act=image&img=${list.pic}" alt="" width="50" height="50">
      </a>
      <div class="comment-body">
        <div class="text">
          <p>${list.content}</p>
        </div>
        <p class="attribution">by <a href="#non">${list.nickname}</a> <span>at</span> ${list.strpubtime}</p>
      </div>
    </article>
    </c:forEach>
      
  </section>
  
  <nav class="nav1">
			  <ul class="pagination" id="li01">
			  <c:if test="${pinglun.totalMsg > 0}">
			 		<c:if test="${pinglun.pageNum > 1}">
			  			<li><a href='user.do?act=querypublicriji&pageNum=${1}&publicnoteid=${note.resultMsg.publicnoteid}'>首页</a></li>
			  		</c:if>
			  		<c:if test="${pinglun.pageNum > 1}">
			  			<li ><a href='user.do?act=querypublicriji&pageNum=${pinglun.pageNum-1}&publicnoteid=${note.resultMsg.publicnoteid}'>&laquo;</a></li>
			  		</c:if>
			  	<c:forEach var="page" begin="${pinglun.startdaohang}" end="${pinglun.enddaohang}">	  		
			  		<c:choose>
			  			<c:when test="${pinglun.pageNum == page}">
			  				<li class="active"><a href='user.do?act=querypublicriji&pageNum=${page}&publicnoteid=${note.resultMsg.publicnoteid}'>${page}</a></li>
			  			</c:when>
			  			<c:otherwise>
			  				<li><a href='user.do?act=querypublicriji&pageNum=${page}&publicnoteid=${note.resultMsg.publicnoteid}'>${page}</a></li>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:forEach>
			  	    <c:if test="${pinglun.pageNum < pinglun.totalPageNum}">
			  	    	<li ><a href='user.do?act=querypublicriji&pageNum=${pinglun.pageNum+1}&publicnoteid=${note.resultMsg.publicnoteid}'>&raquo;</a></li>
			  		</c:if>
			  		<c:if test="${pinglun.pageNum < pinglun.totalPageNum}">
			  			<li><a href='user.do?act=querypublicriji&pageNum=${pinglun.totalPageNum}&publicnoteid=${note.resultMsg.publicnoteid}'>末页</a></li>
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
</c:if>

<br><br>
<c:if test="${note.resultCode == 2}">
<div class="data_list">
	<div class="data_list_title">
		发表评论
	 </div>
	<div class="container-fluid">
	  <div class="row" >
	  	<div class="col-md-12">
	  		
	  			<form class="form-horizontal" method="post" action="user.do" >
	  		   		<div class="form-group">
			    		
					    
			  		</div>
					<div class="form-group">
						<input type="hidden" name="publicnoteid" value="${note.resultMsg.publicnoteid}">
						<input type="hidden" name="act" value="savepinglun">
					  
					 </div>
			  
					  <div class="form-group">
					    <div class="col-sm-12">
					    <textarea id="container" name="content">${noteInfo.result.content}</textarea></div>
						</div>	
						<div class="form-group">
						  
					  </div>		 
					  <div class="form-group">
					    <div class="col-sm-offset-6 col-sm-4">
					      <input type="submit" class="btn btn-primary" value="发表">
					      
							<font id="error" color="red"></font>  
					    </div>
					  </div>
					  
				</form>	  		
 		
	  		</div>
	  	</div>
	</div>	
</div>	

</c:if>
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