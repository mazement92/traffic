INSERT INTO STATION (
	STATION_ID,
	STATION_NAME,
	REG_DTS
)
VALUES (
	'test',
	'station',
	SYSDATE
);

INSERT INTO MONTHLY_STATION (
	MONTH,
	STATION_ID,
	MONTHLY_STATION_USER,
	REG_DTS
)
VALUES (
	1,
	'test',
	10,
	SYSDATE
);

INSERT INTO MEMBER (
	MEMBER_ID,
	MEMBER_PWD,
	REG_DTS
)
VALUES (
	'tester',
	'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',
	SYSDATE
)