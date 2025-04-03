
INSERT INTO tracking (tracking_id, code, status, last_update) VALUES
                                                                  ('5f8d4c9e-27a1-4e2f-b0a9-72a3e5293b20', 'ABC123', 'Versandt', '2024-03-27 10:30:00'),
                                                                  ('6a7d5b3c-12e4-45f9-b9d2-60f4e6a8e9f1', 'XYZ789', 'Unterwegs', '2024-03-26 14:45:00'),
                                                                  ('9c3b7d1a-92ef-4f48-87a3-2d24f36a6fdd', 'LMN456', 'Zugestellt', '2024-03-25 18:20:00'),
                                                                  ('2d5c9f6b-3a0e-472e-92bf-1f4b1d88cfae', 'DEF321', 'Verspätet', '2024-03-28 08:10:00'),
                                                                  ('1b2a8e9c-5d7f-48d1-b0e1-3e4a9c2f8b0a', 'GHI654', 'Abholbereit', '2024-03-29 12:00:00')ON CONFLICT DO NOTHING;

INSERT INTO orders (order_id, status, comment, date, tracking_id) VALUES
                                                                      ('a1b2c3d4-e5f6-7890-ab12-cd34ef567890', 'Pending', 'Bestellung in Bearbeitung', '2024-03-27', '5f8d4c9e-27a1-4e2f-b0a9-72a3e5293b20'),
                                                                      ('b2c3d4e5-f678-9012-ab34-cd56ef789012', 'Shipped', 'Versand wurde eingeleitet', '2024-03-26', '6a7d5b3c-12e4-45f9-b9d2-60f4e6a8e9f1'),
                                                                      ('c3d4e5f6-7890-1234-ab56-cd78ef901234', 'Completed', 'Bestellung erfolgreich zugestellt', '2024-03-25', '9c3b7d1a-92ef-4f48-87a3-2d24f36a6fdd'),
                                                                      ('d4e5f678-9012-3456-ab78-cd90ef123456', 'Delayed', 'Versand hat sich verzögert', '2024-03-28', '2d5c9f6b-3a0e-472e-92bf-1f4b1d88cfae'),
                                                                      ('e5f67890-1234-5678-ab90-cd12ef345678', 'Ready for Pickup', 'Bestellung zur Abholung bereit', '2024-03-29', '1b2a8e9c-5d7f-48d1-b0e1-3e4a9c2f8b0a')ON CONFLICT (order_id) DO NOTHING;

