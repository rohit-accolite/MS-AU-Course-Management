CREATE TABLE users (
    user_id int AUTO_INCREMENT,
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    date_of_joining varchar(255),
    PRIMARY KEY (user_id) 
);

CREATE TABLE courses (
    course_id int AUTO_INCREMENT,
    course_name varchar(255),
    course_description varchar(255),
    prerequisite varchar(255),
    created_on varchar(255),
    last_modified_on varchar(255),
    user_id int,
    PRIMARY KEY (course_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
	ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE skills (
	skill_id INT AUTO_INCREMENT,
	course_id INT,
	skill_name VARCHAR(255),
	PRIMARY KEY (skill_id),
	FOREIGN KEY (course_id) REFERENCES courses(course_id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
);

CREATE TABLE feedbacks (
    feedback_id int AUTO_INCREMENT,
    course_id int,
    participant_name varchar(255),
    feedback_text varchar(255),
    rating int,
    created_on varchar(255),
    PRIMARY KEY (feedback_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
	ON DELETE CASCADE
    ON UPDATE NO ACTION
);

CREATE TABLE materials (
    material_id int AUTO_INCREMENT,
    course_id INT,
    parent_id INT default 0,
    isCurrent boolean default true,
    file_name varchar(255),
    file_type varchar(255),
    file_data longblob,
    created_on varchar(255),
    PRIMARY KEY (material_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
	ON DELETE CASCADE
    ON UPDATE NO ACTION
);
