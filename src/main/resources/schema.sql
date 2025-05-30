-- drop table if exists user;

create table if not exists users (
    userId varchar(18) not null,
    password varchar(18) not null,
    name varchar(10) not null,
    email varchar(50) not null,
    createdAt datetime default current_timestamp,
    primary key (userId)
);

CREATE TABLE IF NOT EXISTS EMPLOYEES (
    EMPLOYEE_ID NUMBER(6) PRIMARY KEY,
    FIRST_NAME VARCHAR2(20),
    LAST_NAME VARCHAR2(25) NOT NULL,
    EMAIL VARCHAR2(25) NOT NULL UNIQUE,
    PHONE_NUMBER VARCHAR2(20),
    HIRE_DATE DATE NOT NULL,
    JOB_ID VARCHAR2(10) NOT NULL,
    SALARY NUMBER(8,2),
    COMMISSION_PCT NUMBER(2,2),
    MANAGER_ID NUMBER(6),
    DEPARTMENT_ID NUMBER(4)
);