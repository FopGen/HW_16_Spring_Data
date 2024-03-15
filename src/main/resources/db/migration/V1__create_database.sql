CREATE TABLE note (
    id UUID default random_uuid() PRIMARY KEY ,
    title VARCHAR(255),
    content TEXT
);