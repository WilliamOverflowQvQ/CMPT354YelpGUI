-- Use SQL to create the tables and constraints in your SQL Server database according to the description below.
-- Your database should have the following tables, where the primary key is underlined, and foreign keys are 
-- shown in italic. You must use the exact names and domains specified. Marks will be deducted if you fail to do so.


-- business = (business_id, name, address, city, postal_code, stars, review_count)

-- Table business 
-- ● business_id: string(22), primary key. 
-- ● name: variable-length string(60), not null. 
-- ● address: variable-length string(75). 
-- ● city: variable-length string (30), not null. 
-- ● postal_code: variable-length string(7). 
-- ● stars: decimal(2, 1), in [1,5] interval.
-- ● review_count: integer, default: 0, non-negative.

CREATE TABLE business (
    business_id    CHAR(22) PRIMARY KEY,
    name           VARCHAR(60) NOT NULL,
    address        VARCHAR(75),
    city           VARCHAR(30) NOT NULL,
    postal_code    VARCHAR(7),
    stars          DECIMAL(2, 1) CHECK (stars BETWEEN 1 AND 5),
    review_count   INT DEFAULT 0 CHECK (review_count >= 0)
);



-- checkin = (checkin_id, business_id, date)
-- Table checkin 
-- ● checkin_id: integer, primary key. 
-- ● business_id: string(22), not null.
-- ● date: date and time, not null, default: current date.

CREATE TABLE checkin (
    checkin_id    INT PRIMARY KEY,
    business_id   CHAR(22) NOT NULL,
    date          DATETIME NOT NULL DEFAULT GETDATE(),
    FOREIGN KEY (business_id) REFERENCES business(business_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);




-- tip = (tip_id, user_id, business_id, date, compliment_count) 
-- Table tip 
-- ● tip_id: integer, primary key. 
-- ● user_id: string(22), not null. 
-- ● business_id: string(22), not null. 
-- ● date: date and time, not null, default: current date. 
-- ● compliment_count: integer, default: 0, non-negative. 

CREATE TABLE tip (
    tip_id             INT PRIMARY KEY,
    user_id            CHAR(22) NOT NULL,
    business_id        CHAR(22) NOT NULL,
    date               DATETIME NOT NULL DEFAULT GETDATE(),
    compliment_count   INT DEFAULT 0 CHECK (compliment_count >= 0),
    FOREIGN KEY (user_id) REFERENCES user_yelp(user_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (business_id) REFERENCES business(business_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);




-- friendship = (user_id, friend)
-- Table friendship 
-- ● user_id: string(22), primary key 
-- ● friend: string(22), primary key 

CREATE TABLE friendship (
    user_id CHAR(22),
    friend  CHAR(22),
    PRIMARY KEY (user_id, friend),
    FOREIGN KEY (user_id) REFERENCES user_yelp(user_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (friend) REFERENCES user_yelp(user_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);




-- review = (review_id, user_id, business_id, stars, useful, funny, cool, date) 
-- Table review 
-- ● review_id: string(22), primary key. 
-- ● user_id: string(22), not null. 
-- ● business_id: string(22), not null. 
-- ● stars: integer, not null, between 1 and 5. 
-- ● useful: integer, default: 0, non-negative. 
-- ● funny: integer, default: 0, non-negative. 
-- ● cool: integer, default: 0, non-negative. 
-- ● date: date and time, default: current date. 

CREATE TABLE review (
    review_id       CHAR(22) PRIMARY KEY,
    user_id         CHAR(22) NOT NULL,
    business_id     CHAR(22) NOT NULL,
    stars           INT NOT NULL CHECK (stars BETWEEN 1 AND 5),
    useful          INT DEFAULT 0 CHECK (useful >= 0),
    funny           INT DEFAULT 0 CHECK (funny >= 0),
    cool            INT DEFAULT 0 CHECK (cool >= 0),
    date            DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES user_yelp(user_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (business_id) REFERENCES business(business_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);




-- user_yelp = (user_id, name, review_count, yelping_since, useful, funny, cool, fans, average_stars)
-- Table user_yelp 
-- ● user_id: string(22), primary key. 
-- ● name: variable-length string(35), not null. 
-- ● review_count: integer, default 0, non-negative. 
-- ● yelping_since: date and time, not null, default: current date. 
-- ● useful: integer, default: 0, non-negative. 
-- ● funny: integer, default: 0, non-negative. 
-- ● cool: integer, default: 0, non-negative. 
-- ● fans: integer, default: 0, non-negative.
-- ● average_stars: decimal(3, 2), in [1,5] interval.

CREATE TABLE user_yelp (
    user_id        CHAR(22) PRIMARY KEY,
    name           VARCHAR(35) NOT NULL,
    review_count   INT DEFAULT 0 CHECK (review_count >= 0),
    yelping_since  DATETIME NOT NULL DEFAULT GETDATE(),
    useful         INT DEFAULT 0 CHECK (useful >= 0),
    funny          INT DEFAULT 0 CHECK (funny >= 0),
    cool           INT DEFAULT 0 CHECK (cool >= 0),
    fans           INT DEFAULT 0 CHECK (fans >= 0),
    average_stars  DECIMAL(3, 2) CHECK (average_stars BETWEEN 1 AND 5)
);

-- Constraints Your database should enforce the primary key constraints noted above. 
-- The following foreign key constraints also need to be enforced: 
-- - checkin.business_id references business.business_id 
-- - tip.business_id references business.business_id 
-- - tip.user_id references user_yelp.user_id 
-- - review.business_id references business.business_id 
-- - review.user_id references user_yelp.user_id 
-- - friendship.user_id references user_yelp.user_id
-- - friendship.friend references user_yelp.user_id
-- The response to the violation of foreign keys constraints should be to not allow deletion or update of referenced records.
