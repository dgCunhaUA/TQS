CREATE TABLE books (
  id BIGSERIAL PRIMARY KEY,
  title varchar(255)
);

INSERT INTO books(id, title) VALUES (1, 'Think Like a Monk');
INSERT INTO books(id, title) VALUES (2, 'Other');