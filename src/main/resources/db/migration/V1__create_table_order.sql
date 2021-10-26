CREATE TABLE order_info (
  order_id INT NOT NULL primary key,
  name VARCHAR(45) NOT NULL,
  qty INT NOT NULL,
  price double precision NOT NULL,
  order_status VARCHAR(45) NOT NULL,
  payment_status VARCHAR(45) NOT NULL);
