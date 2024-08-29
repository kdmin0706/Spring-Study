# TOP N QUERY

## TOP N QUERY

- 페이징 처리를 효과적으로 수행하기 위해 사용
- 전체 결과에서 특정 N 개 추출
    - EX) 성적 상위자 3명

## TOP-N 행 추출 방법

### 1. ROWNUM

- 출력된 데이터 기준으로 행 번호 부여
- 절대적인 행 번호가 아닌 가상의 번호이므로 특정 행을 지정할 수 없다. => 연산 불가
- 첫번째 행이 증가한 이후 할당되므로 '>' 연산 사용 불가(0은 가능하다)

```oracle
SELECT ROWNUM, EMP.*
FROM EMP;

SELECT EMPNO, ENAME, SAL
FROM EMP
WHERE ROWNUM <= 5;
```

### 2. RANK

<img src="https://github.com/user-attachments/assets/43ffd7a5-a51e-4646-b27c-4b6507d59a06" WIDTH="600" HEIGHT="300">

### 3. FETCH

- 출력될 행의 수를 제한하는 절
- ORACLE 12C 이상부터 제공 -> 이전에는 ROWNUM을 주로 사용
- SQL-SERVER 사용 가능
- ORDER BY 절 뒤에 사용 -> 내부 파싱 순서도 ORDER BY 뒤

<img src="https://github.com/user-attachments/assets/f717a961-9134-44f7-9438-418214e4f73f" WIDTH="600" HEIGHT="300">

- OFFSET : 건너뛸 행의 수 ex) 성적 높은 순 1등 제외
- N : 출력할 행의 수
- FETCH : 출력할 행의 수를 전달하는 구문
- FIRST : OFFSET을 쓰지 않을 때 처음부터 N 행 출력
- NEXT : OFFSET을 사용했을 경우를 제외한 행 다음부터 N 행 출력
- ROW | ROWS : 행의 수에 따라 하나일 경우 단수, 여러 값이면 복수형(특별히 구분하지 않아도 된다.)

### 4. TOP N(SQL Server)

- SQL Server 에서 상위 N 개의 추출 문법
- 서브 쿼리 사용 없이 하나의 쿼리로 정렬된 순서로 상위 N 개 출력 가능
- `WITH TIES` 를 사용하여 동순위까지 함께 출력 가능

```sql
SELECT TOP N 컬럼1, 컬럼2
FROM 테이블명
ORDER BY 정렬컬럼명;
```

> WITH TIES 적용 예시
<img src="https://github.com/user-attachments/assets/f53815b9-22e3-4675-b73e-b8783871e952" WIDTH="600" HEIGHT="300">
