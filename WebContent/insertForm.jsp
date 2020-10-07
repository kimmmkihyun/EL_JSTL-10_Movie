<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
	td:first-child{
		width:150px;
	}
</style>


<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>  js폴더를 가져다 놓지 않을 경우-->
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
   
   var idmiss = false; 
   
   $(document).ready(function() { 
	var isCheck=false;
	var use; 
	var isChange = false;
	
	
    $("#id_check").click(function() {   
    	idmiss = false;
    	isCheck=true;
        $.ajax({
         url: "id_check_proc.mv",
         data : ({
        	 userid: $("input[name=id]").val()
         }), 
      
         success: function (data){ 
         	
        	//alert("alert:"+data+"/"+data.length); 
         	
       		if ($('input[name="id"]').val().trim()==""){
         	   $('#idmessage').html("<font color=red>id 입력 누락</font>");
                $('#idmessage').show();
               
            }
        	 
        	 
       		else if (jQuery.trim(data)=='YES'){
        	  
               $('#idmessage').html("<font color=red>사용 가능합니다.</font>");
               $('#idmessage').show(); 
      			
              use = "possible";
              isChange = false;
              
            }else{
	            $('#idmessage').html("<font color=red>이미 사용중인 아이디입니다.</font>");
	            $('#idmessage').show();                
	            use = "impossible";
            }
           } // success의 끝
        });//ajax()의 끝
     }); // click()의 끝
    
    $("input[name=id]").keydown(function() {
    	
    	isChange = true;
    	use="";
    	$('#idmessage').css("display","none");
    	return;
	}); 
    
    $("#sub").click(function(){ 
    	    
    	if(idmiss == true){
    		alert("id누락");
    		return false;
    	}
    	
    	else if(use=="impossible"){
    		alert("이미 사용중인 아이디입니다.");
    		return false;
    	}
    	else if(idmiss == true){    		
    		alert("idmiss == true");
    		idmiss = false;
    		return false;
    	}
    	else if(isCheck==false ||isChange == true ){
			alert("중복체크를 하세요");
			return false;
    	}
    	
		isCheck=true;
		
		
    })
   }); // ready()의 끝
   
</script>
 

<%
	application.setAttribute("flag", "false");
%>

insertForm.jsp<br><br>
  
	<h2>영화 선호도 조사 </h2>
	<form action="insertProc.mv" method="posst">
		<table border="1" width="700px">
		<tr>
			<td>아이디</td>
			<td>
				<input type="text" name="id" value="IU">
				<button type="button" id="id_check">중복체크</button>
				<span id="idmessage" style="display:none">가나다라</span>
			</td>  
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="아이유"></td> 
		</tr> 
		<tr>
			<td>나이</td>
			<td><input type="text" name="age" value="23"></td> 
		</tr>
		<tr>
			<td>좋아하는 장르</td>
			<td>
			
				<input type="checkbox" name="genre" value="공포">공포
				<input type="checkbox" name="genre" value="다큐">다큐 
				<input type="checkbox" name="genre" value="액션">액션 
				<input type="checkbox" name="genre" value="애니메이션">애니메이션
			
			</td>
			
		</tr>

		<tr>
			<td>즐겨보는 시간대</td>
			<td>
				<select name="time">
				
					<option value="08~10">08~10</option>
					<option value="10~12">10~12</option>
					<option value="12~14">12~14</option>
					<option value="14~16">14~16</option>
					<option value="16~18">16~18</option>
					<option value="18~20">18~20</option>
				
				</select>
			</td> 
		</tr>
		
		<tr>
			<td>동반 관객수</td>
			<td> 
			
				<input type="radio" name="partner" value="1">1
				<input type="radio" name="partner" value="2">2 
				<input type="radio" name="partner" value="3">3 
				<input type="radio" name="partner" value="4">4
			
			</td>
		</tr>
		
		<tr>
			<td>영화관 개선사항</td>
			<td>
				<textarea name="memo" cols="40" rows="3">value는 여기에 넣기</textarea> 
			</td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<input type="submit" value="가입하기" id="sub">
			</td>
		</tr>
		</table>
	</form>
