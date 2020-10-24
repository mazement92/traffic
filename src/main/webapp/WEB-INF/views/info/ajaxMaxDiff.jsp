<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<c:choose>
		<c:when test="${empty result}">
			데이터가 없습니다.
		</c:when>
		<c:otherwise>
			역명 : ${result.stationName}
		</c:otherwise>
	</c:choose>
</div>