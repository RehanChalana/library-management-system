-- Inserting authors
INSERT INTO authors (name) VALUES
('George Orwell'),
('J.K. Rowling'),
('J.R.R. Tolkien'),
('Jane Austen'),
('Mark Twain');

-- Inserting books
INSERT INTO books (ISBN, title, publication_date, author_id, genre) VALUES
('978-0451524935', '1984', '1949-06-08', 1, 'Dystopian'),
('978-0439554930', 'Harry Potter and the Sorcerers Stone', '1997-09-01', 2, 'Fantasy'),
('978-0618260300', 'The Hobbit', '1937-09-21', 3, 'Fantasy'),
('978-1503290563', 'Pride and Prejudice', '1813-01-28', 4, 'Romance'),
('978-0486280615', 'The Adventures of Huckleberry Finn', '1884-12-10', 5, 'Adventure');

-- Inserting users
INSERT INTO users (username, password, enabled) VALUES
('alice', '2b6e6e3dc7da2fbc08d1c5cf0f8a11e8', TRUE),
('bob', '098f6bcd4621d373cade4e832627b4f6', TRUE),
('charlie', 'ab56b4d92b40713acc5af89985d4b786', FALSE),
('dave', '5f4dcc3b5aa765d61d8327deb882cf99', TRUE);

-- Inserting book copies
INSERT INTO book_copies (book_id, user_id, is_borrowed, due_date) VALUES
(1, 1, TRUE, '2024-07-01'),
(2, 2, TRUE, '2024-07-10'),
(3, NULL, FALSE, NULL),
(4, 1, TRUE, '2024-06-20'),
(5, NULL, FALSE, NULL);

-- Insert sample users
INSERT INTO users (username, password, enabled) VALUES ('user1', '{bcrypt}$2a$12$WnH/PHyYhtEGxQTvayAQ5O./tqK9JRsCGQ2WPxn/ZEauZ.8gUqX6W', TRUE);
INSERT INTO users (username, password, enabled) VALUES ('user2', '{bcrypt}$2a$12$lkIVoeA2SGZTyk9LlCY.m.H5tXNfMhOOgzll2Ha.I0YtMQhM6w6xS', TRUE);

-- Insert sample authorities
INSERT INTO authorities (role) VALUES ('ROLE_USER');
INSERT INTO authorities (role) VALUES ('ROLE_ADMIN');

-- Insert sample user_authorities relationships
INSERT INTO user_authorities (user_id, authority_id) VALUES (1, 1);  -- user1 has ROLE_USER
INSERT INTO user_authorities (user_id, authority_id) VALUES (1, 2);  -- user1 has ROLE_ADMIN
INSERT INTO user_authorities (user_id, authority_id) VALUES (2, 1);
