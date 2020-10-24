<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>TRAFFIC TASK - INFO</title>
	</head>
	<body>
		파일 절대 경로 : 
		<input type="text" id="path" placeholder="파일 절대경로를 입력해주세요"/>
		<button type="button" onclick="insertTrafficData();">전송</button>
		</br>
		</br>
		일 평균 인원 상위 10개 - <button type="button" onclick="ajaxDailyAvg();">조회</button>
			<div id="dailyAvgArea">
			</div>
		</br>
		월 인원 평균 최하위 - <button type="button" onclick="ajaxMonthlyAvg();">조회</button>
			<div id="monthlyAvgArea">
			</div>
		</br>
		월 인원 최대 최소 차이 최상위 - <button type="button" onclick="ajaxMaxDiff();">조회</button>
			<div id="maxDiffArea">
			</div>
		</br>
		</br>
		<button type="button" onclick="logout();">로그아웃</button>
	</body>
</html>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
function insertTrafficData() {
	$.ajax({
		url: "/info/insertTrafficData",
		dataType: "json",
		contentType: "application/json",
		data:JSON.stringify({
			dataPath : $("#path").val()
		}),
        type: "POST"
	}).done(function(data) {
		alert(data.msg);
		location.reload();
    }).fail(function(request, status, error) {
    	alert(request.responseText);
    });
}

function logout() {
	$.ajax({
		url: "/member/logout",
		contentType: "application/json",
		dataType: "json",
        type: "POST"
	}).done(function(data) {
		$(location).attr("href", "/");
    }).fail(function(data) {
    	alert("로그아웃에 실패하였습니다.");
    });
}

function ajaxDailyAvg() {
	$('#dailyAvgArea').empty();
	$.ajax({
		url: "/info/ajaxDailyAvg",
		contentType: "application/json",
		dataType: "json",
        type: "GET"
	}).done(function(data) {
		if(jQuery.isEmptyObject(data)) {
			$("#dailyAvgArea").html("조회된 데이터가 없습니다.");
		} else {
			$.each(data, function(i, d) {
				$("#dailyAvgArea").append(i+1 + ". 역명 : " + d.stationName + ", 인원 수 : " + d.dailyAvgUser + "</br>");
			});
		}
    }).fail(function(data) {
    	alert("데이터 조회에 실패하였습니다.");
    });
}

function ajaxMonthlyAvg() {
	$.ajax({
		url: "/info/ajaxMonthlyAvg",
		contentType: "application/json",
		dataType: "json",
        type: "GET"
	}).done(function(data) {
		if(jQuery.isEmptyObject(data)) {
			$("#monthlyAvgArea").html("조회된 데이터가 없습니다.");
		} else {
			$("#monthlyAvgArea").html("역명: " + data.stationName + ", 인원 수 : " + data.monthlyAvgUser);
		}
    }).fail(function(data) {
    	alert("데이터 조회에 실패하였습니다.");
    });
}

function ajaxMaxDiff() {
	$.ajax({
		url: "/info/ajaxMaxDiff",
		contentType: "application/json",
		dataType: "json",
        type: "GET"
	}).done(function(data) {
		if(jQuery.isEmptyObject(data)) {
			$("#maxDiffArea").html("조회된 데이터가 없습니다.");
		} else {
			$("#maxDiffArea").html("역명: " + data.stationName);
		}
    }).fail(function(data) {
    	alert("데이터 조회에 실패하였습니다.");
    });
}
</script>