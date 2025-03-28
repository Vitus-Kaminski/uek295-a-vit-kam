-- Kategorien
INSERT INTO category (id, name) VALUES ('550e8400-e29b-41d4-a716-446655440000', 'NotListed') ON CONFLICT (name) DO NOTHING;
INSERT INTO category (id, name) VALUES ('550e8400-e29b-41d4-a716-446655440001', 'Electronics') ON CONFLICT (name) DO NOTHING;
INSERT INTO category (id, name) VALUES ('550e8400-e29b-41d4-a716-446655440002', 'Clothing') ON CONFLICT (name) DO NOTHING;