ALTER TABLE order_info
ADD COLUMN account_num VARCHAR(200) NOT NULL AFTER payment_status;