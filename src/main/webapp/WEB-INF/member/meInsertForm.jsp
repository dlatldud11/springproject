<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="./../common/common.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	int myoffset = 2;
	int mywidth = twelve - 2 * myoffset;
	int formleft = 3;
	int formright = twelve - formleft;
%>
<html>
<head>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="jquery.validate.js"></script>
<style type="text/css">
	.form-group {
		margin-bottom: 3px;
	}
	.good-result{/* 상태 양호 */
		font-size : 10pt;
		color:blue;
		font-weight: bolder;
	}
</style>
<script type="text/javascript">
		function checkDuplicateId(  ){
			var id = document.myform.id.value ;
			if( id.length == 0 ){
				alert('아이디를 입력해 주세요') ;
				document.myform.id.focus() ; 
				return false ;
			}
			var url='<%=contextPath%>/idcheck.me?id=' + id ; 
			window.open(url, 'mywin', 'height=150,width=300') ;
		}
		
		function findZipcode( ){
			var url='<%=contextPath%>/zipcheck.me';
			window.open(url, 'mywin',
				'height=600,width=720,status=yes,scrollbars=yes,resizable=no');
		}
		
	function isCheckFalse() {
		document.myform.isCheck.value = false;
	}
	function checkForm() {
		var isCheck = document.myform.isCheck.value;
		//alert( isCheck ) ;
		if (isCheck == 'false') {
			alert('아이디 중복 체크를 우선 해주세용.');
			return false;
		}
	}
</script>
</head>
<body>
	<div id="main_container" align="center"
		class="container col-xs-offset-<%=myoffset%> col-lg-offset-<%=myoffset%> col-xs-<%=mywidth%> col-lg-<%=mywidth%>">
		<h2>회원 가입 하기</h2>
		<div class="panel panel-primary sub_container">
			<div class="panel-heading" align="left">
				<font color="red"><b>회원 가입</b></font> 페이지입니다.
			</div>
			<div class="panel-body sub_container">
				<c:set var="apppath" value="<%=request.getContextPath()%>" />
				<form:form modelAttribute="member" id="myform" name="myform" class="form-horizontal" role="form"
					action="${apppath}/insert.me" method="post">
					<input type="text" name="isCheck" value="false"> 
					<input type="hidden" name="mpoint" value="5">
					<div class="form-group">
						<label for="id"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">아이디</label>
						<div class="col-xs-<%=formright - 2%> col-lg-<%=formright - 2%>">
							<form:input path="id" type="text" name="id" id="id" class="form-control"
								placeholder="아이디" value="${requestScope.bean.id}"
								onkeyup="isCheckFalse();"/> 
								<form:errors cssClass="err" path="id" />
						</div>
						<div class="col-xs-<%=2%> col-lg-<%=2%>" align="left">
							<input type="button" class="btn btn-info" value="중복 검사"
								onclick="checkDuplicateId();">
						</div>
					</div>
					<div class="form-group">
						<label for="name"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">이름</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<form:input path="name" type="text" name="name" id="name" class="form-control"
								placeholder="이름" value="${requestScope.bean.name}"/>
								<form:errors cssClass="err" path="name" />
						</div>
					</div>
					<div class="form-group">
						<label for="password"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">비밀
							번호</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<form:input path="password" type="password" name="password" id="password"
								class="form-control" placeholder="비밀 번호를 넣어 주셔요"
								value="" /> 
							<form:errors cssClass="err" path="password" />
						</div>
					</div>
					<div class="form-group">
						<label for="salary"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">급여</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<form:input path="salary" type="number" name="salary" id="salary"
								class="form-control" placeholder="급여"
								value="${requestScope.bean.salary}"/> 
								<form:errors cssClass="err" path="salary" />
						</div>
					</div>
					<div class="form-group">
						<label for="hiredate"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">입사
							일자</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<form:input path="hiredate" type="date" name="hiredate" id="hiredate"
								class="form-control datepicker" placeholder="입사 일자" value=""/>
							<form:errors cssClass="err" path="hiredate" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="gender"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">성별</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>"
							align="left">
							<label class="radio-inline"> 
							<form:radiobuttons path="gender" items="${radiolist}"
								itemLabel="mykey" itemValue="myvalue"/>
								&nbsp;&nbsp;
							</label> 
							<form:errors cssClass="err" path="gender" />
						</div>
					</div>					
					<div class="form-group">						 
						<label for="hobby" class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">취미</label>							
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>" align="left">
							<form:checkboxes path="hobby" items="${checklist}"
								itemLabel="mykey" itemValue="mykey"/>
								&nbsp;&nbsp;
							<form:errors cssClass="err" path="hobby" />
						</div>	
					</div>					

					
					<div class="form-group">
						<label for="job"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">직업</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<form:select path="job" class="form-control" name="job" id="job">
								<form:options items="${selectlist}" 
									itemLabel="mykey" itemValue="mykey"/>
							</form:select> 
							<form:errors cssClass="err" path="job" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="zipcode"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">우편
							번호</label>
						<div class="col-xs-<%=formright - 2%> col-lg-<%=formright - 2%>">
							<input type="text" name="fakezipcode" id="fakezipcode"
								class="form-control" disabled="disabled" placeholder="우편 번호"
								value="12345"> <input type="hidden" name="zipcode"
								id="zipcode" value="12345">
						</div>
						<div class="col-xs-<%=2%> col-lg-<%=2%>" align="left">
							<input type="button" class="btn btn-info" value="우편 번호 찾기"
								onclick="findZipcode();">
						</div>
						<form:errors cssClass="err" path="zipcode" />
					</div>
					<div class="form-group">
						<label for="address1"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">주소</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<input type="text" name="fakeaddress1" id="fakeaddress1"
								class="form-control" disabled="disabled" placeholder="주소"
								value="aaa"> <input type="hidden" name="address1"
								id="address1" value="aaa"> <form:errors cssClass="err" path="address1" />
						</div>
					</div>
					<div class="form-group">
						<label for="address2"
							class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">세부
							주소</label>
						<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
							<form:input path="address2" type="text" name="address2" id="address2"
								class="form-control" placeholder="세부 주소" value="삼성 아파트"/>
							<form:errors cssClass="err" path="address2" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-<%=twelve%> col-lg-<%=twelve%>" align="center">
							<button type="submit" class="btn btn-default"
								onclick="return checkForm();">
								<b>회원 가입</b>
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="reset" class="btn btn-default">초기화</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			//alert('dd') ;
			$(".datepicker").datepicker();
			$("#spanid").addClass('good-result');
		});
	</script>
</body>
</html>