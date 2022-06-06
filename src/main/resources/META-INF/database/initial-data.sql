insert into Item (name, price, description) values ('Kindle', 499.0, 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into Item (name, price, description) values ('Camera GoPro', 1499.0, 'Uma boa camera.');

insert into Client (name) values ("Lucas");
insert into Client (name) values ("Henrique");
insert into Client (name) values ("Silva");

insert into orderr (id, client_id, order_date, total, status) values (1, 1, sysdate(), 100.0, 'WAITING');

insert into order_item (id, order_id, item_id, item_price, amount) values (1, 1, 1, 5.0, 2);

insert into category (id, name) values (1, "Electronics");