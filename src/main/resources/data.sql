INSERT INTO User(name, password) VALUES('admin', 'admin');
INSERT INTO Enterprise(name, description, localization) VALUES('UPK GMBH','VAG', 'Germany');
INSERT INTO Employee(user_id, first_name, last_name, gender, age, enterprise_id) VALUES(1, 'Roman', 'Jasinski', 'man', '31', 1);
INSERT INTO Task(name, description, start_date, end_date, status, priority, open, employee_id) VALUES('Init', 'Start project', '05.01.2022',
                                                                                            '12.01.2023', 'in-work', 'LOW', 'true', 1);

