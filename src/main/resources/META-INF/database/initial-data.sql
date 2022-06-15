insert into Item (id, name, price, created_date, description) values (1, 'Kindle', 499.0, date_sub(sysdate(), interval 1 day),'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into Item (id, name, price, created_date,description) values (3, 'Camera GoPro', 1499.0, date_sub(sysdate(), interval 1 day), 'Uma boa camera.');

insert into Client (id, name) values (1, "Lucas");
insert into Client (id, name) values (2, "Henrique");
insert into Client (id, name) values (3, "Silva");

insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (1, 1, date_sub(sysdate(), interval 1 day), date_sub(sysdate(), interval 1 day), 998.0, 'WAITING');
insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (2, 1, date_sub(sysdate(), interval 1 day), date_sub(sysdate(), interval 1 day), 449.0, 'WAITING');

insert into order_item (order_id, item_id, item_price, amount) values (1, 1, 499.0, 2);
insert into order_item (order_id, item_id, item_price, amount) values (2, 1, 499.0, 1);


insert into payment(payment_type, order_id, status, card_number) values ('creditcard',1, 'PROCESSING', '1234.1234.1234')
insert into payment(payment_type, order_id, status, bar_code) values ('bankslip', 2, 'PROCESSING', '12323932463874383454')

insert into category (id, name) values (1, 'Electronics');