create table t_admin
(
    id        int auto_increment,
    adminName varchar(20) not null,
    password  varchar(20) null,
    constraint t_admin_adminName_uindex
        unique (adminName),
    constraint t_admin_id_uindex
        unique (id)
);

alter table t_admin
    add primary key (id);

create table t_article
(
    id           int auto_increment
        primary key,
    title        varchar(80)                            not null,
    author_id    int unsigned                           not null,
    category_id  int                                    not null,
    time         datetime default '2021-12-01 00:00:00' null,
    star         int      default 0                     null,
    comment      int      default 0                     null,
    visit        int      default 0                     null,
    content      text                                   null,
    introduction varchar(200)                           null,
    constraint author_id
        foreign key (author_id) references t_user (user_id)
            on delete cascade,
    constraint category_id
        foreign key (category_id) references t_category (id)
            on delete cascade
)
    charset = utf8;

create table t_category
(
    id           int auto_increment
        primary key,
    categoryName varchar(30) not null,
    constraint t_category_categoryName_uindex
        unique (categoryName)
)
    charset = utf8;

create table t_comment
(
    id         int auto_increment
        primary key,
    article_id int                                    null,
    nickname   varchar(30)                            null,
    content    text                                   null,
    time       datetime default '2021-12-01 00:00:00' null,
    star       int      default 0                     null,
    diss       int      default 0                     null,
    constraint article_id
        foreign key (article_id) references t_article (id)
            on delete cascade
)
    charset = utf8;

create table t_tag
(
    id  int,
    tag varchar(30) null,
    constraint t_tag_tag_uindex
        unique (tag)
)
    charset = utf8;

create index id
    on t_tag (id);

alter table t_tag
    modify id int auto_increment;

create table article_tag
(
    id          int auto_increment
        primary key,
    article_id2 int not null,
    tag_id      int not null,
    constraint article_id2
        foreign key (article_id2) references t_article (id)
            on delete cascade,
    constraint tag_id
        foreign key (tag_id) references t_tag (id)
            on delete cascade
)
    charset = utf8;

create table t_user
(
    user_id       int unsigned auto_increment comment 'primary_key'
        primary key,
    user_name     varchar(20)                       not null comment 'username',
    nickname      varchar(20)                       not null,
    user_password varchar(20)                       not null comment 'password',
    signature     varchar(100)                      null,
    avatar_img    varchar(50) default 'default.jpg' null,
    status        int         default 1             not null,
    constraint t_user_nickname_uindex
        unique (nickname),
    constraint t_user_user_name_uindex
        unique (user_name)
)
    charset = utf8;

