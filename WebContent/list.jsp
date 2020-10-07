<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>            
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
list.jsp<br>
<script type="text/javascript">
	function allDelete(obj){
		
		var chkobj = document.getElementsByName("rowcheck");
		var rowcnt = chkobj.length;
		
		if(obj.checked){
			for(var i=0;i<rowcnt;i++){
				chkobj[i].checked = true;
			}
		}
		else{ 
			for(var i=0;i<rowcnt;i++){
				chkobj[i].checked = false;
			}
		}
			
	} //allDelete
	
	function insert() {
		location.href = "insertForm.jsp";
	}
	
	function selectDelete() {
		var chkobj = document.getElementsByName("rowcheck");
		var flag = false;
		
		for(var i=0;i<chkobj.length;i++){
			if(chkobj[i].checked){ 
				flag = true;
			}
		}
		
		if(!flag) {
			alert("한개라도 선택");
			return;
		}
			
		document.userform.submit();
	}//selectDelete
		

</script>

<form name="userform" action="deleteAll.mv">
	<input type="button" value="삭제" onClick="selectDelete()">
	<input type="button" value="추가" onClick="insert()">
<table border="1" >
	<tr>
		<td><input type="checkbox" name="allcheck" onClick="allDelete(this)"></td>
		<td>번호</td>
		<td>아이디</td>
		<td>이름</td>
		<td>나이</td>
		<td>장르</td>
		<td>시간</td>
		<td>관객수</td>
		<td>개선사항</tD>
		<td>수정</td>	 
		<td>삭제</td>	
	</tr>
	
	<c:forEach var="mb" items="${ lists }">
		<tr>
		<td><input type="checkbox" name="rowcheck" value="${ mb.num }"></td>
		<td>${ mb.num }</td>
		<td>${ mb.id }</td>
		<td>${ mb.name }</td>
		<td>${ mb.age }</td>
		<td>${ mb.genre }</td>
		<td>${ mb.time }</td>
		<td>${ mb.partner }</td>
		<c:if test="${ mb.memo == null }">
			<td>개선할 사항 없음</td>	
		</c:if>
		<c:if test="${ ! (mb.memo == null) }">
			<td>${ mb.memo }</td>	
		</c:if>

		<td><a href="updateForm.mv?num=${ mb.num }">수정</a></td>	
		<td><a href="delete.mv?num=${ mb.num }">삭제</a></td>
	
	</c:forEach>
	
	
</table>
</form>