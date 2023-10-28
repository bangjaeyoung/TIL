## CONCAT
- 문자열 연결 및 합칠 때 사용하는 함수
- 쉼표(,)를 기준으로 합칠 문자열을 나열한다.


ex) 
```sql
SELECT
    CONCAT('/home/grep/src/', F.BOARD_ID, '/', F.FILE_ID, F.FILE_NAME, F.FILE_EXT) AS FILE_PATH

--> FILE_PATH = /home/grep/src/B0001/IMG_000001photo1.jpg
```

[관련 문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/164671)
