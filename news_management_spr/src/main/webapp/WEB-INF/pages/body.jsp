<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:if test="${sessionScope.presentation eq 'newsList' }">
	<c:import url="/WEB-INF/pages/newsList.jsp"/>
</c:if>


<c:if test="${sessionScope.presentation eq 'viewNews' }">
	<c:import url="/WEB-INF/pages/viewNews.jsp"/>
</c:if>


<c:if test="${sessionScope.presentation eq 'addNews' }">
	<c:import url="/WEB-INF/pages/addNews.jsp"/>
</c:if>


<c:if test="${sessionScope.presentation eq 'editNews' }">
	<c:import url="/WEB-INF/pages/editNews.jsp"/>
</c:if>





