<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="body-title">
	<a href=""><spring:message code="local.guestinfo.news" /></a> <spring:message code="local.viewnews" />
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text"><spring:message code="local.news.title" /></td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${newsForEdit.newsTitle}" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><spring:message code="local.news.date" /></td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${newsForEdit.newsDate}" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><spring:message code="local.news.brief" /></td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${newsForEdit.newsBrief}" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><spring:message code="local.news.content" /></td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${newsForEdit.newsContent}" />
				</div></td>
		</tr>
	</table>
</div>


<security:authorize access="hasRole('ROLE_ADMIN')">
<div class="first-view-button">
	<form:form action="goToEditNews" modelAttribute="news" method="POST">
		<input type="hidden" name="newsId" value="${newsForEdit.newsId}" />
		
		<input type="submit" value="<spring:message code="local.locbutton.name.edit.news" />" />
	</form:form>
</div>
<br><br>
<div class="second-view-button">
	<form:form action="deleteNews" modelAttribute="news" method="POST">		
		<input type="hidden" name="newsId" value="${newsForEdit.newsId}" /> 
		<input	type="submit" onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false" value="<spring:message code="local.locbutton.name.delete.news" />" />
	</form:form>
</div>
</security:authorize>
