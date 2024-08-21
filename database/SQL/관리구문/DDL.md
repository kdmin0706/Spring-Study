# DDL

## DDL(Data Definition Language)

- 데이터 정의어
- 데이터 구조 정의(객체 생성, 삭제, 변경) 언어
- CREATE(객체 생성), ALTER(객체 변경), DROP(객체 삭제), TRUNCATE(데이터 삭제)
- AUTO COMMIT(명령어 수행하면 즉시 저장, 원복 불가)

## CREATE

- 테이블이나 인덱스와 같은 `객체를 생성`하는 명령어
- 테이블 생성 시 테이블명, 컬럼명, 컬럼 순서, 컬럼 크기, 데이터타입 등 정의 필수
- 테이블 생성 시 각 컬럼의 제약조건 및 기본값은 생략 가능하다.
- 테이블 생성 시 소유자 명시 가능(생략 시 명령어 수행 계정 소유)
- 숫자컬럼의 경우 컬럼 사이즈 생략 가능(날짜 컬럼은 사이즈 명시 x)

```sql
CREATE TABLE [소유자] 테이블명
(
    컬럼1
    데이터타입, [
    DEFAULT
    기본값] [
    제약조건],
    컬럼2
    데이터타입, [
    DEFAULT
    기본값] [
    제약조건],
    컬럼3
    데이터타입, [
    DEFAULT
    기본값]
[
    제약조건]
);
```

### 테이블 복제

```sql
CREATE TABLE 테이블명
AS
SELECT *
FROM 복제테이블명;
```

- 특징
    1. 복제테이블의 컬럼명과 컬럼의 데이터 타입이 복제된다.
    2. SELECT 문에서 컬럼 별칭 사용 시 컬럼별칭 이름으로 생성
    3. CREATE 문에서 컬럼명 변경 가능
    4. NULL 속성도 복제
    5. 테이블에 있는 제약조건, INDEX 등은 복제되지 않는다.

### 데이터타입

| 데이터타입       | 설명                                                        |    
|-------------|-----------------------------------------------------------|
| CHAR(N)     | 고정형 문자 타입으로 사이즈 전달 필수, 사이즈만큼 확정형 데이터가 입력(빈자리수는 공백으로 채워진다) |
| VARCHAR2(n) | 가변형 문자 타입으로 사이즈 전달 필수, 사이즈보다 작은 문자값이 입력되더라도 입력값 그대로 유지    |
| NUMBER(p,s) | 숫자형 타입으로 자리수 생략 가능, 소수점 자리 제한 시 s 전달(p는 총 자리수)            |
| DATE        | 날짜 타입으로 사이즈 전달 불가                                         |

> SQL Server의 경우도 유사하다. VARCHAR2 -> VARCHAR 사용, NUMBER -> NUMERIC 사용  
> SQL Server의 경우 문자타입도 사이즈 생략 가능하다.(생략 시 1)

## ALTER

- 테이블 구조 변경(컬럼명, 컬럼 데이터타입, 컬럼사이즈, DEFAULT 값, 컬럼삭제, 컬럼 추가, 제약조건)
- 컬럼 순서 변경 불가(재생성으로 해결)

### 1. 컬럼 추가

- 새로 추가된 컬럼의 위치는 맨 마지막(절대 중간 위치에 추가 불가)
- 컬럼 추가시 데이터타입 필수, DEFAULT 값, 제약조건을 명시할 수 있다.
- `여러 컬럼을 동시에 추가 가능`(반드시 괄호 사용)

```sql
-- 기본 FORM
ALTER TABLE 테이블명
    ADD 컬럼명 데이터타입 [DEFAULT] [제약조건];

-- EX)
ALTER TABLE EMP
    ADD PHONE VARCHAR(20) NOT NULL;
```

### 2. 컬럼(속성) 변경

- 컬럼 사이즈, 데이터타입, DEFAULT 값 변경가능
- 여러 컬럼 동시 변경 가능

```oracle
-- 기본 form
ALTER TABLE 테이블명
    MODIFY (컬럼명 default 값)

-- 컬럼 사이즈 변경
ALTER TABLE TEST
    MODIFY (COL_A NUMBER(10), COL_B VARCHAR(4));

-- default 값 변경
ALTER TABLE EMP
    MODIFY (SAL DEFAULT 3000);
```

1. 컬럼 사이즈 변경
    - 컬럼 사이즈 증가는 항상 가능하다.
    - 컬럼 사이즈 축소는 데이터 존재 여부에 따라 제한
    - 동시 변경이 가능하다(반드시 괄호는 필수)
2. 데이터 타입 변경
    - 빈 컬럼의 경우 데이터 타입 변경 가능
    - CHAR, VARCHAR 타입일 경우 데이터가 있어도 서로 변경 가능
3. default 값 변경
    - default 값이란 `특정 컬럼에 값이 생략될 경우(입력시 언급되지 않을 경우)` 자동으로 부여되는 값
    - INSERT 시 default 값이 선언된 컬럼에 `null`을 직접 입력할 경우 default 값이 아닌 `null`이 입력된다.
    - 이미 데이터가 존재하는 테이블에 default 값 선언 시 `기존 데이터 수정 x`(이후 입력된 데이터부터 적용)
    - default 값 해제 시 default 값을 null로 선언

### 3. 컬럼 이름 변경

- 항상 가능
- 동시 여러 컬럼의 이름 변경은 불가하다.

```oracle
ALTER TABLE TABLE_NAME RENAME COLUMN 기존컬럼명 TO 새컬럼명;
```

### 4. 컬럼 삭제

- 데이터 존재 여부와 상관없이 언제가 가능하다.
- RE-CYCLE BIN 에 남지 않는다. (FLASHBACK으로 복구 불가)
- 동시 삭제 불가

```oracle
ALTER TABLE TEST
    DROP COLUMN COL_A;
```

## DROP

- 객체(테이블, 인덱스 등) 삭제
- DROP 후에는 조회 불가하다.
- PURGE 로 테이블 삭제 시 RE-CYCLE BIN 조회 불가

```oracle
DROP TABLE TABLE_NAME [PURGE];
```

## TRUNCATE

- 구조는 남기고 데이터만 즉시 삭제, 즉시 반영(AUTO COMMIT)
- RE-CYCLE BIN 조회 불가

```oracle
TRUNCATE TABLE TABLE_NAME;
```

## DELETE/DROP/TRUNCATE 차이

- DELETE : 데이터 일부 또는 전체 삭제, ROLLBACK 가능
- TRUNCATE : 데이터 전체 삭제만 가능(일부 삭제 불가), 즉시 반영(ROLLBACK 불가)
- DROP : 데이터와 구조를 동시 삭제, 즉시 반영(ROLLBACK 불가)

## 제약조건

- 데이터 무결성을 위해 각 컬럼에 생성하는 데이터의 제약 장치
- 테이블 생성 시 정의 가능, 컬럼 추가 시 정의 가능, 이미 생성된 컬럼에 제약조건만 추가 가능

### 1. PRIMARY KEY(기본키)

- 유일한 식별자(각 행을 구별할 수 있는 식별자 기능)
- 중복 허용 X, NULL 허용 X -> UNIQUE + NOT NULL
- 특정 컬럼에 PRIMARY KEY 생성하면 NOT NULL 속성 자동 부여(CTAS 로 테이블 복사 시에는 미부여)
- 하나의 테이블에 여러 기본키를 생성할 수 없음.
- 하나의 기본키를 여러 컬럼에 결합하여 생성할 수 있음
- PRIMARY KEY 생성 시 자동으로 UNIQUE INDEX 생성

### 2. UNIQUE

- 중복을 허용하지 않는다.
- NULL은 허용한다.
- unique index 자동 생성

```oracle
CREATE TABLE TEST
(
    NO      NUMBER,
    SUBJECT VARCHAR2(20) UNIQUE
);
```

### 3. NOT NULL

- 다른 제약조건과 다르게 컬럼의 특징을 나타냄 -> CTAS 로 복제 시 따라간다.
- 컬럼 생성 시 NOT NULL을 선언하지 않으면 Nullable 컬럼으로 생성
- 이미 만들어진 컬럼에 NOT NULL 선언 시 제약조건 생성이 아닌 컬럼 수정(MODIFY)로 해결한다.

### 4. FOREIGN KEY

- 참조테이블의 참조 컬럼에 있는 데이터를 확인하면서 본 테이블 데이터를 관리할 목적으로 생성
- 반드시 참조(부모)테이블의 참조 컬럼(REFERENCE KEY)이 사전에 PK 혹은 UNIQUE KEY를 가져야한다.
- FOREIGN KEY 옵션(생성 시 정의, 변경 불가 -> 재생성)
    - ON DELETE CASCADE : 부모 데이터 삭제 시 자식 데이터 함께 삭제
    - ON DELETE SET NULL : 부모 데이터 삭제 시 자식 데이터의 참조값은 NULL로 수정

### 5. CHECK

- 직접적으로 데이터의 값 제한(양수, (1,2,3,4) 중 하나)

```oracle
ALTER TABLE TEST
    ADD CONSTRAINT TEST_SAL CHECK ( SAL > 0 );
```

## 기타 오브젝트

### 1. 뷰(VIEW)

- 저장 공간을 가지지는 않지만 테이블처럼 조회 및 수정할 수 있는 객체
- 종류
    1. 단순 뷰 : 하나의 테이블 조회 뷰
    2. 복합 뷰 : 둘 이상의 테이블 조회 뷰
- 특징
    1. 뷰(VIEW)는 기본테이블로부터 유도된 테이블이기에 기본 테이블과 같은 형태의 구조를 가지고 있으며,
       조작도 기본 테이블과 거의 같다.
    2. 뷰는 가상의 테이블이기에 `물리적으로 구현`되어 있지 않음으로 `저장공간을 차지하지 않는다.`
    3. 데이터를 안전하게 보호 가능하다.
    4. 이미 정의되어 있는 뷰는 다른 뷰의 정의에 기초가 될 수 있다.
    5. 기본 테이블이 삭제되면 그 테이블을 참조하여 만든 뷰 역시 삭제된다.
- 장점
    1. 논리적 독립성 제공
    2. 데이터의 접근을 제어함으로써 보안 유지
    3. 사용자의 데이터 관리 단순화
    4. 데이터의 다양한 지원 가능
- 단점
    1. 뷰의 정의 변경 불가
    2. 산입,삭제, 갱신 연산 제한
    3. 인덱스 구성 불가

```oracle
CREATE
[OR REPLACE] VIEW 뷰이름
       AS 쿼리;

DROP VIEW 뷰이름; -- 삭제
```

### 2. 시퀀스(SEQUENCE)

- 자동으로 연속적인 숫자를 부여해주는 객체


### 3. 시노님(SYNONYM)
- 테이블 별칭 생성
  - EX) HR 계정에서 SCOTT.EMP를 EMP로 조회하는 방법
```oracle
CREATE [OR REPLACE] [PUBLIC] SYNONYM 별칭 FOR 테이블명; 
```