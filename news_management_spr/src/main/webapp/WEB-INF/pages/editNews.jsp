<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div style="color:  #353596;">
	<form:form action="editNews" modelAttribute="news" method="POST">	
	
		<p align="center"><spring:message code="local.editnews.editnews" /></p>
		<br>
		<div>				
			<p class="addNews"><form:label path = "newsTitle"><spring:message code="local.news.title" /></form:label></p>			
			<form:textarea path="newsTitle" cols="60" rows="3" id="Title"></form:textarea><br>				
			<p><form:label path = "newsBrief"><spring:message code="local.news.brief" /></form:label></p>
			<form:textarea path="newsBrief" cols="60" rows="3" id="Brief"></form:textarea><br> 			
			<p><form:label path = "newsContent"><spring:message code="local.news.content" /></form:label></p>
			<form:textarea path="newsContent" cols="60" rows="8" id="Content"></form:textarea>
			<script>
     			document.getElementById('Title').value = "${newsForEdit.newsTitle}";
     			document.getElementById('Brief').value = "${newsForEdit.newsBrief}";
     			document.getElementById('Content').value = "${newsForEdit.newsContent}";
			</script> 		
		</div>
		<br>
		<div>
			<form:hidden path="newsId" />				
			<input	type="submit" value="<spring:message code="local.locbutton.name.edit.news" />" />
			<button type="reset"><spring:message code="local.locbutton.name.clear" /></button>
		</div>
	</form:form>
</div>