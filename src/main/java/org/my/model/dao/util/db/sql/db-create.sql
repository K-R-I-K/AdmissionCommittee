/*DROP database IF EXISTS admissionCommittee;

CREATE database admissionCommittee;*/

DROP TABLE applicant_faculty;
DROP TABLE faculties;
DROP TABLE users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(50),
    password VARCHAR,
    role VARCHAR(50),

    name VARCHAR(50),
    email VARCHAR(50),
    city VARCHAR(50),
    region VARCHAR(50),
    educational_institution VARCHAR(50)
);

CREATE TABLE faculties (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE,
    budget_places INTEGER,
    total_places INTEGER
);

CREATE TABLE applicant_faculty (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users ON DELETE CASCADE,
    faculty_id INTEGER REFERENCES faculties ON DELETE CASCADE,
    mark1 INTEGER,
    mark2 INTEGER,
    mark3 INTEGER,
    avg_certificate_mark REAL,
    passed BOOLEAN
);

INSERT INTO users VALUES (DEFAULT, 'login1', 'bc547750b92797f955b36112cc9bdd5cddf7d0862151d03a167ada8995aa24a9ad24610b36a68bc02da24141ee51670aea13ed6469099a4453f335cb239db5da',
                          'APPLICANT', 'user1', 'email1@gmail.com', 'city1', 'region1', 'institution1');
INSERT INTO users VALUES (DEFAULT, 'login2', '92a891f888e79d1c2e8b82663c0f37cc6d61466c508ec62b8132588afe354712b20bb75429aa20aa3ab7cfcc58836c734306b43efd368080a2250831bf7f363f',
                          'APPLICANT', 'user2', 'email2@gmail.com', 'city2', 'region2', 'institution2');
INSERT INTO users VALUES (DEFAULT, 'login3', '2a64d6563d9729493f91bf5b143365c0a7bec4025e1fb0ae67e307a0c3bed1c28cfb259fc6be768ab0a962b1e2c9527c5f21a1090a9b9b2d956487eb97ad4209',
                          'APPLICANT', 'user3', 'email3@gmail.com', 'city3', 'region3', 'institution3');
INSERT INTO users VALUES (DEFAULT, 'admin', '33275a8aa48ea918bd53a9181aa975f15ab0d0645398f5918a006d08675c1cb27d5c645dbd084eee56e675e25ba4019f2ecea37ca9e2995b49fcb12c096a032e',
                          'ADMIN', 'admin1', 'admin_email1@gmail.com', 'admin_city1', 'admin_region1', 'admin_institution1');
INSERT INTO faculties VALUES (DEFAULT, 'faculty1', 10, 100);
INSERT INTO faculties VALUES (DEFAULT, 'faculty2', 20, 200);
INSERT INTO faculties VALUES (DEFAULT, 'faculty3', 30, 300);

INSERT INTO applicant_faculty VALUES (DEFAULT, 1, 1, 200, 200, 200, 10.75, 'false');
INSERT INTO applicant_faculty VALUES (DEFAULT, 2, 2, 150, 150, 150, 8.9, 'false');
INSERT INTO applicant_faculty VALUES (DEFAULT, 3, 3, 175, 150, 200, 11.8, 'false');

SELECT * FROM users;
SELECT * FROM faculties;
SELECT * FROM applicant_faculty;
