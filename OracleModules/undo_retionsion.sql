SHOW
PARAMETER UNDO_RETENTION;
-- UNDO_RETENTION 은 최근 15분동안 진행된 commit delete 등의 xecute 정보를 담고 있음

--  ORACLE SQL
SELECT *
FROM [복구가 필요한 TABLE 이름]
AS OF TIMESTAMP
(SYSTIMESTAMP- INTERVAL '15' MINUTE)
WHERE [DELETE 조건을 걸어 놓았던 조건문 삽입 /*WHERE USER_NAME = '이영현'*/];
/* WHERE USER_NAME = '이영현' */



-- 삭제된 데이터에 대한 INSERT 진행 ORACLE SQL
INSERT INTO [복구하고자 하는 테이블 이름 입력]
SELECT *
FROM [삭제가 진행되었던 테이블을 입력함]
AS OF TIMESTAMP
(SYSTIMESTAMP- INTERVAL '15' MINUTE)
WHERE [DELETE 조건을 걸어 놓았던 조건문 삽입 /*WHERE USER_NAME = '이영현'*/];

