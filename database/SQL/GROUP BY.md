# GROUP BY

## GROUP BY 절
- 각 행을 특정 조건에 따라 `그룹으로 분리하여 계산`하도록 하는 구문식
- GROUP BY 절에 그룹을 지정할 컬럼을 전달(여러 개 전달 가능)
- 만약 그룹 연산에서 `제외`할 대상이 있다면 `미리 WHERE 절에서 해당 행을 제외`한다.
  - WHERE 절이 GROUP BY 절보다 먼저 수행되기 때문에
- 그룹에 대한 조건은 WHERE 절에서 사용할 수 없다.
- SELECT 절에 집계함수를 사용하여 그룹연산 결과를 표현한다.
- GROUP BY 절을 사용하면 데이터가 요약되므로 요약되기 전 데이터와 함께 출력할 수 없다.

```sql
SELECT * | 컬럼명 | 표현식
FROM 테이블명 또는 뷰명
WHERE 조회할 데이터 조건
GROUP BY 그룹핑컬럼명
HAVING 그룹핑 대상 필터링 조건;
```

### 잘못된 사용 예시
```sql
SELECT DEPTNO, MAX(SAL), ENAME
FROM EMP
GROUP BY DEPTNO;
```
- GROUP BY 절에 DEPTNO를 사용하면 DEPTNO가 같은 값으로 묶여서 요약
- SELECT 절에 표현 가능, 따라서 GROUP BY 칼럼과 집계 함수를 사용한 결과만이 전달 가능
- 즉, GROUP BY 절에 명시되지 않은 컬럼은 전달이 불가하다❗


