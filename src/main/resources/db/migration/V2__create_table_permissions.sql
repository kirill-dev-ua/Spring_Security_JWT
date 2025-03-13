CREATE TABLE permissions
(
    id   BIGSERIAL,
    name VARCHAR(255) NOT NULL,
    constraint pk_permissions primary key (id),
    constraint uq_permissions unique (name)
);
