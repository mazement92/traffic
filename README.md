# 나이스 평가정보 사전과제 : 서울교통공사 승하차 순위 데이터 적재 및 조회용API 개발

## 개발 환경
- Eclipse 2020-09
- Java 8
- Gradle
- Spring Boot 2.2.10
- H2 Database

## 빌드 및 실행 방법
1. java와 git 설치
2. download zip을 통해 다운로드 후 압축풀기 혹은 cmd 실행 후 [git clone git@github.com:mazement92/traffic.git] 명령을 통해 git clone, 
3. ./gradlew clean build 명령 실행시 ./build/libs 폴더에 traffic-0.0.1-SNAPSHOT.war 파일 생성
4. ./build/libs java -jar traffic-0.0.1-SNAPSHOT.war 명령으로 실행
5. 웹 브라우저를 통해 localhost:8080 접속 - 기본 회원가입/로그인 페이지

## 요구사항 및 가산점 
- API 명세서 작성

- 데이터파일(.csv)의 각 레코드를 데이터베이스에 저장하는 API 개발
  - CSVReader를 통해 csv 파일의 각 row를 읽어 [,]로 split해 알맞은 컬럼에 저장
  - https://www.data.go.kr/data/15044249/fileData.do : [서울교통공사_승하차인원_20191231.csv]와 column이 일치하는 데이터만 입력 가능
  
- 일평균 인원이 가장 많은 상위 10개의 역 명과 인원 수를 출력하는 API 개발
  - 각 역의 월별 인원수를 모두 합친 후 365로 나누어 내림차순 정렬한 뒤 상위 10개 조회
  
- 월 인원수 평균이 가장 낮은 역 명과 인원수를 출력하는 API 개발
  - 각 역의 월별 인원수의 평균을 구한 후 오름차순 정렬한 뒤 첫번째 row 조회
  
- 월 인원수 최대 최소의 차이가 가장 큰 역 명을 출력하는 API 개발
  - 월별 인원수의 최대값과 최소값을 구해 차이를 구한 후 값의 내림차순 정렬을 통해 첫번째 row 조회
  
- API 인증(Authentication) 구현
  - JWT 사용 - 로그인 시 토큰 생성하여 session에 입력
  - Interceptor 구현하여 session의 토큰 검증

## 브라우저를 통한 확인
1. localhost:8080 접속 (크롬 권장)
2. ID와 PW를 입력하고 회원가입 버튼 클릭 - 계정 생성 완료
3. ID와 PW를 입력하고 로그인 버튼 클릭 - 로그인, JWT 생성 후 세션에 저장
4. 로그인 시 데이터 등록, 조회 페이지 자동 접속 - localhost:8080/info/infoMain - 비로그인 상태에선 기본 페이지로 리다이렉트
5. csv 파일의 절대경로를 입력 후 전송 버튼 클릭 - csv 파일만 전송 가능합니다. 입력한 데이터는 다른 계정으로도 조회할 수 있습니다.
6. 각 데이터별 조회 클릭 시 내용 출력

## API DOC
**모든 기능은 웹 브라우저를 통해서 편하게 확인 가능합니다.**

```
[POST] /member/insertMember - 회원가입
  RequestBody 
  {
    "memberId" : "tester",
    "memberPwd": "123"
  }
  ResponseBody
  {
    "msg" : "회원가입이 완료되었습니다."
  }
```
```
[POST] /member/login - 로그인 (토큰 생성 후 세션에 세팅)
  RequestBody 
  {
    "memberId" : "tester",
    "memberPwd": "123"
  }
  ResponseBody
  {
    "msg" : "환영합니다."
  }
```
```
[POST] /member/logout - 로그아웃 (세션에서 토큰 제거)
```
```
[POST] /info/insertTrafficData - csv 데이터 입력 : https://www.data.go.kr/data/15044249/fileData.do [서울교통공사_승하차인원_20191231.csv] 와 같은 데이터만 입력 가능
  RequestBody 
  {
    "dataPath" : "C:\\서울교통공사_승하차인원_20191231.csv"
  }
  ResponseBody
  {
    "msg" : "288개 역에 대한 정보를 입력하였습니다."
  }
```
```
[GET]  /info/ajaxDailyAvg - 일 평균 인원 상위 10개 역 명, 인원 수 조회
  ResonseBody
  {
    [
      {
        "dailyAvgUser":202174,
        "stationName":"강남"
      },
      {
        "dailyAvgUser":170721,
        "stationName":"잠실(2)"
      },
      {
        "dailyAvgUser":167872,
        "stationName":"홍대입구"
      },
      {
        "dailyAvgUser":139189,
        "stationName":"신림"
      },
      {
        "dailyAvgUser":126034,
        "stationName":"구로디지털단지"
      },
      {
        "dailyAvgUser":121245,
        "stationName":"고속터미널(3)"
      },
      {
        "dailyAvgUser":121183,
        "stationName":"삼성"
      },
      {
        "dailyAvgUser":118081,
        "stationName":"신도림"
      },
      {
        "dailyAvgUser":111771,
        "stationName":"서울역(1)"
      },
      {
        "dailyAvgUser":105143,
        "stationName":"서울대입구"
      }
    ]
  }
```
```
[GET]  /info/ajaxMonthlyAvg - 월 인원 평균 최하위 역 명, 인원 수 조회
  ResponseBody
  {
    "stationName":"둔촌오륜",
    "monthlyAvgUser":38808
  }
```
```
[GET]  /info/ajaxMaxDiff - 월 인원 최대 최소 차이 최상위 역 명 조회
  ResponseBody
  {
    "stationName":"강남"
  }
```
