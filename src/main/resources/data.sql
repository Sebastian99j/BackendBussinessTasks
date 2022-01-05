INSERT INTO User(name, password) VALUES('admin', 'admin');
INSERT INTO Enterprise(user_id, description, localization) VALUES(1, 'VAG', 'Germany');
INSERT INTO Employee(first_name, last_name, gender, age, enterprise_id) VALUES('Roman', 'Jasinski', 'man', '31', 1);
INSERT INTO Task(name, description, start_date, end_date, status, priority, open, employee_id) VALUES('Init', 'Start project', '05.01.2022',
                                                                                            '12.01.2023', 'in-work', 'low', false, 1);

