<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<%-- 스프링 관련 설정 코드 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	int myoffset = 2;
	int mywidth = twelve - 2 * myoffset;
	int formleft = 3;
	int formright = twelve - formleft;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BootStrap Sample</title>
</head>
<body>
	<div class="container col-sm-offset-<%=myoffset%> col-sm-<%=mywidth%>">
		<div class="panel panel-default panel-primary">
			<div class="panel-heading">
				<h4>답글 달기</h4>
			</div>
			<div class="panel-body">
				<c:set var="apppath" value="<%=request.getContextPath()%>" />
				<form:form modelAttribute="board" class="form-horizontal" role="form"
					action="${apppath}/reply.bo" method="post">
					<input type="text" name="pageNumber"
						value="<%=request.getParameter("pageNumber")%>">
						<input type="text" name="pageSize"
						value="<%=request.getParameter("pageSize")%>"> <input
						type="text" name="groupno"
						value="<%=request.getParameter("groupno")%>"> <input
						type="text" name="orderno"
						value="<%=request.getParameter("orderno")%>">
						<input type="text" name="depth"
						value="<%=request.getParameter("depth")%>">
					<div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="writer">작성자</label>
						<div class="col-sm-<%=formright%>">
							<input type="text" class="form-control" name="fakewriter"
								id="fakewriter" placeholder="작성자"
								value="${sessionScope.loginfo.name}(${sessionScope.loginfo.id})"
								disabled="disabled"> <input type="hidden" name="writer"
								id="writer" value="${sessionScope.loginfo.id}">
								<form:errors cssClass="err" path="writer" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="subject">글
							제목</label>
						<div class="col-sm-<%=formright%>">
							<form:input path="subject" type="text" class="form-control" name="subject"
								id="subject" placeholder="글 제목"  value=""/>
								<form:errors cssClass="err" path="subject" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="password">비밀
							번호</label>
						<div class="col-sm-<%=formright%>">
							<form:input path="password" type="password" class="form-control" name="password"
								id="password" placeholder="비밀 번호를 넣어 주셔용^^"
								 value="" />
								 <form:errors cssClass="err" path="password" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="content">글
							내용</label>
						<div class="col-sm-<%=formright%>">
							<form:textarea path="content" name="content" id="content" rows="5" cols=""
								placeholder="글 내용" class="form-control" />
							<form:errors cssClass="err" path="content" />					
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="regdate">작성
							일자</label>
						<div class="col-sm-<%=formright%>">
							<form:input path="regdate" type="datetime" class="form-control" name="regdate"
								id="regdate" placeholder="작성 일자" value="" />
								<form:errors cssClass="err" path="regdate" />
						</div>
					</div>
					<div class="form-group">
						<div align="center" class="col-sm-offset-3 col-sm-6">
							<button class="btn btn-default" type="submit">답글 달기</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-default" type="reset">취소</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$('[data-toggle="tooltip"]').tooltip();
		});
	</script>

</body>
</html>