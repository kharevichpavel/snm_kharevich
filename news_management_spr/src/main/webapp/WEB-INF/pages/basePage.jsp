<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
    Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/validation.js"></script>
<title>basePage</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/newsStyle.css"/>" />
</head>

<body>
	<div class="page">
	
		<div class="header">
			<c:import url="/WEB-INF/pages/header.jsp" />			
		</div>

		<div class="base-layout-wrapper">

			<div class="menu">
				<security:authorize access="hasRole('ROLE_ANONYMOUS')">
					<c:if test="${requestScope.presentation eq 'registration' }">
						<c:import url="/WEB-INF/pages/registration.jsp" />
					</c:if>
				</security:authorize>
				<security:authorize access="hasAnyRole('ROLE_GUEST', 'ROLE_ADMIN')">
					<c:import url="/WEB-INF/pages/menu.jsp" />
				</security:authorize>
			</div>

			<div class="content">
				<security:authorize access="hasRole('ROLE_ANONYMOUS')">					
						<c:import url="/WEB-INF/pages/guestInfo.jsp" />					
				</security:authorize>

				<security:authorize access="hasAnyRole('ROLE_GUEST', 'ROLE_ADMIN')">					
						<c:import url="/WEB-INF/pages/body.jsp" />					
				</security:authorize>
			</div>

		</div>

		<div class="footer">
			<c:import url="/WEB-INF/pages/footer.jsp" />
		</div>

	</div>
</body>
</html>