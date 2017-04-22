<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!-- 右边变化的内容 -->
<script type="text/javascript" src="statics/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		
	})
</script>
<style>
.d1{
	height: 80px;
}

.nav1{
	text-align: center;
}

</style>
<div id="d1">
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
    <div class="d1">
      <img src="user.do?act=image&img=01.jpg" width="100%" height="80px">
      </div>
      <div class="carousel-caption">
        <h3></h3>
   		 <p></p>
      </div>
    </div>
    <div class="item">
    <div class="d1">
      <img src="user.do?act=image&img=02.jpg" width="100%" height="80px">
      </div>
      <div class="carousel-caption">
        <h3></h3>
   		 <p></p>
      </div>
    </div>
    <div class="item">
    <div class="d1">
      <img src="user.do?act=image&img=03.jpg" width="100%" height="80px">
      </div>
      <div class="carousel-caption">
        ...
      </div>
    </div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
<div>
<!--视频
<object id="flash" height="550" width="100%" data-player-playerbody="flash" type="application/x-shockwave-flash" data="http://www.iqiyi.com/common/flashplayer/20160908/1052f98c2359.swf"><param name="AutoStart" value="0"><param name="quality" value="high"><param name="allowScriptAccess" value="always"><param name="wmode" value="opaque"><param name="align" value="middle"><param name="bgcolor" value="#000000"><param name="swLiveConnect" value="true"><param name="loop" value="true"><param name="play" value="true"><param name="DeviceFont" value="false"><param name="allowFullScreen" value="true"><param name="menu" value="true"><param name="flashVars" value="outsite=false&amp;components=fefff7e630&amp;autoplay=true&amp;isMember=0&amp;cyclePlay=false&amp;exclusive=0&amp;qiyiProduced=0&amp;share_sTime=&amp;share_eTime=&amp;tipdataurl=http://static.iqiyi.com/ext/common/Tipdatavod_201604111745.xml&amp;qiyiProducedPreloader=http://www.iqiyi.com/common/flashplayer/20160411/1508561520f8.swf&amp;exclusivePreloader=http://www.iqiyi.com/common/flashplayer/20160411/150821793893.swf&amp;preloader=http://www.iqiyi.com/common/flashplayer/20160425/15455aa9a57.swf&amp;vipPreloader=http://www.iqiyi.com/common/flashplayer/20160411/150884b0df09.swf&amp;adurl=http://www.iqiyi.com/common/flashplayer/20160909/am-3-42-5.swf&amp;flashP2PCoreUrl=http://www.iqiyi.com/common/flashplayer/20160909/10462a1b82aa.swf&amp;videoIsFromQidan=lianbo&amp;cpnc=ce25da37fbb1f5ee4d07deb3d9a49093&amp;cpnv=1.0&amp;playerUrl=http://www.iqiyi.com/common/flashplayer/20160908/1052f98c2359.swf&amp;parentId=flashbox&amp;albumid=&amp;albumId=&amp;channelID=27&amp;origin=flash&amp;collectionID=&amp;isSource=0&amp;pgct=1473604419469&amp;usevr=false&amp;plyct=1473604420038&amp;P00001=76FARnzEcKH9im3lU1WivewERIm16m3Tm1KPCaOxPfMyUNnyL7m3gSzAU9Eh2CHdnKy9Tktf6&amp;profileID=2203069615&amp;profileCookie=76FARnzEcKH9im3lU1WivewERIm16m3Tm1KPCaOxPfMyUNnyL7m3gSzAU9Eh2CHdnKy9Tktf6&amp;passportID=2203069615&amp;vipuser=&amp;yhls=1561621981916&amp;playerCTime=1473604420038&amp;pageCTime=1473604419469&amp;browserType=CHROME&amp;pageTmpltType=ugcalbumtplt&amp;webEventID=35b64c3d30fba21ca403686d20f02926&amp;definitionID=b0ad776954fff1f0c1ae31204ebdfe35&amp;tvId=5497810509"><div style="background-color:#232323;width:100%;height:100%;position:absolute;top:0;left:0;z-index:100;"><div style="position:absolute;left:50%;margin-left:-180px;top:50%;margin-top:-40px;"><p style="text-align:center;margin-bottom:40px;color:#fff;">主人，没有安装flash player不能播放啊~~请您<a href="http://www.adobe.com/go/getflashplayer" target="_blank" style="color:#57A900;">立即安装</a></p><p style="text-align:center;margin-bottom:40px;color:#fff;">打死也不想装flash？可以试试我们的<a href="http://mbdapp.iqiyi.com/j/ot/QIYImedia_0_08.exe" target="_blank" style="color:#57A900;">桌面客户端</a>，用过的都觉得好！</p></div></div></object>
  -->
  <div class="data_list">
	<div class="data_list_title" id="ddd"><span class="glyphicon glyphicon-edit"></span>&nbsp;公开日志 </div>
<c:choose>
 	<c:when test="${zhuyenote.totalMsg == 0 }">
 		<h3>暂无公开日志</h3>
 	</c:when>
 <c:otherwise>
<table class="table table-striped table-hover">
	<tbody>
		 <tr>
		 	<th>
		 		云记昵称
		 	</th>
		 	<th>
		 		日记主题
		 	</th>
		 	<th>
		 		发布时间
		 	</th>
		 </tr>
		 
		 	
		 		<c:forEach var="list" items="${zhuyenote.pageMsg}">
			 		<tr>
			 			<td>${list.nickname }</td>
			 			<td><a href="user.do?act=querypublicriji&publicnoteid=${list.publicnoteid}" >${list.title }</a></td>
			 			<td>${list.strpubtime }</td>
			 		</tr>			
			 	</c:forEach>
			 	 </tbody>
		</table>
			 	 <nav class="nav1">
			  <ul class="pagination" id="li01">
			  <c:if test="${zhuyenote.totalMsg > 0}">
			 		<c:if test="${zhuyenote.pageNum > 1}">
			  			<li><a href='user.do?act=zhuye&pageNum=${1}'>首页</a></li>
			  		</c:if>
			  		<c:if test="${zhuyenote.pageNum > 1}">
			  			<li ><a href='user.do?act=zhuye&pageNum=${zhuyenote.pageNum-1}'>&laquo;</a></li>
			  		</c:if>
			  	<c:forEach var="page" begin="${zhuyenote.startdaohang}" end="${zhuyenote.enddaohang}">	  		
			  		<c:choose>
			  			<c:when test="${zhuyenote.pageNum == page}">
			  				<li class="active"><a href='user.do?act=zhuye&pageNum=${page}'>${page}</a></li>
			  			</c:when>
			  			<c:otherwise>
			  				<li><a href='user.do?act=zhuye&pageNum=${page}'>${page}</a></li>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:forEach>
			  	    <c:if test="${zhuyenote.pageNum < zhuyenote.totalPageNum}">
			  	    	<li ><a href='user.do?act=zhuye&pageNum=${zhuyenote.pageNum+1}'>&raquo;</a></li>
			  		</c:if>
			  		<c:if test="${zhuyenote.pageNum < zhuyenote.totalPageNum}">
			  			<li><a href='user.do?act=zhuye&pageNum=${zhuyenote.totalPageNum}'>末页</a></li>
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

  
  <div class="data_list">
	<div class="data_list_title" id="ddd"><span class="glyphicon glyphicon-edit"></span>&nbsp;推荐福利视频:50位古装美女混剪 </div>
	<object id="flash" height="550" width="100%" data-player-playerbody="flash" type="application/x-shockwave-flash" data="http://www.iqiyi.com/common/flashplayer/20160908/1052f98c2359.swf"><param name="AutoStart" value="0"><param name="quality" value="high"><param name="allowScriptAccess" value="always"><param name="wmode" value="opaque"><param name="align" value="middle"><param name="bgcolor" value="#000000"><param name="swLiveConnect" value="true"><param name="loop" value="true"><param name="play" value="true"><param name="DeviceFont" value="false"><param name="allowFullScreen" value="true"><param name="menu" value="true"><param name="flashVars" value="outsite=false&amp;components=fefff7e630&amp;autoplay=true&amp;isMember=0&amp;cyclePlay=false&amp;exclusive=0&amp;qiyiProduced=0&amp;share_sTime=&amp;share_eTime=&amp;tipdataurl=http://static.iqiyi.com/ext/common/Tipdatavod_201604111745.xml&amp;qiyiProducedPreloader=http://www.iqiyi.com/common/flashplayer/20160411/1508561520f8.swf&amp;exclusivePreloader=http://www.iqiyi.com/common/flashplayer/20160411/150821793893.swf&amp;preloader=http://www.iqiyi.com/common/flashplayer/20160425/15455aa9a57.swf&amp;vipPreloader=http://www.iqiyi.com/common/flashplayer/20160411/150884b0df09.swf&amp;adurl=http://www.iqiyi.com/common/flashplayer/20160909/am-3-42-5.swf&amp;flashP2PCoreUrl=http://www.iqiyi.com/common/flashplayer/20160909/10462a1b82aa.swf&amp;videoIsFromQidan=lianbo&amp;cpnc=ce25da37fbb1f5ee4d07deb3d9a49093&amp;cpnv=1.0&amp;playerUrl=http://www.iqiyi.com/common/flashplayer/20160908/1052f98c2359.swf&amp;parentId=flashbox&amp;albumid=&amp;albumId=&amp;channelID=27&amp;origin=flash&amp;collectionID=&amp;isSource=0&amp;pgct=1473604419469&amp;usevr=false&amp;plyct=1473604420038&amp;P00001=76FARnzEcKH9im3lU1WivewERIm16m3Tm1KPCaOxPfMyUNnyL7m3gSzAU9Eh2CHdnKy9Tktf6&amp;profileID=2203069615&amp;profileCookie=76FARnzEcKH9im3lU1WivewERIm16m3Tm1KPCaOxPfMyUNnyL7m3gSzAU9Eh2CHdnKy9Tktf6&amp;passportID=2203069615&amp;vipuser=&amp;yhls=1561621981916&amp;playerCTime=1473604420038&amp;pageCTime=1473604419469&amp;browserType=CHROME&amp;pageTmpltType=ugcalbumtplt&amp;webEventID=35b64c3d30fba21ca403686d20f02926&amp;definitionID=b0ad776954fff1f0c1ae31204ebdfe35&amp;tvId=5497810509"><div style="background-color:#232323;width:100%;height:100%;position:absolute;top:0;left:0;z-index:100;"><div style="position:absolute;left:50%;margin-left:-180px;top:50%;margin-top:-40px;"><p style="text-align:center;margin-bottom:40px;color:#fff;">主人，没有安装flash player不能播放啊~~请您<a href="http://www.adobe.com/go/getflashplayer" target="_blank" style="color:#57A900;">立即安装</a></p><p style="text-align:center;margin-bottom:40px;color:#fff;">打死也不想装flash？可以试试我们的<a href="http://mbdapp.iqiyi.com/j/ot/QIYImedia_0_08.exe" target="_blank" style="color:#57A900;">桌面客户端</a>，用过的都觉得好！</p></div></div></object>
	</div>