SHOW PARAMETER UNDO_RETENTION; -- 최근 

-- 삭제후 커밋한 자료 살려내는 ORACLE SQL
SELECT * FROM [삭제를 진행한 TABLE 이름]
 AS OF TIMESTAMP (SYSTIMESTAMP-INTERVAL '15' MINUTE)
 WHERE [삭제한 데이터를 불러오는 조건문 자리];
 
 
 -- 삭제후 커밋한 자료 살려내는 ORACLE SQL
INSERT INTO [복구진행할 테이블명]
SELECT * FROM [삭제를 진행한 TABLE 이름]
 AS OF TIMESTAMP (SYSTIMESTAMP-INTERVAL '15' MINUTE)
 WHERE [삭제한 데이터를 불러오는 조건문 자리];