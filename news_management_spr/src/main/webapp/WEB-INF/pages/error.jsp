<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<p class="error">
	<c:if
		test="${sessionScope.errorNumber eq 'You do not have administrator rights!'}">
		<spring:message code="local.error.1" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'An error occurred while deleting the news!'}">
		<spring:message code="local.error.2" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'User exists, change login!'}">
		<spring:message code="local.error.3" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'Something went wrong, please try again later!'}">
		<spring:message code="local.error.4" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'An error occurred while creating the news!'}">
		<spring:message code="local.error.5" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'An error occurred while changing the news!'}">
		<spring:message code="local.error.6" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'An error occurred while fetching the news list!'}">
		<spring:message code="local.error.7" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'An error occurred while registering!'}">
		<spring:message code="local.error.8" />
	</c:if>
	<c:if
		test="${sessionScope.errorNumber eq 'An error occurred while fetching the news!'}">
		<spring:message code="local.error.9" />
	</c:if>	
</p>
	<form:form action="/news_management_spr/newsManagement/showBasePage" modelAttribute="" method="POST">		
		<input type="submit" value="<spring:message code="local.locbutton.error" />" />
	</form:form>


