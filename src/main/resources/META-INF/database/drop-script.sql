alter table category drop foreign key pk_category_category
alter table client_contacts drop foreign key fk_client_contacts_client
alter table client_details drop foreign key fk_client_details_client
alter table inventory drop foreign key fk_inventory_item
alter table invoice drop foreign key fk_invoice_order
alter table item_attribute drop foreign key fk_item_attribute_item
alter table item_category drop foreign key fk_item_category_category
alter table item_category drop foreign key fk_item_category_item
alter table item_tag drop foreign key fk_item_tag_item
alter table order_item drop foreign key fk_order_item_item
alter table order_item drop foreign key fk_order_item_order
alter table orderr drop foreign key fk_order_client
alter table payment drop foreign key fk_payment_order
drop table if exists category
drop table if exists client
drop table if exists client_contacts
drop table if exists client_details
drop table if exists inventory
drop table if exists invoice
drop table if exists item
drop table if exists item_attribute
drop table if exists item_category
drop table if exists item_tag
drop table if exists order_item
drop table if exists orderr
drop table if exists payment