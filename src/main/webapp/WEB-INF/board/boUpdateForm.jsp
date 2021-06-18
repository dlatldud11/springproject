<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<%-- 스프링 관련 설정 코드 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
	int myoffset = 2;
	int mywidth = twelve - 2 * myoffset;
	int formleft = 3 ;
	int formright = twelve - formleft ; 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BootStrap Sample</title>
</head>
<body>
	<div class="container col-sm-offset-2 col-sm-8">
		<div class="panel panel-default panel-primary">
			<div class="panel-heading"><h4>게시물 수정</h4></div>
			<div class="panel-body">
				<c:set var="apppath" value="<%=request.getContextPath()%>" />
				<form:form modelAttribute="board" class="form-horizontal" role="form" action="${apppath}/update.bo" method="post">
					<input type="hidden" name="pageNumber" value="${param.pageNumber}">
					<input type="hidden" name="pageSize" value="${param.pageSize}">
					<input type="hidden" name="mode" value="${param.mode}">
					<input type="hidden" name="keyword" value="${param.keyword}">
					
					<div class="form-group">
						<label class="control-label col-sm-3" for="no">글번호</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="fakeno" id="fakeno"								
								   		placeholder="글번호" value="${bean.no}" disabled="disabled">
							<input type="hidden" name="no" id="no" value="${bean.no}">
						</div>
					</div>					
					<div class="form-group">
						<label class="control-label col-sm-3" for="writer">작성자</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="fakewriter" id="fakewriter"								
										placeholder="작성자" value="${bean.writer}" disabled="disabled">
							<input type="hidden" name="writer" id="writer" value="${bean.writer}">							
							<form:errors cssClass="err" path="writer" />							 
						</div>
					</div>
										
					<div class="form-group">
						<label class="control-label col-sm-3" for="subject">글 제목</label>
						<div class="col-sm-9">
							<form:input path="subject" type="text" class="form-control" name="subject" id="subject"								
								placeholder="글 제목" value="${bean.subject}" />
								<form:errors cssClass="err" path="subject" />
						</div>
					</div>					
					<div class="form-group">
						<label class="control-label col-sm-3" for="password">비밀 번호</label>
						<div class="col-sm-9">
							<form:input path="password" type="password" class="form-control" name="password"
								id="password" placeholder="비밀 번호를 넣어 주셔용^^" value="${bean.password}" />
								<form:errors cssClass="err" path="password" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="content">글 내용</label>
						<div class="col-sm-9">
							<form:textarea path="content" name="content" id="content" rows="5" cols="" 
								placeholder="글 내용" class="form-control" />
								<form:errors cssClass="err" path="content" />						
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="readhit">조회수</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="fakereadhit" id="fakereadhit"								
								placeholder="조회수" value="${bean.readhit}" disabled="disabled">
							<input type="hidden" class="form-control" name="readhit" id="readhit" value="${bean.readhit}">							
						</div>
					</div>			
					<div class="form-group">
						<label class="control-label col-sm-3" for="regdate">작성 일자</label>
						<div class="col-sm-9">
							<form:input path="regdate" type="datetime" class="form-control" name="regdate" id="regdate"								
								placeholder="작성 일자" value="${bean.regdate}" />
							<form:errors cssClass="err" path="regdate" />
						</div>
					</div>					
					<div class="form-group">
						<div align="center" class="col-sm-offset-3 col-sm-6">
							<button class="btn btn-default" type="submit">수정하기</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-default" type="reset">취소</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>