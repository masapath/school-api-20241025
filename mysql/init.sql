CREATE TABLE IF NOT EXISTS Teachers (
    teacher_id INT PRIMARY KEY,
    teacher_name VARCHAR(100) NOT NULL
);

CREATE TABLE  IF NOT EXISTS Classes (
    class_id INT PRIMARY KEY,
    class_name VARCHAR(100) NOT NULL,
    teacher_id INT,
    FOREIGN KEY (teacher_id) REFERENCES Teachers(teacher_id)
);

CREATE TABLE  IF NOT EXISTS Students (
    student_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    login_id VARCHAR(100) NOT NULL,
    class_id INT,
    FOREIGN KEY (class_id) REFERENCES Classes(class_id)
);

-- データの挿入
INSERT INTO Teachers (teacher_id, teacher_name) VALUES
(1, '教師A'),
(2, '教師B');

INSERT INTO Classes (class_id, class_name, teacher_id) VALUES
(1, 'クラスA', 1),
(2, 'クラスB', 2),
(3, 'クラスC', 1);

INSERT INTO Students (student_id, name, login_id, class_id) VALUES
(1, '佐藤', 'foo123', 1),
(2, '鈴木', 'bar456', 2),
(3, '田中', 'baz789', 3),
(4, '加藤', 'hoge0000', 1),
(5, '太田', 'fuga1234', 2),
(6, '佐々木', 'piyo5678', 3),
(7, '小田原', 'fizz9999', 1),
(8, 'Smith', 'buzz777', 2),
(9, 'Johnson', 'fizzbuzz#123', 3),
(10, 'Williams', 'xxx:42', 1);
