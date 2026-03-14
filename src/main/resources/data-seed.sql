
INSERT INTO categories (name, description, created_at, updated_at) VALUES
('Electronics', 'Electronic devices and gadgets', NOW(), NOW()),
('Clothing', 'Fashion and apparel', NOW(), NOW()),
('Sports', 'Sports equipment and gear', NOW(), NOW()),
('Books', 'Books and literature', NOW(), NOW()),
('Food', 'Food and beverages', NOW(), NOW());

INSERT INTO products (name, description, price, stock, sku, category_id, created_at, updated_at)
SELECT
    'Product ' || i,
    'Description for product ' || i,
    (random() * 1000000)::numeric(15,2),
    (random() * 100)::int,
    'SKU-' || LPAD(i::text, 6, '0'),
    (i % 5) + 1,
    NOW(),
    NOW()
FROM generate_series(1, 10000) AS s(i);
