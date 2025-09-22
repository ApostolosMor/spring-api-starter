CREATE TABLE orders (
    id bigint auto_increment PRIMARY KEY,
    customer_id bigint,
    status varchar(20),
    created_at datetime DEFAULT CURRENT_TIMESTAMP,
    total_price decimal(20,2),
    CONSTRAINT orders_users_id_fk
        FOREIGN KEY (customer_id) REFERENCES users(id)
);

CREATE TABLE order_items (
    id bigint auto_increment PRIMARY KEY,
    order_id bigint NOT NULL,
    product_id bigint NOT NULL,
    unit_price decimal(20,2) NOT NULL,
    quantity int NOT NULL,
    total_price decimal(20,2) NOT NULL,
    CONSTRAINT order_items_orders_id_fk
        FOREIGN KEY (order_id) REFERENCES orders(id), -- Missing comma here
    CONSTRAINT order_items_products_id_fk
        FOREIGN KEY (product_id) REFERENCES products(id)
);


