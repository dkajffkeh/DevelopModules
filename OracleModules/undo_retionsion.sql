SHOW
PARAMETER UNDO_RETENTION; -- �ֱ�

-- ������ Ŀ���� �ڷ� ������� ORACLE SQL
SELECT *
FROM [������ ������ TABLE �̸�]
    AS OF TIMESTAMP (SYSTIMESTAMP- INTERVAL '15' MINUTE)
WHERE [������ �����͸� �ҷ����� ���ǹ� �ڸ�];


-- ������ Ŀ���� �ڷ� ������� ORACLE SQL
INSERT INTO [���������� ���̺��]
SELECT *
FROM [������ ������ TABLE �̸�]
    AS OF TIMESTAMP (SYSTIMESTAMP- INTERVAL '15' MINUTE)
WHERE [������ �����͸� �ҷ����� ���ǹ� �ڸ�];