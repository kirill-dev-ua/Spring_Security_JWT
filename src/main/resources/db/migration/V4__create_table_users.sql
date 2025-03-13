create table users
(
    id       bigserial,
    full_name varchar(255),
    email varchar(255),
    password varchar(255) not null,
    role_id  bigint       not null,
    created_at timestamp,
    updated_at timestamp,
    constraint pk_users primary key (id),
    constraint fk_users foreign key (role_id) references roles(id) on delete cascade
);