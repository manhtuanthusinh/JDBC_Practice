-- 1. Users Table: Basic CRUD and Authentication practice
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Accounts Table: Practice Foreign Keys and Decimal handling
-- A user can have multiple accounts (Savings, Checking, Cash)
CREATE TABLE accounts (
                          account_id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT NOT NULL,
                          account_name VARCHAR(50) NOT NULL,
                          account_type ENUM('Checking', 'Savings', 'Credit', 'Cash') NOT NULL,
                          balance DECIMAL(15, 2) DEFAULT 0.00,
                          FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- 3. Categories Table: Simple lookup table practice
CREATE TABLE categories (
                            category_id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(50) NOT NULL UNIQUE
);

-- 4. Transactions Table: Practice Complex Queries and Joins
CREATE TABLE transactions (
                              transaction_id INT AUTO_INCREMENT PRIMARY KEY,
                              account_id INT NOT NULL,
                              category_id INT NOT NULL,
                              amount DECIMAL(15, 2) NOT NULL,
                              transaction_type ENUM('Income', 'Expense') NOT NULL,
                              description VARCHAR(255),
                              transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE,
                              FOREIGN KEY (category_id) REFERENCES categories(category_id)
);
