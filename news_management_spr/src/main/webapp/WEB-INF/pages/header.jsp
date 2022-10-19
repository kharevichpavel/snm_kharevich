<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="wrapper">
	<div class="newstitle"><spring:message code="local.header.newstitle" /></div>

	<div class="local-link">
		<div align="right">			
			<a href="?language=en" ><spring:message code="local.locbutton.name.en" /> </a> &nbsp;&nbsp;
			<a href="?language=by" ><spring:message code="local.locbutton.name.by" /></a> &nbsp;&nbsp; 
			<a href="?language=ru" ><spring:message code="local.locbutton.name.ru" /></a> <br />			
			<p style="height:6px"></p>					
		</div>

		<security:authorize access="hasRole('ROLE_ANONYMOUS')">
			<div align="right">
				<form:form  action="login" modelAttribute="user" method="POST">				
					
					<spring:message code="local.header.auth.login" />					
					<input name="login" class="form-control"/><br />					
					<p style="height:3px"></p> 
					<spring:message code="local.header.auth.password" />					
					<input type="password" name="password" class="form-control"/><br/>					
					<sec:csrfInput />
					<c:if test="${param.error != null}" >						
							<blink style="color: red; font-size: 1em; white-space: nowrap;"><spring:message code="local.authentication.error" /></blink>						
					</c:if>					
					<c:url var="registrationLink" value="/newsManagement/registration">
						<c:param name="presentation" value="registration" />
					</c:url>							
					<a href="${registrationLink}"><spring:message code="local.locbutton.name.registration"/></a> &nbsp;&nbsp; 					
					<input type="submit" value="<spring:message code="local.locbutton.name.sign.in"/>" /><br />
					
				</form:form>
			</div>
		</security:authorize>
		
		<security:authorize access="hasAnyRole('ROLE_GUEST', 'ROLE_ADMIN')">
			<div align="right">
				<form:form action="logout" method="POST">					
					<input type="submit" value="<spring:message code="local.locbutton.name.sign.out" />" /><br />
				</form:form>
			</div>
		</security:authorize>
		
	</div>

</div>
