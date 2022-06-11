insert into Item (name, price, created_date, description) values ('Kindle', 499.0, date_sub(sysdate(), interval 1 day),'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into Item (name, price, created_date,description) values ('Camera GoPro', date_sub(sysdate(), interval 1 day),1499.0, 'Uma boa camera.');

insert into Client (name) values ("Lucas");
insert into Client (name) values ("Henrique");
insert into Client (name) values ("Silva");

insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (1, 1, date_sub(sysdate(), interval 1 day), date_sub(sysdate(), interval 1 day), 100.0, 'WAITING');

insert into order_item (order_id, item_id, item_price, amount) values (1, 1, 5.0, 2);

insert into category (id, name) values (1, "Electronics");