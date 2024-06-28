

INSERT INTO users (username, password, enabled) VALUES ('user1', '{bcrypt}$2a$10$aUSABGv6GxbxaYXK50orHuUNnzvEDPx5XVV61YwG6PN4sdrc5u3bm', true);
INSERT INTO authorities (user_id, authority) VALUES ((SELECT user_id FROM users WHERE username = 'user1'), 'ROLE_ADMIN');

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


-- Inserting book copies
INSERT INTO book_copies (book_id, user_id, is_borrowed, due_date) VALUES
(1, 1, TRUE, '2024-07-01'),
(2, 1, TRUE, '2024-07-10'),
(3, NULL, FALSE, NULL),
(4, 1, TRUE, '2024-06-20'),
(5, NULL, FALSE, NULL);


