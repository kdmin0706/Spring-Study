# DML

## DML(Data Manipulation Language)
- 데이터의 삽입(INSERT), 수정(UPDATE), 삭제(DELETE), 병합(MERGE)
- 저장(commit) 혹은 취소(rollback)이 반드시 필요하다.

### INSERT
- 테이블에 행을 삽입할 때 사용
- 한 번에 한 행만 입력 가능하다(SQL SERVER, 여러 행 동시 삽입 가능)
- 하나의 컬럼에는 한 값만 삽입 가능하다.
- 컬럼별 데이터 타입과 사이즈에 맞게 삽입
- INTO 절에 컬럼명을 명시하여 일부 컬럼만 입력 가능하다. => `작성하지 않은 컬럼은 NULL 이 입력된다.`
- 전체 컬럼에 대한 데이터 입력 시 테이블 명 뒤의 컬럼명 생략 가능하다.
```sql
INSERT INTO 테이블 VALUES (값1, 값2, ...);   -- 전체 값
INSERT INTO 테이블(컬럼1, 컬럼2, ...) VALUES (값1, 값2, ...);   -- 선택한 컬럼 값
```

### UPDATE
- 데이터를 수정할 때 사용한다.
- 컬럼 단위를 수행
- 다중 컬럼 수정 가능하다.
```sql
UPDATE 테이블명
SET 수정할 컬럼명 = 수정값
WHERE 조건;   -- WHERE 절로 수정 대상 설정
```

### DELETE
- 데이터를 삭제할 때 사용
- 행 단위로 실행
```sql
DELETE[FROM] 테이블명
[WHERE 조건]; -- WHERE 절로 삭제 행 선택 가능
```

### MERGE
- 데이터 병합
- 참조 테이블과 동일하게 맞추는 작업(참조테이블의 데이터 입력, 참조테이블의 값으로 수정 등)
- INSERT, UPDATE, DELETE 작업을 동시에 수행
```sql
MERGE INTO 수정할테이블
USING 참조테이블
ON (연결조건) -- 괄호필수
WHEN MATCHED THEN 
    UPDATE 
       SET 수정내용
WHEN NOT MATCHED THEN 
    INSERT VALUES (값1, 값2, ...);
```

