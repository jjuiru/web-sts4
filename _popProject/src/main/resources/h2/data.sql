INSERT INTO artboard (pop_code, pop_name, start_day, end_day, content, img, rink, place)
VALUES 
    ('a01', 'TWS PopUp', '2024-03-27', '2024-04-28', '청량돌 TWS 1집 기념 팝업 스토어', NULL, 'https://youtu.be/u_V1rvUQpwY','여의도 더현대서울'),
    ('a02', 'Plave PopUp', '2024-04-27', '2024-05-28', '버추얼돌 1위 기념 팝업 스토어', NULL, 'https://youtu.be/u_V1rvUQpwY','여의도 더현대서울');

INSERT INTO member (user_id, pass, email, uname, create_day, update_day, phone_num, birth, gender, uevent)
VALUES
    ('admin', 'admin', 'admin@admin.com', '어드민', NOW(), NULL, '01077603956', '1993-06-05', 'm', 'on'),
    ('aaa', 'aaa', 'aaa@aaa.com', 'aaa', NOW(), NULL, '01012345678', '1995-01-01', 'f', 'off');
