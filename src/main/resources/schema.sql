
CREATE TABLE IF NOT EXISTS authors(
    author_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS books (
    book_id SERIAL PRIMARY KEY,
    ISBN VARCHAR(14) NOT NULL,
    title VARCHAR(255) NOT NULL,
    publication_date DATE NOT NULL,
    author_id SERIAL NOT NULL,
    genre VARCHAR(255) NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors(author_id)
);


CREATE TABLE IF NOT EXISTS book_copies(
    copy_id SERIAL PRIMARY KEY,
    book_id SERIAL NOT NULL,
    is_borrowed BOOLEAN NOT NULL,
    due_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);





