INSERT INTO categories (id, name) VALUES
(1, 'Produce'),
(2, 'Dairy & Eggs'),
(3, 'Baked Goods'),
(4, 'Beverages'),
(5, 'Canned & Dry Goods');

 INSERT INTO products (name, price, description, category_id) VALUES
 ('Organic Bananas', 0.69, 'Certified organic bananas, sold individually.', 1),
 ('Roma Tomatoes', 1.99, 'One pound package of ripe Roma tomatoes, perfect for sauces.', 1),
 ('Avocado (Hass)', 2.49, 'Large, creamy Hass avocado.', 1);

 -- Dairy & Eggs (Category ID 2)
 INSERT INTO products (name, price, description, category_id) VALUES
 ('Large Grade A Eggs', 3.49, 'Dozen large brown eggs from cage-free hens.', 2),
 ('Whole Milk', 4.19, 'One gallon of pasteurized and homogenized whole milk.', 2);

 -- Baked Goods (Category ID 3)
 INSERT INTO products (name, price, description, category_id) VALUES
 ('Whole Wheat Bread', 3.99, 'Freshly baked whole wheat loaf, 20 slices.', 3);

 -- Beverages (Category ID 4)
 INSERT INTO products (name, price, description, category_id) VALUES
 ('Sparkling Water (Case)', 7.99, 'Case of 12 unflavored sparkling water cans.', 4),
 ('Orange Juice (No Pulp)', 5.25, '64 oz carton of 100% natural, no-pulp orange juice.', 4);

 -- Canned & Dry Goods (Category ID 5)
 INSERT INTO products (name, price, description, category_id) VALUES
 ('Black Beans (Canned)', 1.10, '15 oz can of seasoned black beans.', 5),
 ('Spaghetti Pasta', 1.75, 'One pound box of bronze-cut durum wheat spaghetti.', 5);