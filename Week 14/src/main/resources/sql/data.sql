insert into users values('user1', '{noop}secret1', true);
insert into users values('user2', '{bcrypt}$2a$10$frjR.ML/Qj7U57slNYHE5OIPfRLhnLtyWo/BhYpd2PpoydKy26q32', true);
insert into users values('user3', '{bcrypt}$2a$10$46qedOxIcVfhZzq0xGDEUeQ/CcmqXHQDB67th8ZjooMXqthR.snFO', true);
insert into users values('user4', '{noop}secret4', false);

insert into authorities values('user1', 'ROLE_USER');
insert into authorities values('user2', 'ROLE_USER');
insert into authorities values('user2', 'ROLE_EDITOR');
insert into authorities values('user3', 'ROLE_USER');
insert into authorities values('user3', 'ROLE_EDITOR');
insert into authorities values('user3', 'ROLE_ADMIN');
insert into authorities values('user4', 'ROLE_USER');