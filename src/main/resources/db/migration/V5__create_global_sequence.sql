drop sequence if exists global_id_seq cascade;

create sequence global_sequence
    start with -2147483648
    increment by 1
    minvalue -2147483648
    no cycle;

alter table permissions
    alter column id set default nextval('global_sequence');
alter table roles
    alter column id set default nextval('global_sequence');
alter table users
    alter column id set default nextval('global_sequence');

drop sequence if exists permissions_id_seq;
drop sequence if exists roles_id_seq;
drop sequence if exists users_id_seq;
