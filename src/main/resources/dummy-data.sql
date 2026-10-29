-- ========================================================
-- DUMMY DATA FOR PLOTTHEIRFUTURE (MySQL)
-- ========================================================

-- 1. Insert Dummy Users
INSERT INTO users (id, name, password, mobile_number, role, otp, role_id, registered, image, location_id)
VALUES 
(1, 'Sanvi Reddy', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', '+919848012345', 'ADMIN', '123456', 1, 1, 'https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&w=400&q=80', 1),
(2, 'Santosh Varma', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', '+919876543210', 'COUPLE', '654321', 2, 1, 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?auto=format&fit=crop&w=400&q=80', 2),
(3, 'Malli Karjuna', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', '+919988776655', 'GUEST', '482910', 3, 0, 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?auto=format&fit=crop&w=400&q=80', 3)
ON DUPLICATE KEY UPDATE name=VALUES(name);

-- 2. Insert Dummy Locations
INSERT INTO locations (id, user_id, city, mandal, district, constituency, pincode, active, priority)
VALUES 
(1, 1, 'Hyderabad', 'Gachibowli', 'Rangareddy', 'Serilingampally', '500032', 1, 1),
(2, 2, 'Bengaluru', 'Indiranagar', 'Bengaluru Urban', 'Shantinagar', '560038', 1, 1),
(3, 3, 'Vijayawada', 'Benz Circle', 'Krishna', 'Vijayawada East', '520010', 1, 1)
ON DUPLICATE KEY UPDATE city=VALUES(city);

-- 3. Insert Dummy Web Apps
INSERT INTO web_app (id, name, type, active, primary_colour, secondary_colour, theme_colour)
VALUES 
(1, 'PlotTheirFuture Main Portal', 'website', 1, '#1E3A8A', '#D97706', '#F8FAFC'),
(2, 'Sanvi & Santosh Wedding Microsite', 'app', 1, '#831843', '#F59E0B', '#FFFBEB')
ON DUPLICATE KEY UPDATE name=VALUES(name);

-- 4. Insert Dummy Hero Images for Web Apps
INSERT INTO web_app_hero_images (web_app_id, image_url)
VALUES 
(1, 'https://images.unsplash.com/photo-1519741497674-611481863552?auto=format&fit=crop&w=1600&q=80'),
(1, 'https://images.unsplash.com/photo-1511285560929-80b456fea0bc?auto=format&fit=crop&w=1600&q=80'),
(1, 'https://images.unsplash.com/photo-1469371670807-013ccf25f16a?auto=format&fit=crop&w=1600&q=80'),
(2, 'https://images.unsplash.com/photo-1583939003579-730e3918a45a?auto=format&fit=crop&w=1600&q=80'),
(2, 'https://images.unsplash.com/photo-1606800052052-a08af7148866?auto=format&fit=crop&w=1600&q=80');
