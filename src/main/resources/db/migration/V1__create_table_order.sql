CREATE TABLE order_info (
  order_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  qty INT NOT NULL,
  price DOUBLE NOT NULL,
  order_status VARCHAR(45) NOT NULL,
  payment_status VARCHAR(45) NOT NULL,
  PRIMARY KEY (order_id));
