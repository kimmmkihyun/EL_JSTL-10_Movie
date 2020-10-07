<%@page import="myPkg.MovieBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>            
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>       
<style type="text/css">
	td : first-child {
		width:180px;
	}
</style>

updateForm.jsp<br><br>
  
<%
	MovieBean mb = (MovieBean)request.getAttribute("mb");
	
%>  
   
	<h2>영화 선호도 조사 </h2>
	<form action="updateProc.mv" method="post">
	<input type="hidden" name="num" value="${ mb.num }">
		<table border="1" width="700px">
		<tr>
			<td>아이디</td>
			<td> 
				${ mb.id }
			</td>  
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="${ mb.name }"></td> 
		</tr> 
		<tr>
			<td>나이</td>
			<td><input type="text" name="age" value="${ mb.age }"></td> 
		</tr>
		<tr>
			<td>좋아하는 장르</td>
			<td>
			
				<input type="checkbox" name="genre" value="공포" <c:if test="${ fn:contains(mb.genre,'공포') }"> checked </c:if> >공포
				<input type="checkbox" name="genre" value="다큐" <c:if test="${ fn:contains(mb.genre,'다큐') }"> checked </c:if> >다큐 
				<input type="checkbox" name="genre" value="액션" <c:if test="${ fn:contains(mb.genre,'액션') }"> checked </c:if> >액션 
				<input type="checkbox" name="genre" value="애니메이션" <c:if test="${ fn:contains(mb.genre,'애니메이션') }"> checked </c:if> >애니메이션
			 
			</td>
			
		</tr>

		<tr>
			<td>즐겨보는 시간대</td>
			<td>
				<select name="time">
				
					<option value="08~10" <c:if test="${ mb.time eq '08~10'}">selected </c:if>>08~10</option>
					<option value="10~12" <c:if test="${ mb.time eq '10~12'}">selected </c:if>>10~12</option>
					<option value="12~14" <c:if test="${ mb.time eq '12~14'}">selected </c:if>>12~14</option>
					<option value="14~16" <c:if test="${ mb.time eq '14~16'}">selected </c:if>>14~16</option>
					<option value="16~18" <c:if test="${ mb.time eq '16~18'}">selected </c:if>>16~18</option>
					<option value="18~20" <c:if test="${ mb.time eq '18~20'}">selected </c:if>>18~20</option>
				
			 
				</select>
			</td> 
		</tr>
		
		<tr>
			<td>동반 관객수</td>
			<td> 
			 
				<input type="radio" name="partner" value="1"  <c:if test="${ mb.partner == 1 }"> checked </c:if> >1
				<input type="radio" name="partner" value="2"  <c:if test="${ mb.partner == 2 }"> checked </c:if> >2 
				<input type="radio" name="partner" value="3"  <c:if test="${ mb.partner == 3 }"> checked </c:if> >3 
				<input type="radio" name="partner" value="4"  <c:if test="${ mb.partner == 4 }"> checked </c:if> >4
			
			</td>
		</tr>
		<tr>
			<td>영화관 개선사항</td>
			<td>
				<textarea name="memo" cols="40" rows="3"><%= mb.getMemo() %></textarea> 
			</td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<input type="submit" value="수정" id="sub">
			</td>
		</tr>
		</table>
	</form>
