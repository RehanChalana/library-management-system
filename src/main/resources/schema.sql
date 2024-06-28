
CREATE TABLE IF NOT EXISTS authors(
    author_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS books (
    book_id SERIAL PRIMARY KEY,
    ISBN VARCHAR(14) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL UNIQUE,
    publication_date DATE NOT NULL,
    author_id SERIAL NOT NULL,
    genre VARCHAR(255) NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors(author_id)
);

-- Users table to store user details
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);


CREATE TABLE IF NOT EXISTS book_copies  (
    copy_id SERIAL PRIMARY KEY,
    book_id INT NOT NULL ,
    user_id INT,
    is_borrowed BOOLEAN NOT NULL,
    due_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(book_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Authorities table to store roles/authorities for each user
CREATE TABLE authorities (
    user_id INTEGER NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

-- Index for performance improvement on authorities table
CREATE UNIQUE INDEX ix_auth_user_id ON authorities (user_id, authority);







