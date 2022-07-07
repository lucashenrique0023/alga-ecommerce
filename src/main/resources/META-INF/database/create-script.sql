create table testando (id integer not null auto_increment,name varchar(100) not null,upper_category integer,primary key (id)) engine=InnoDB

create function above_orders_total_average(valor double) returns boolean reads sql data return valor > (select avg(total) from orderr);