INSERT INTO User(name, password) VALUES('admin', 'admin');
INSERT INTO User(name, password) VALUES('pawel', '12345');
INSERT INTO Enterprise(name, description, localization) VALUES('UPK GMBH','VAG', 'Germany');
INSERT INTO Enterprise(name, description, localization) VALUES('Cedrob','drob', 'Ciechanow');
INSERT INTO Employee(user_id, first_name, last_name, gender, age, enterprise_id) VALUES(1, 'Roman', 'Jasinski', 'man', '31', 1);
INSERT INTO Employee(user_id, first_name, last_name, gender, age, enterprise_id) VALUES(2, 'Pawel', 'Ostaszewski', 'man', '27', 2);
INSERT INTO Task(name, description, start_date, end_date, status, priority, open, completion, employee_id) VALUES('Init', 'Start project', '05.01.2022',
                                                                                            '12.01.2023', 'in-work', 'HIGH', 'true', 15, 1);
INSERT INTO Task(name, description, start_date, end_date, status, priority, open, employee_id) VALUES('App', 'Start project', '15.11.2022',
                                                                                                      '14.06.2022', 'in-work', 'HIGH', 'true', 1);

