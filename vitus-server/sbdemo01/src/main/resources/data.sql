INSERT INTO tracking (tracking_id, code, status, last_update) VALUES
    ('5f8d4c9e-27a1-4e2f-b0a9-72a3e5293b20', 'ABC123', 'Versandt', '2024-03-27 10:30:00');

INSERT INTO tracking (tracking_id, code, status, last_update) VALUES
    ('6a7d5b3c-12e4-45f9-b9d2-60f4e6a8e9f1', 'XYZ789', 'Unterwegs', '2024-03-26 14:45:00');

INSERT INTO tracking (tracking_id, code, status, last_update) VALUES
    ('9c3b7d1a-92ef-4f48-87a3-2d24f36a6fdd', 'LMN456', 'Zugestellt', '2024-03-25 18:20:00');

INSERT INTO tracking (tracking_id, code, status, last_update) VALUES
    ('2d5c9f6b-3a0e-472e-92bf-1f4b1d88cfae', 'DEF321', 'Verspätet', '2024-03-28 08:10:00');

INSERT INTO tracking (tracking_id, code, status, last_update) VALUES
    ('1b2a8e9c-5d7f-48d1-b0e1-3e4a9c2f8b0a', 'GHI654', 'Abholbereit', '2024-03-29 12:00:00');
ON CONFLICT DO NOTHING;