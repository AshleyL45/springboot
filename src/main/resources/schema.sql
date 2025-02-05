CREATE TABLE IF NOT EXISTS user (
    user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS film (
    film_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    synopsis TEXT,
    release_date DATE,
    production VARCHAR(255),
    image_url VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS actor (
    actor_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    birthdate DATE,
    birthplace VARCHAR(255),
    popularity INT,
    gender VARCHAR(50),
    biography TEXT,
    image_url VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS category (
    category_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS watched_film (
    user_id INT,
    film_id INT,
    rating INT,
    watch_date DATE,
    PRIMARY KEY (user_id, film_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (film_id) REFERENCES film(film_id)
);

CREATE TABLE IF NOT EXISTS favorite_film (
    user_id INT,
    film_id INT,
    PRIMARY KEY (user_id, film_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (film_id) REFERENCES film(film_id)
);

CREATE TABLE IF NOT EXISTS favorite_actor (
    user_id INT,
    actor_id INT,
    PRIMARY KEY (user_id, actor_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (actor_id) REFERENCES actor(actor_id)
);

CREATE TABLE IF NOT EXISTS film_category (
    film_id INT,
    category_id INT,
    PRIMARY KEY (film_id, category_id),
    FOREIGN KEY (film_id) REFERENCES film(film_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);
