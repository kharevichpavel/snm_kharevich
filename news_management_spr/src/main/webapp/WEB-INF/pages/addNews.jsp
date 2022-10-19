<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<security:authorize access="hasRole('ROLE_ADMIN')">
<div style="color:  #353596;">
	<form:form action="addNews" modelAttribute="news" method="POST">
		<p align="center"><spring:message code="local.addnews.addnews" /></p>
		<br>
		<div>			
			<p class="addNews"><spring:message code="local.news.title" /></p>
			<form:textarea name="newsTitle" cols="60" rows="3" path="newsTitle"></form:textarea><br> 
			<p><spring:message code="local.news.brief" /></p>
			<form:textarea name="newsBrief" cols="60" rows="3" path="newsBrief"></form:textarea><br> 
			<p><spring:message code="local.news.content" /></p>
			<form:textarea name="newsContent" cols="60" rows="8" path="newsContent"></form:textarea>
		</div>
		<br>

		<div>
			<input type="submit" value="<spring:message code="local.locbutton.name.add.news" />" />
			<button type="reset"><spring:message code="local.locbutton.name.clear" /></button>
		</div>
	</form:form>
</div>
</security:authorize>

