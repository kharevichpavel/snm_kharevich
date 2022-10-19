<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="registration">
	<form:form action="doRegistration" modelAttribute="user" method="POST">
		<p align="center"><spring:message code="local.registration.title" /></p><br/>
		<div>
			<spring:message code="local.registration.email" /><br/>
			<form:input placeholder="Example: one@gmail.com" path="email" required="true" size="30px"/><br/> 
			<spring:message code="local.registration.tel" /><br/>
			<form:input placeholder="Example: +375291111111" path="telephone" required="true" size="30px" pattern="(\+)([0-9]){12}"/><br/> 			
			<spring:message code="local.registration.login" /><br/>
			<form:input path="login" required="true" size="30px"/><br/> 			
			<spring:message code="local.registration.password" /><br/>
			<form:input path="password" required="true" size="30px"/>
		</div><br/>
		<div>			
			<input type="submit" value="<spring:message code="local.registration.register" />" />
			<input type="hidden"
							   name="${_csrf.parameterName}"
							   value="${_csrf.token}" />
			<button type="reset"><spring:message code="local.locbutton.name.clear" /></button>
		</div>
	</form:form>
</div>
