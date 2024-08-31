# PIVOT vs UNPIVOT

## 데이터의 구조

### 1. LONG DATA(Tidy Data)

- 하나의 속성이 하나의 컬럼으로 정의되어 값들이 여러 행으로 쌓이는 구조
- `RDBMS` 의 테이블 설계 방식
- 다른 테이블과의 조인 연산이 가능한 구조

### 2. WIDE DATA(Cross Data)

- 행과 컬럼에 유의미한 정보 전달을 목적으로 작성하는 교차표
- 하나의 속성값이 여러 컬럼으로 분리되어 표현
- RDBMS 에서 WIDE 형식으로 테이블 설계 시 값이 추가될 때마다 컬럼이 추가되어야 하므로 비효율적이다!
- 다른 테이블과의 조인 연산이 불가하다.
- 주로 데이터를 요약할 목적으로 사용한다.

### 데이터 구조 변경

1. PIVOT : LONG -> WIDE
2. UNPIVOT : WIDE -> LONG

## PIVOT

- `교차표`를 만드는 기능
- STACK, UNSTACK, VALUE 컬럼의 정의가 중요하다!
- FROM 절에 STACK, UNSTACK, VALUE 컬럼명만 정의 필요(필요 시 서브쿼리를 사용하여 필요 컬럼 제한)
- PIVOT 절에 UNSTACK, VALUE 컬럼명 정의
- PIVOT 절 IN 연산자에 UNSTACK 컬럼 값을 정의
- FROM 절에 선언된 컬럼 중 PIVOT 절에 선언한 VALUE 컬럼, UNSTACK 컬럼을 제외한 모든 컬럼은 STACK 컬럼이 된다.

<img src="https://github.com/user-attachments/assets/8078a1c9-365e-48ca-857a-dbe74277e435" WIDTH="500" HEIGHT="300">
<img src="https://github.com/user-attachments/assets/2dc76050-4ec6-4ab6-b2f3-6bf28dd12326" WIDTH="500" HEIGHT="300">


## UNPIVOT
- WIDE 데이터를 LONG 데이터로 변경하는 문법
- STACK 컬럼 : 이미 UNSTACK 되어 있는 여러 컬럼을 하나의 컬럼으로 STACK 시 새로 만들 컬럼 이름(사용자 정의)
- VALUE 컬럼 : 교차표에서 셀 자리(VALUE) 값을 하나의 컬럼으로 표현하고자 할 때 새로 만들 컬럼명(사용자 정의)
- 값1, 값2 : 실제 UNSTACK 되어 있는 컬럼명들


<img src="https://github.com/user-attachments/assets/c2628df0-f375-452a-9e53-21f878594301" HEIGHT="300">
