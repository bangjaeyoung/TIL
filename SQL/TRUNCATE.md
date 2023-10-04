```SQL
SELECT TRUNCATE(1234.5678, 2) FROM EX;  ->  1234.56
SELECT TRUNCATE(1234.5678, 1) FROM EX;  ->  1234.5
SELECT TRUNCATE(1234.5678, 0) FROM EX;  ->  1234
SELECT TRUNCATE(1234.5678, -1) FROM EX;  ->  1230
SELECT TRUNCATE(1234.5678, -2) FROM EX;  ->  1200
```

<br>

- 숫자를 버릴 자릿수를 명시해줘서 그만큼 버리는 함수
- **양수는 소수점 아래의 값들까지**
- **0은 딱 소수점까지**
- **음수는 소수점 위의 값들까지(지워지는 수는 0으로 표기됨)**
- SQL에서는 보통 TRUNCATE 함수가 테이블을 삭제하는 용도로 사용됨 -> TRUNCATE TABLE TABLE_NAME:
- DROP TABLE은 테이블 자체를 삭제하지만, **TRUNCATE TABLE은 테이블 내의 데이터를 삭제함**
- **숫자 버림의 용도**로 사용되는 TRUNCATE 함수는 **MySQL 벤더에 한해서만** 사용할 수 있는 확장 기능임
