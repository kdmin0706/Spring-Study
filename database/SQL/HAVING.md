# HAVING

## HAVING 절
- 그룹 함수 결과를 조건으로 사용할 때 사용하는 절이
- WHERE 절을 사용하여 그룹을 제한할 수 없으므로 HAVING 절에 전달
- HAVING 절이 GROUP BY 절 앞에 올 수는 있지만 뒤에 쓰는 것을 권장
- 내부적 연산 순서가 SELECT 절보다 먼저이므로 `SELECT 절에서 선언된 Alias 사용 불가`

### 예시

1. 올바른 사용
```sql
SELECT DEPARTMENT_ID,
       SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPARTMENT_ID
HAVING SUM(SALARY) > 20000;
```
2. 잘못된 사용
```sql
SELECT DEPARTMENT_ID,
       SUM(SALARY)
FROM EMPLOYEE
WHERE SUM(SALARY) > 20000
GROUP BY DEPARTMENT_ID;
```

3. WHERE + HAVING 절
```sql
SELECT DEPARTMENT_ID,
       SUM(SALARY)
FROM EMPLOYEE
WHERE DEPARTMENT_ID IN (10, 30, 40, 100)
GROUP BY DEPARTMENT_ID
HAVING SUM(SALARY) > 20000;
```
- 순서상 WHERE 절을 먼저 수행한다.
- 원하는 데이터만 필터링한 후 GROUP BY에 의해 그룹연산을 수행한 뒤,  
  HAVING 절에서 만족하는 데이터만 선택하여 출력한다.
