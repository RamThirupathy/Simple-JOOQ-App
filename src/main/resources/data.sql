insert into users(EMAIL) values ('john@test.com'), ('joshua@test.com');

insert into courses(TITLE, DESCRIPTION, PRICE) values ('JOOQ Crash course', 'To go from beginner to expert on a popular DB framework', 10),('Spock crash course', 'To go from beginner to expert on a popular Java testing framework', 10)

insert into PURCHASES(user_id, course_id, purchased_at) values ('1', '1', now()), ('1', '2', now()), ('2', '1', now())