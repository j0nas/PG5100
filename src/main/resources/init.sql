INSERT INTO USER (ID, EMAIL, PASSWORD, TYPE) VALUES (NULL, 'jonas.jensen@galleriet.no', 'Passw0rd', 'STUDENT');
INSERT INTO USER (ID, EMAIL, PASSWORD, TYPE) VALUES (NULL, 'jonas.hansen@galleriet.no', 'Passw0rd', 'TEACHER');
INSERT INTO USER (ID, EMAIL, PASSWORD, TYPE) VALUES (NULL, 'jonas.svendsen@vulkan.no', 'Passw0rd', 'STUDENT');
INSERT INTO USER (ID, EMAIL, PASSWORD, TYPE) VALUES (NULL, 'jonas.ohlssen@vulkan.no', 'Passw0rd', 'STUDENT');
INSERT INTO USER (ID, EMAIL, PASSWORD, TYPE) VALUES (NULL, 'jonas.ohlssen@vaterland.no', 'Passw0rd', 'STUDENT');

INSERT INTO LOCATION (ID, BUILDING, ROOM) VALUES (NULL, 'Galleriet', 'Auditorium');
INSERT INTO LOCATION (ID, BUILDING, ROOM) VALUES (NULL, 'Vulkan', 'Auditorium');
INSERT INTO LOCATION (ID, BUILDING, ROOM) VALUES (NULL, 'Vaterland', 'Auditorium');

INSERT INTO SUBJECT (ID, NAME, FK_LOCATION) VALUES (NULL, 'PG5100', 1);
INSERT INTO SUBJECT (ID, NAME, FK_LOCATION) VALUES (NULL, 'Chakra healing', 2);
INSERT INTO SUBJECT (ID, NAME, FK_LOCATION) VALUES (NULL, 'Soul searching', 3);

INSERT INTO USR_SUB (SUBJECTS_ID, USERS_ID) VALUES (1, 1);
INSERT INTO USR_SUB (SUBJECTS_ID, USERS_ID) VALUES (2, 2);
INSERT INTO USR_SUB (SUBJECTS_ID, USERS_ID) VALUES (3, 2);
INSERT INTO USR_SUB (SUBJECTS_ID, USERS_ID) VALUES (2, 3);
