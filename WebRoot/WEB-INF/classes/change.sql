--userinfo表的username和email区分大小写--

alter table userinfo drop constraint unique_username;
alter table userinfo alter column username varchar(50) COLLATE Chinese_PRC_CS_AS;
alter table userinfo add constraint unique_username unique(username);

alter table userinfo drop constraint unique_email;
alter table userinfo alter column email varchar(50) COLLATE Chinese_PRC_CS_AS;
alter table userinfo add constraint unique_email unique(email);