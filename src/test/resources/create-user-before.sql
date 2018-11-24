delete from user_role;
delete from usr;

insert into usr(id, active, password,username) value
(1, true, '$2a$08$uP6Rtaz2anOuc/989c.v3etAICDNcsYi7Fo37PGHkfrBxo6m4IPqO', 'user1'),
(2, true, '$2a$08$uP6Rtaz2anOuc/989c.v3etAICDNcsYi7Fo37PGHkfrBxo6m4IPqO', 'user2');

insert into user_role(user_id, roles) value
(1, 'USER'), (1, 'ADMIN'),
(2,'USER');