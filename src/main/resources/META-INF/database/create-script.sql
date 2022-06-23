create table category (id integer not null auto_increment,name varchar(100) not null,upper_category integer,primary key (id)) engine=InnoDB
create table client (id integer not null auto_increment,cpf varchar(14) not null,name varchar(100) not null,primary key (id)) engine=InnoDB
create table client_contacts (client_id integer not null,description varchar(255),contacts_KEY varchar(255) not null,primary key (client_id, contacts_KEY)) engine=InnoDB
create table client_details (birth_date date,gender varchar(30) not null,client_id integer not null,primary key (client_id)) engine=InnoDB
create table inventory (id integer not null auto_increment,amount integer,item_id integer not null,primary key (id)) engine=InnoDB
create table invoice (order_id integer not null,emission_date datetime(6) not null,xml longblob not null,primary key (order_id)) engine=InnoDB
create table item (id integer not null auto_increment,created_date datetime(6) not null,description varchar(275) default 'description',last_modify_date datetime(6),name varchar(100) not null,picture longblob,price decimal(19,2),primary key (id)) engine=InnoDB
create table item_attribute (item_id integer not null,name varchar(100) not null,value varchar(255)) engine=InnoDB
create table item_category (item_id integer not null,category_id integer not null) engine=InnoDB
create table item_tag (item_id integer not null,tag varchar(50) not null) engine=InnoDB
create table order_item (item_id integer not null,order_id integer not null,amount integer not null,item_price decimal(19,2) not null,primary key (item_id, order_id)) engine=InnoDB
create table orderr (id integer not null auto_increment,additional_info varchar(50),city varchar(50),number varchar(10),state varchar(2),street varchar(100),zip varchar(9),conclusion_date datetime(6),created_date datetime(6) not null,last_modify_date datetime(6),status varchar(30) not null,total decimal(19,2) not null,client_id integer not null,primary key (id)) engine=InnoDB
create table payment (payment_type varchar(31) not null,order_id integer not null,status varchar(30) not null,bar_code varchar(100),card_number varchar(50),primary key (order_id)) engine=InnoDB
alter table category add constraint unq_name unique (name)
create index idx_name on client (name)
alter table client add constraint unq_cpf unique (cpf)
alter table inventory add constraint unq_item unique (item_id)
create index idx_name on item (name)
alter table item add constraint unq_name unique (name)
alter table item_category add constraint unq_item_category unique (item_id, category_id)
alter table category add constraint pk_category_category foreign key (upper_category) references category (id)
alter table client_contacts add constraint fk_client_contacts_client foreign key (client_id) references client (id)
alter table client_details add constraint fk_client_details_client foreign key (client_id) references client (id)
alter table inventory add constraint fk_inventory_item foreign key (item_id) references item (id)
alter table invoice add constraint fk_invoice_order foreign key (order_id) references orderr (id)
alter table item_attribute add constraint fk_item_attribute_item foreign key (item_id) references item (id)
alter table item_category add constraint fk_item_category_category foreign key (category_id) references category (id)
alter table item_category add constraint fk_item_category_item foreign key (item_id) references item (id)
alter table item_tag add constraint fk_item_tag_item foreign key (item_id) references item (id)
alter table order_item add constraint fk_order_item_item foreign key (item_id) references item (id)
alter table order_item add constraint fk_order_item_order foreign key (order_id) references orderr (id)
alter table orderr add constraint fk_order_client foreign key (client_id) references client (id)
alter table payment add constraint fk_payment_order foreign key (order_id) references orderr (id)