DROP TABLE personas IF EXISTS;
CREATE TABLE personas (
 id BIGINT IDENTITY NOT NULL PRIMARY KEY,
 first_name VARCHAR(250),
 last_name VARCHAR(250),
 company VARCHAR(250),
 job_title VARCHAR(250),
);