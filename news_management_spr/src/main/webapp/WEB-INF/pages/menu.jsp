<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<div class="menu-wrapper">
	<div class="menu-title-wrapper">
		<div class="menu-title"><spring:message code="local.menu.news" /></div>
	</div>

	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: right;">
			<ul style="list-style-image: url(../resources/images/img.jpg); text-align: left;">			
				<c:url var="newsListLink" value="/newsManagement/newsList"></c:url>
				<li style="padding-left: 15px;"><a href="${newsListLink}"><spring:message code="local.menu.newslist" /></a><br />
				</li>

				<security:authorize access="hasRole('ROLE_ADMIN')">
					<li style="padding-left: 15px;">
							<c:url var="addNewsLink" value="/newsManagement/goToAddNews"></c:url>												
							<a href="${addNewsLink}"><spring:message code="local.menu.addnews" /></a><br />
					</li>
				</security:authorize>
			</ul>
		</div>
		<div class="clear"></div>
	</div>	
	<div style="height: 25px;"></div>
</div>

