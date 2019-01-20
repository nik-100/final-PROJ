<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" 
prefix="spring" %>
<html>
<head>
<title>Claim Details</title>
</head>

<body>
<h3>Claim List</h3>
<c:if test="${!empty listclaims}"/>
<table border=1>
<tr>
<th>Claim ID</th>
<th>Claim Reason</th>
<th>Claim Status</th>

<c:forEach items="${listclaims}" var="claim">
		<tr>
			<td>${claim.claimId}</td>
			<td>${claim.claimReason}</td>
			<td>${claim.claimStatus}</td>
			
			
			
			
			 <td><a href="<c:url value='/accept/${claim.claimId}' />" >Accept</a></td>
			<td><a href="<c:url value='/reject/${claim.claimId}'/>" >Reject</a>
			</td> 	 					
		</tr>
		</c:forEach>
	</table>



</body>
</html>