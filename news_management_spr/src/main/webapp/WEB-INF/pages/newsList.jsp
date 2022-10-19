<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<div class="body-title">
	<a href=""><spring:message code="local.guestinfo.news" /></a>
	<spring:message code="local.newslist" />
</div>

<form:form action="deleteNews" modelAttribute="news" method="POST">
	<c:forEach var="listNews" items="${listNews}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<c:out value="${listNews.newsTitle}" />
				</div>
				<div class="news-date">
					<font size="1"><c:out value="${listNews.newsDate}" /></font>
				</div>

				<div class="news-content">
					<c:out value="${listNews.newsBrief}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
					
						<security:authorize access="hasRole('ROLE_ADMIN')">						
						<c:url var="goToEditNewsLink" value="/newsManagement/goToEditNews">
							<c:param name="newsId" value="${listNews.newsId}" />
						</c:url>												
						<a href="${goToEditNewsLink}"><spring:message code="local.newslist.edit" />&nbsp;</a>							
						</security:authorize>		
										
						<c:url var="goToViewNewsLink" value="/newsManagement/goToViewNews">
							<c:param name="newsId" value="${listNews.newsId}"/>
						</c:url>												
						<a href="${goToViewNewsLink}"><spring:message code="local.newslist.view" />&nbsp;</a>
												
						<security:authorize access="hasRole('ROLE_ADMIN')">							
							<form:checkbox path="newsId" value="${listNews.newsId}" />
						</security:authorize>
					</div>
				</div>
			</div>
		</div>

	</c:forEach>

	<div class="no-news">
		<c:if test="${listNews eq null}">
			<p>
				<spring:message code="local.guestinfo.nonews" />
			</p>
		</c:if>
	</div>
	<div class="second-button">

		<c:if test="${not(listNews eq null)}">
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<p align="right">
					
					<form:form action="deleteNews" modelAttribute="news" method="POST">		
						<input type="hidden" name="newsId"/> 
						<input	type="submit" onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false" value="<spring:message code="local.newslist.delete" />" />
					</form:form>
				</p>
				<br />
			</security:authorize>
		</c:if>
	</div>
</form:form>

<form:form action="pagination" method="GET" sessionAttribute="paginationSize">
	<label><spring:message code="local.newslist.pagecount" />
	<select	size="1" name="paginationSize" onfocus="this.value=''">
			<option value="5">5</option>
			<option value="10">10</option>
			<option value="15">15</option>
			<option value="20">20</option>
			<option value="50">50</option>
	</select></label> 		
	<input type="submit" value="<spring:message code="local.newslist.select" />"/>	
</form:form>

<p align="center">
		<c:forEach var="i" begin="1" end="${paginationSizeForPage}">
			<label>
				<c:url var="paginationLink" value="/newsManagement/pagination">
					<c:param name="pageNumber" value="${i}" />
				</c:url>
				<a href="${paginationLink}">
				<spring:message code="local.newslist.page" /> <c:out value="${i}" /></a>&nbsp;&nbsp;
			</label>
		</c:forEach>
	</p>






