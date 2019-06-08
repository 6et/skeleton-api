--liquibase formatted sql

--changeset gtrevisan:1 dbms:oracle
--comment:
--SCRIPT SQL
--rollback ALTER TABLE ENGAGEMENT_LEVEL DROP (ENLE_TX_ACRONYM);
--rollback update evaluation set EVAL_IN_IDP_REQUIRED = 'N', EVAL_IN_FEEDBACK_REQUIRED = 'N';