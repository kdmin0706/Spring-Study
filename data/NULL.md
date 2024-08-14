# NULL

## NULL 이란?

- DBMS에서 `아직 정해지지 않은 값`을 의미한다.
- 0과 빈문자열("")과는 다른 개념이다.
- 모델 설계 시 각 컬럼별로 NULL을 허용할 지를 결정(Nullable Column)

<p>

## NULL의 특성

### 1. NULL을 포함한 연산 결과는 `항상 NULL`이다.

> 테이블

| ENAME | SAL  | COMM | SAL + COMM |
|-------|------|------|------------|
| SMITH | 800  |
| ALLEN | 1600 | 300  | 1900       |
| WARD  | 1250 | 500  | 1750       

```sql
SELECT ENAME, SAL, COMM, SAL + COMM
FROM EMP;
```
- ex) COMM 컬럼에 공백으로 보이는 것으 NULL이다.(빈 문자열일 수 있지만 데이터는 NULL)  
      이 때 NULL을 포함한 COMM과 SAL의 연산결과는 NULL이 리턴된다.

```oracle
SELECT ENAME, SAL, COMM, SAL + NVL(COMM, 0)
FROM EMP;
```

### 2. 집계함수는 NULL을 제외한 연산 결과를 리턴
- SUM,AVG,MIN,MAX 등의 함수는 항상 NULL을 무시한다.

```oracle
-- null을 포함한 집계 함수 결과(1)
SELECT COUNT(*), COUNT(SAL), COUNT(COMM)
FROM EMP;
```

```oracle
-- ex) null을 포함한 집계 함수 결과(2)
SELECT AVG(COMM), SUM(COMM) / COUNT(*)
FROM EMP;
```

- AVG 연산 결과는 NULL을 무시한 평균을 리턴하므로, NULL이 아닌 데이터의 평균을 리턴한다.
- 두 번째 수식은 평균을 직접 구한 것으로, COMM의 총 합을 총 컬럼의 갯수로 나눈 값이다.
- 따라서 위의 두 결과는 COMM이 NULL을 포함할 경우 항상 다르게 리턴된다.
- NULL을 무시한 평균을 얻고자함인지, 컬럼 전체의 평균을 계산하고자 함인지에 따라 적절하게 사용한다.
</p>


<p>

## NULL의 ERD 표기법
- IE 표기법에서는 NULL 허용 여부를 알 수 없다.
- BARKER 표기법에서는 속성 앞에 `동그라미`가 NULL의 허용 속성을 의미한다.

</p>



