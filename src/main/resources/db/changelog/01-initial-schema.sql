--liquibase formatted sql
--changeset giro77:01-initial-schema.sql

-- create user
drop user if exists ${DB_USERNAME};
create user ${DB_USERNAME} with password '${DB_PASSWORD}';

-- create table
create table posts (
    id VARCHAR primary key,
    name VARCHAR not null,
    short_name VARCHAR not null,
    is_lead BOOL not null default false
);

-- grant table permissions
grant select, insert, update, references on table posts to ${DB_USERNAME};

--rollback DROP TABLE posts;

-- create table
create table streams (
    id VARCHAR primary key,
    name VARCHAR not null,
    short_name VARCHAR not null,
    lead_id VARCHAR
);

-- grant table permissions
grant select, insert, update, references on table streams to ${DB_USERNAME};

--rollback DROP TABLE streams;

-- create table
create table users (
    id VARCHAR primary key,
    name VARCHAR not null,
    surname VARCHAR not null,
    second_name VARCHAR,
    stream_id VARCHAR references streams (id) on delete set null on update cascade,
    post_id VARCHAR references posts (id) on delete set null on update cascade,
    avatar VARCHAR
);

alter table streams
add constraint streams_lead_fk foreign key (lead_id) references users (id) on delete set null on update cascade;

-- grant table permissions
grant select, insert, update, references on table users to ${DB_USERNAME};

--rollback DROP TABLE users;