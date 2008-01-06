
-- Oracle Syntax to create the Notify_Log table
CREATE TABLE JMS_LOG (
  NOTIFY_TYPE VARCHAR2(10 CHAR) NOT NULL ENABLE,
  DESC_SHORT VARCHAR2(4000 CHAR),
  DESC_LONG CLOB,
  PROCESS_DATA CLOB,
  ERROR_REPORT CLOB,
  RETRY_IDX NUMBER(*,0),
  RETRY_OPTION VARCHAR2(4000 BYTE),
  PROCESS_ID NUMBER(*,0),
  PROJECT_NAME VARCHAR2(256 BYTE),
  ENGINE_NAME VARCHAR2(256 BYTE),
  RESTARTED_FROM_CHECKPOINT CHAR(1 CHAR),
  TRACKING_INFOS CLOB,
  CUSTOM_ID VARCHAR2(4000 BYTE),
  CREATED TIMESTAMP (6) NOT NULL ENABLE
)