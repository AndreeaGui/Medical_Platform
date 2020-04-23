INSERT INTO users (id, birth_date, gender, name, password, role, username) VALUES
('3257a3f9261a4de187c0a129e27ca0a2', '2019-09-04', 'FEMALE', 'Lexy Gray', '123', 'DOCTOR', 'doctor'),
('5257a3f9261a4de187c0a129e27ca0a2', '2019-09-04', 'MALE', 'George Smith', '123', 'CAREGIVER', 'caregiver'),
('8257a3f9261a4de187c0a129e27ca0a2', '2019-09-04', 'FEMALE', 'Ela Dumb', '123', 'PATIENT', 'patient');

INSERT INTO doctor(id) VALUES ('3257a3f9261a4de187c0a129e27ca0a2');
INSERT INTO caregiver(id) VALUES ('5257a3f9261a4de187c0a129e27ca0a2');

INSERT INTO patient(id, caregiver_id, doctor_id, medical_record) VALUES
('8257a3f9261a4de187c0a129e27ca0a2', '5257a3f9261a4de187c0a129e27ca0a2','3257a3f9261a4de187c0a129e27ca0a2', 'Cancer' );
