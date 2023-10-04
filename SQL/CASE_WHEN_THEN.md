```SQL
CASE
    WHEN 조건 1   
    THEN '반환값 1'   
    WHEN 조건 2    
    THEN '반환값 2'    
    ELSE '조건 1,2에 해당하지 않을 때의 반환값'   
END   
```

<br>

- WHEN과 THEN은 **한 쌍**이어야 함   
- **여러 개**의 WHEN과 THEN이 있을 수 있음   
- ELSE가 존재하면, 모든 조건에 해당하지 않을 때의 반환값을 설정할 수 있음   
- ELSE가 존재하지 않고, 모든 조건에 해당하지 않을 경우, 반환값은 **NULL**이 나옴

