CREATE TABLE user_roles (
    id INTEGER PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    display_name VARCHAR(50) NOT NULL
);

CREATE TABLE employees (
    emp_number INTEGER PRIMARY KEY,
    employee_id VARCHAR(50) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100) DEFAULT '',
    last_name VARCHAR(100) NOT NULL,
    termination_id INTEGER
);

CREATE TABLE users (
    id INTEGER PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL UNIQUE,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    emp_number INTEGER NOT NULL REFERENCES employees(emp_number),
    user_role_id INTEGER NOT NULL REFERENCES user_roles(id)
);