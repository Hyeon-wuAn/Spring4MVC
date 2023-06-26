-- 회원 테이블
CREATE TABLE member (
    mno             int             auto_increment,
    userid          VARCHAR(100)    unique,
    passwd          VARCHAR(100)    not null,
    name            VARCHAR(10)     NOT NULL,
    email           VARCHAR(40)     not NULL,
    regdate         datetime        default current_timestamp,
    primary key (mno)
);

-- 게시판 테이블
create table board (
    bno         int             auto_increment,
    title       varchar(100)    not null,
    userid      varchar(18)     not null,
    regdate     datetime        default current_timestamp,
    views       int             default 0,
    contents    text            not null,
    primary key (bno),
    foreign key (userid) references member(userid)
);

