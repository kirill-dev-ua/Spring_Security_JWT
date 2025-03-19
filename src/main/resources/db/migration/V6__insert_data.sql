INSERT INTO roles (name)
VALUES ('ADMIN'),
       ('MANAGER'),
       ('USER');

INSERT INTO permissions (name)
VALUES ('READ'),
       ('WRITE'),
       ('CAN_BLOCK_USER'),
       ('DELETE');

INSERT INTO role_permissions (role_id, permission_id)
VALUES ((select r.id from roles r where r.name = 'ADMIN'),
        (select p.id from permissions p where p.name = 'READ')),
       ((select r.id from roles r where r.name = 'ADMIN'),
        (select p.id from permissions p where p.name = 'WRITE')),
       ((select r.id from roles r where r.name = 'ADMIN'),
        (select p.id from permissions p where p.name = 'DELETE')),
       ((select r.id from roles r where r.name = 'ADMIN'),
        (select p.id from permissions p where p.name = 'CAN_BLOCK_USER')),

       ((select r.id from roles r where r.name = 'MANAGER'),
        (select p.id from permissions p where p.name = 'READ')),
       ((select r.id from roles r where r.name = 'MANAGER'),
        (select p.id from permissions p where p.name = 'WRITE')),
       ((select r.id from roles r where r.name = 'MANAGER'),
        (select p.id from permissions p where p.name = 'DELETE')),
       ((select r.id from roles r where r.name = 'MANAGER'),
        (select p.id from permissions p where p.name = 'CAN_BLOCK_USER')),

       ((select r.id from roles r where r.name = 'USER'),
        (select p.id from permissions p where p.name = 'READ'));


--password 123456789
-- INSERT INTO users (fullname, email, password, role_id, created_at, updated_at)
-- VALUES ('admin', 'kirillAdmin@gmail.com'
--         '$2a$10$QqBL6O/DQDbdqwcAzcVUE.p9B2tJQXb6i9E9iFhAkOc2C9T/H6PPC',
--                                                         (select r.id from roles r where r.name = 'ADMIN'),
--         now(), now());

INSERT INTO users (id, full_name, email, password, created_at, updated_at, role_id, enabled)
VALUES (-2147483641, 'John Doe', 'john@example.com',
        '$2a$10$QqBL6O/DQDbdqwcAzcVUE.p9B2tJQXb6i9E9iFhAkOc2C9T/H6PPC', now(), now(),
        (select r.id from roles r where r.name = 'ADMIN'), true);

-- INSERT INTO users (username, password, role_id)
-- SELECT
--     'user' || generate_series(1, 100),
--     md5(random()::text),
--     CASE WHEN random() > 0.5 THEN (select r.id from roles r where r.name = 'ADMIN')
--         ELSE (select r.id from roles r where r.name = 'USER') END
-- FROM generate_series(1, 100);