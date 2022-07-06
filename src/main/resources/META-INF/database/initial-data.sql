insert into item (id, name, price, created_date, description) values (1, 'Kindle', 499.0, date_sub(sysdate(), interval 1 day),'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into item (id, name, price, created_date,description) values (3, 'Camera GoPro', 1499.0, date_sub(sysdate(), interval 1 day), 'Uma boa camera.');
insert into item (id, name, price, created_date,description) values (4, 'Camera GoPro 2', 2499.0, date_sub(sysdate(), interval 1 day), 'Hell YEAH Camera.');
insert into item (id, name, price, created_date,description) values (5, 'Camera GoPro 3', 3499.0, date_sub(sysdate(), interval 1 day), 'Holy Fuck Camera.');

insert into client (id, name, cpf) values (1, "Lucas Souza", "105");
insert into client (id, name, cpf) values (2, "Henrique Vasconcelos", "123");
insert into client (id, name, cpf) values (3, "Silva Lima", "124");
insert into client (id, name, cpf) values (4, "Amanda Vaz", "4432");

insert into client_details (client_id,gender, birth_date) values (1, "MALE", date_sub(sysdate(), interval 27 year))
insert into client_details (client_id,gender, birth_date) values (2, "MALE", date_sub(sysdate(), interval 21 year))
insert into client_details (client_id,gender, birth_date) values (3, "MALE", date_sub(sysdate(), interval 32 year))
insert into client_details (client_id,gender, birth_date) values (4, "FEMALE", date_sub(sysdate(), interval 12 year))

insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (1, 1, date_sub(sysdate(), interval 1 day), date_sub(sysdate(), interval 1 day), 1497.0, 'WAITING');
insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (2, 1, date_sub(sysdate(), interval 1 day), date_sub(sysdate(), interval 1 day), 449.0, 'WAITING');
insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (3, 2, date_sub(sysdate(), interval 1 day), date_sub(sysdate(), interval 1 day), 449.0, 'WAITING');
insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (4, 3, date_sub(sysdate(), interval 1 day), date_sub(sysdate(), interval 1 day), 449.0, 'WAITING');
insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (5, 3, date_sub(sysdate(), interval 1 day), date_sub(sysdate(), interval 1 day), 449.0, 'WAITING');
insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (6, 4, date_sub(sysdate(), interval 1 day), date_sub(sysdate(), interval 1 day), 3499.0, 'WAITING');

insert into order_item (order_id, item_id, item_price, amount) values (1, 1, 499.0, 2);
insert into order_item (order_id, item_id, item_price, amount) values (1, 3, 499.0, 1);
insert into order_item (order_id, item_id, item_price, amount) values (2, 1, 499.0, 1);
insert into order_item (order_id, item_id, item_price, amount) values (3, 1, 499.0, 1);
insert into order_item (order_id, item_id, item_price, amount) values (4, 3, 499.0, 1);
insert into order_item (order_id, item_id, item_price, amount) values (6, 5, 3499.0, 1);


insert into payment(payment_type, order_id, status, card_number) values ('creditcard' ,1, 'PROCESSING', '123412341234')
insert into invoice(order_id, xml, emission_date) values (1, "<>", sysdate());

insert into category (id, name) values (1, 'Electronics');
insert into category (id, name) values (2, 'Book');

insert into item_category (item_id, category_id) values (1, 2)