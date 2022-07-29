insert into item (id, name, price, created_date, description) values (1, 'Kindle', 499.0, date_sub(sysdate(), interval 1 day),'Nice Gadget');
insert into item (id, name, price, created_date,description) values (3, 'Camera GoPro', 1499.0, date_sub(sysdate(), interval 1 day), 'Good Gamera.');
insert into item (id, name, price, created_date,description) values (4, 'Google Pixel', 2499.0, date_sub(sysdate(), interval 1 day), 'Good Smartphone.');
insert into item (id, name, price, created_date,description) values (5, 'Sandwich', 3499.0, date_sub(sysdate(), interval 1 day), 'Good Sandwich');

insert into category (id,name) values (1, 'Electronics');
insert into category (id,name) values (2, 'Book');
insert into category (id,name) values (3, 'Football');
insert into category (id,name) values (4, 'Food');
insert into category (id,name) values (5, 'Swimming');
insert into category (id,name) values (6, 'Laptop');
insert into category (id,name) values (7, 'Kitchen');
insert into category (id,name) values (8, 'Smartphones');

insert into item_category (item_id, category_id) values (1, 1)
insert into item_category (item_id, category_id) values (1, 2)
insert into item_category (item_id, category_id) values (3, 1)
insert into item_category (item_id, category_id) values (4, 1)
insert into item_category (item_id, category_id) values (4, 8)
insert into item_category (item_id, category_id) values (5, 4)

insert into client (id, name, cpf) values (1, "Lucas Souza", "105");
insert into client (id, name, cpf) values (2, "Henrique Vasconcelos", "123");
insert into client (id, name, cpf) values (3, "Silva Lima", "124");
insert into client (id, name, cpf) values (4, "Amanda Vaz", "4432");

insert into client_details (client_id,gender, birth_date) values (1, "MALE", date_sub(sysdate(), interval 27 year))
insert into client_details (client_id,gender, birth_date) values (2, "MALE", date_sub(sysdate(), interval 21 year))
insert into client_details (client_id,gender, birth_date) values (3, "MALE", date_sub(sysdate(), interval 32 year))
insert into client_details (client_id,gender, birth_date) values (4, "FEMALE", date_sub(sysdate(), interval 12 year))

insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (1, 1, sysdate(), sysdate(), 998.0, 'WAITING');
insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (2, 1, date_sub(sysdate(), interval 1 day), date_sub(sysdate(), interval 1 day), 499.0, 'WAITING');
insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (3, 2, date_sub(sysdate(), interval 2 day), date_sub(sysdate(), interval 2 day), 499.0, 'WAITING');
insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (4, 3, date_sub(sysdate(), interval 3 day), date_sub(sysdate(), interval 3 day), 1499.0, 'WAITING');
insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (5, 3, date_sub(sysdate(), interval 4 day), date_sub(sysdate(), interval 4 day), 1499.0, 'WAITING');
insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (6, 4, date_sub(sysdate(), interval 5 day), date_sub(sysdate(), interval 5 day), 3499.0, 'WAITING');
insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (7, 4, date_sub(sysdate(), interval 500 day), date_sub(sysdate(), interval 5 day), 3499.0, 'WAITING');
insert into orderr (id, client_id, created_date, last_modify_date, total, status) values (8, 4, date_sub(sysdate(), interval 900 day), date_sub(sysdate(), interval 5 day), 3499.0, 'WAITING');

insert into order_item (order_id, item_id, item_price, amount) values (1, 1, 499.0, 2);
insert into order_item (order_id, item_id, item_price, amount) values (1, 3, 499.0, 1);
insert into order_item (order_id, item_id, item_price, amount) values (2, 1, 499.0, 1);
insert into order_item (order_id, item_id, item_price, amount) values (3, 1, 499.0, 1);
insert into order_item (order_id, item_id, item_price, amount) values (4, 3, 1499.0, 1);
insert into order_item (order_id, item_id, item_price, amount) values (5, 3, 1499.0, 1);
insert into order_item (order_id, item_id, item_price, amount) values (6, 5, 3499.0, 1);
insert into order_item (order_id, item_id, item_price, amount) values (7, 5, 3499.0, 1);
insert into order_item (order_id, item_id, item_price, amount) values (8, 5, 3499.0, 1);


insert into payment(payment_type, order_id, status, card_number) values ('creditcard' ,1, 'PROCESSING', '123412341234')
insert into payment(payment_type, order_id, status, card_number) values ('creditcard' ,2, 'CANCELED', '123412341234')
insert into payment(payment_type, order_id, status, card_number) values ('creditcard' ,3, 'CANCELED', '123412341234')
insert into payment(payment_type, order_id, status, card_number) values ('creditcard' ,4, 'PROCESSING', '123412341234')
insert into payment(payment_type, order_id, status, card_number) values ('creditcard' ,5, 'RECEIVED', '123412341234')
insert into payment(payment_type, order_id, status, bar_code, due_date) values ('bankslip' ,6, 'PROCESSING', '456', date_add(sysdate(), interval 1 day))
insert into payment(payment_type, order_id, status, bar_code, due_date) values ('bankslip' ,7, 'PROCESSING', '456', date_sub(sysdate(), interval 5 day))
insert into invoice(order_id, xml, emission_date) values (1, "<>", sysdate());



