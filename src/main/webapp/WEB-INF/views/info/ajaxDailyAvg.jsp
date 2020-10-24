<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<c:if test="${fn:length(resultList) == 0}">
		데이터가 없습니다.
	</c:if>
	<c:forEach items="${resultList}" var="result" varStatus="status">
		${status.count}. 역명 : ${result.stationName}, 인원 수 : ${result.dailyAvgUser}
		</br>
	</c:forEach>
</div>