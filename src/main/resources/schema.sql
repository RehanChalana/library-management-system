


CREATE TABLE IF NOT EXISTS book (
    book_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    num_pages INT NOT NULL,
    publication_date DATE NOT NULL,
    author_id INT NOT NULL,
    is_borrowed BOOLEAN
);





