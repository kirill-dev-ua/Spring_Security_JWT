create table role_permissions
(
    role_id       bigint not null,
    permission_id bigint not null,
    constraint pk_role_permissions primary key (role_id, permission_id),
    constraint fk_role_permissions1 foreign key (role_id)
        references roles (id) on delete cascade,
    constraint fk_role_permissions2 foreign key (permission_id)
        references permissions (id) on delete cascade
);