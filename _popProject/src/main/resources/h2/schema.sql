CREATE TABLE MEMBER(
    user_id VARCHAR(30) PRIMARY KEY,
    pass VARCHAR(30) NOT NULL,
    email VARCHAR(255) NOT NULL,
    uname VARCHAR(100) NOT NULL,
    create_day DATE NOT NULL,
    update_day DATE,
    phone_num VARCHAR(20),
    birth DATE,
    gender VARCHAR(10),
    uevent VARCHAR(10)
);

CREATE TABLE artboard (
    pop_code VARCHAR(30) PRIMARY KEY,
    pop_name VARCHAR(200) NOT NULL,
    start_day DATE,
    end_day DATE,
    content CLOB,
    rink VARCHAR(255),
    img VARCHAR(255),
    place VARCHAR(200)
);

CREATE TABLE likelist (
    like_code INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(200),
    pop_code VARCHAR(30),
    FOREIGN KEY (user_id) REFERENCES MEMBER(user_id),
    FOREIGN KEY (pop_code) REFERENCES artboard(pop_code)
);
