<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<center>
	<h4>
		<spring:message code="local.guestinfo" />
	</h4>
</center>

<div class="body-title">
	<a href=""><spring:message code="local.guestinfo.news" /></a>
	<spring:message code="local.guestinfo.latestnews" />
</div>

<c:forEach var="news" items="${listNews}" begin="0" end="4">
	<div class="single-news-wrapper">
		<div class="single-news-header-wrapper">
			<div class="news-title">
				${news.newsTitle}"
			</div>
			<div class="news-date">
				<font size="1">${news.newsDate}"</font>
			</div>

			<div class="news-content">
				${news.newsBrief}"
			</div>
		</div>
	</div>
</c:forEach>

<div class="no-news">
	<c:if test="${listNews eq null}">
		<spring:message code="local.guestinfo.nonews" />
	</c:if>
</div>



