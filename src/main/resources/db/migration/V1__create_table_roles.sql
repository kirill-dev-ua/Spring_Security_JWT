CREATE TABLE roles
(
    id   BIGSERIAL,
    name VARCHAR(255) NOT NULL,
    constraint pk_roles primary key (id),
    constraint uq_roles unique (name)
);

