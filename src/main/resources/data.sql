TRUNCATE TABLE students RESTART IDENTITY CASCADE;
TRUNCATE TABLE courses RESTART IDENTITY CASCADE;
TRUNCATE TABLE student_enrollments RESTART IDENTITY CASCADE;

INSERT INTO students (student_id, name, email, enrolled) VALUES
                                                            (1, 'John Smith', 'john.smith@university.edu', true);


INSERT INTO courses (course_id, course_name) VALUES
                                                (101, 'Introduction to Computer Science');


INSERT INTO student_enrollments (enrollment_id, student_id, course_id, is_completed, grade) VALUES
                                                                                                (1001, 1, 101, true, 95.5);