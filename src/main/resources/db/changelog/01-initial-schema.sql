--changeset my_name:01-initial-schema.sql

-- create user
CREATE USER ${DB_USERNAME} WITH PASSWORD '${DB_PASSWORD}';

-- create table
CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    short_name VARCHAR NOT NULL,
    is_lead BOOL NOT NULL DEFAULT FALSE
);

GRANT SELECT, INSERT, UPDATE, REFERENCES ON TABLE posts TO ${DB_USERNAME};

--rollback DROP TABLE posts;

-- create table
CREATE TABLE streams (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    short_name VARCHAR NOT NULL,
    lead_id INT references users(id) on delete set null on update cascade
);

GRANT SELECT, INSERT, UPDATE, REFERENCES ON TABLE streams TO ${DB_USERNAME};

--rollback DROP TABLE streams;

-- create table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    surname VARCHAR NOT NULL,
    second_name VARCHAR,
    stream_id INT references streams(id) on delete set null on update cascade,
    post_id INT references posts(id) on delete set null on update cascade,
    avatar VARCHAR
);

GRANT SELECT, INSERT, UPDATE, REFERENCES ON TABLE users TO ${DB_USERNAME};

--rollback DROP TABLE users;