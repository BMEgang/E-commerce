<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%--分页显示的开始 --%>
    	<div style="text-align:center">
    		
    		total page:${page.totalPageNum}/${page.currentPageNum}th page
    		
    		<a href="${pageContext.request.contextPath}/${page.url}&num=1">first page</a>
    		
    		<a href="${pageContext.request.contextPath}/${page.url}&num=${page.prePageNum}">last page</a>
    		
    		
    		<%--显示的页码，使用forEach遍历显示的页面 --%>
    		<c:forEach begin="${page.startPage}" end="${page.endPage}" var="pagenum">
    			<a href="${pageContext.request.contextPath}/${page.url}&num=${pagenum}">${pagenum}</a>
    		</c:forEach>
    		
    		
    		<a href="${pageContext.request.contextPath}/${page.url}&num=${page.nextPageNum}">next page</a>
    		<a href="${pageContext.request.contextPath}/${page.url}&num=${page.totalPageNum}">last page</a>
    		<input type="text" id="pagenum" name="pagenum" size="1"/><input type="button" value="go to" onclick="jump()" />
    		<script type="text/javascript">
    			function jump(){
    				var totalpage = ${page.totalPageNum};
    				var pagenum = document.getElementById("pagenum").value;
    				//判断输入的是一个数字
    				var reg =/^[1-9][0-9]{0,1}$/;
    				if(!reg.test(pagenum)){
    					//不是一个有效数字
    					alert("please input a valid number");
    					return ;
    				}
    				//判断输入的数字不能大于总页数
    				if(parseInt(pagenum)>parseInt(totalpage)){
    					//超过了总页数
    					alert("the page cannot over total page");
    					return;
    				}
    				//转向分页显示的Servlet
    				window.location.href="${pageContext.request.contextPath}/${page.url}&num="+pagenum;
    			}
    		</script>
    	</div>
    	<%--分页显示的结束--%>
