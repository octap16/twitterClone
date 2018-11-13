insert into usr (id, username, password, active)
          values(1,'admin','$2a$08$O9dVJoRNBch45.Q1EhfEueeoEjTpgTYYnQyWN7P4hzgIBi.1xRmaq',true);
insert into user_role (user_id, roles)
          values(1,'USER'),(1,'ADMIN');