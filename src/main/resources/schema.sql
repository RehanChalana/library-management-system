
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

CREATE TABLE IF NOT EXISTS users(
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password CHAR(68) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS book_copies(
    copy_id SERIAL PRIMARY KEY,
    book_id INTEGER NOT NULL,
    user_id INTEGER ,
    is_borrowed BOOLEAN NOT NULL,
    due_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(book_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS authorities (
    authorities_id SERIAL PRIMARY KEY ,
    role VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS user_authorities (
    authorities_id INT NOT NULL ,
    user_id INT NOT NULL ,
    FOREIGN KEY (authorities_id) REFERENCES users(user_id),
    FOREIGN KEY (authorities_id) REFERENCES authorities(authorities_id, role),
    PRIMARY KEY (user_id, authority_id)
);







