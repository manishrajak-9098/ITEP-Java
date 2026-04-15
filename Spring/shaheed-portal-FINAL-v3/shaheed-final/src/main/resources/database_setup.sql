-- ============================================================
--  Shaheed Parivar Sahayata Portal — Database Setup Script
--  MySQL 8.x
--  Run this ONCE before deploying the application
-- ============================================================

-- Step 1: Database banana
CREATE DATABASE IF NOT EXISTS shaheed_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE shaheed_db;

-- ============================================================
--  TABLE: users
--  All three roles: ADMIN, OFFICER, FAMILY
-- ============================================================
CREATE TABLE IF NOT EXISTS users (
    user_id             INT AUTO_INCREMENT PRIMARY KEY,
    username            VARCHAR(100) NOT NULL UNIQUE,
    password            VARCHAR(255) NOT NULL,        -- BCrypt hashed
    role                ENUM('ADMIN','OFFICER','FAMILY') NOT NULL,
    full_name           VARCHAR(150) NOT NULL,
    email               VARCHAR(150),
    phone               VARCHAR(15),
    is_password_changed TINYINT(1) DEFAULT 0,
    is_active           TINYINT(1) DEFAULT 1,
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_login          DATETIME,
    linked_family_id    INT,
    INDEX idx_username (username),
    INDEX idx_role     (role)
) ENGINE=InnoDB;

-- ============================================================
--  DEFAULT ADMIN — BCrypt of "Admin@123"
--  Username: admin@gov.in
--  Password: Admin@123
--  ⚠️  Change this in production immediately!
-- ============================================================
INSERT INTO users (username, password, role, full_name, email, is_password_changed, is_active, created_at)
VALUES (
    'admin@gov.in',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',  -- Admin@123
    'ADMIN',
    'System Administrator',
    'admin@shaheedportal.gov.in',
    1,  -- Admin ko password change nahi karna (already set)
    1,
    NOW()
);

-- ============================================================
--  TABLE: soldiers
--  Shaheed ka complete record
-- ============================================================
CREATE TABLE IF NOT EXISTS soldiers (
    soldier_id          INT AUTO_INCREMENT PRIMARY KEY,
    service_number      VARCHAR(20) NOT NULL UNIQUE,
    full_name           VARCHAR(150) NOT NULL,
    rank                VARCHAR(50) NOT NULL,
    unit                VARCHAR(100) NOT NULL,
    regiment            VARCHAR(100),
    date_of_birth       DATE,
    date_of_joining     DATE,
    date_of_martyrdom   DATE NOT NULL,
    place_of_martyrdom  VARCHAR(200),
    martyrdom_category  ENUM('BATTLE_CASUALTY','ACCIDENTAL','DISEASE','OTHER'),
    awards              VARCHAR(500),
    case_status         ENUM('PENDING','DOCUMENTS_SUBMITTED','UNDER_VERIFICATION','VERIFIED','BENEFITS_PROCESSING','COMPLETED','REJECTED')
                        DEFAULT 'PENDING',
    application_id      VARCHAR(20) UNIQUE,            -- SP-2024-00001
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by_admin_id INT NOT NULL,
    assigned_officer_id INT,
    officer_remarks     VARCHAR(500),
    INDEX idx_application_id (application_id),
    INDEX idx_case_status    (case_status)
) ENGINE=InnoDB;

-- ============================================================
--  TABLE: family_members
--  Shaheed ke parivaar ka record
-- ============================================================
CREATE TABLE IF NOT EXISTS family_members (
    family_id               INT AUTO_INCREMENT PRIMARY KEY,
    soldier_id              INT NOT NULL,
    full_name               VARCHAR(150) NOT NULL,
    relation                ENUM('WIFE','MOTHER','FATHER','SON','DAUGHTER','BROTHER','SISTER','OTHER') NOT NULL,
    date_of_birth           DATE,
    aadhar_number           VARCHAR(20),
    address                 VARCHAR(500),
    city                    VARCHAR(100),
    state                   VARCHAR(100),
    pincode                 VARCHAR(10),
    phone                   VARCHAR(15),
    email                   VARCHAR(150),
    bank_account_number     VARCHAR(30),
    bank_name               VARCHAR(100),
    ifsc_code               VARCHAR(20),
    bank_branch             VARCHAR(100),
    is_primary_beneficiary  TINYINT(1) DEFAULT 0,
    has_login_access        TINYINT(1) DEFAULT 0,
    INDEX idx_soldier_id (soldier_id)
) ENGINE=InnoDB;

-- ============================================================
--  TABLE: benefits
--  Government benefits tracking
-- ============================================================
CREATE TABLE IF NOT EXISTS benefits (
    benefit_id          INT AUTO_INCREMENT PRIMARY KEY,
    soldier_id          INT NOT NULL,
    family_id           INT,
    benefit_type        ENUM('EX_GRATIA','FAMILY_PENSION','EDUCATION_HELP','MEDICAL_HELP',
                             'HOUSING_HELP','JOB_ASSISTANCE','SCHOLARSHIP','OTHER') NOT NULL,
    benefit_name        VARCHAR(200) NOT NULL,
    description         VARCHAR(500),
    amount              DECIMAL(15,2),
    amount_unit         VARCHAR(50),                   -- 'One Time', 'Per Month'
    status              ENUM('PENDING','APPROVED','UNDER_PROCESS','PAID','REJECTED','ON_HOLD')
                        DEFAULT 'PENDING',
    approval_date       DATE,
    payment_date        DATE,
    sanctioned_by       VARCHAR(150),
    remarks             VARCHAR(500),
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by_admin_id INT NOT NULL,
    INDEX idx_soldier_status (soldier_id, status)
) ENGINE=InnoDB;

-- ============================================================
--  TABLE: documents
--  Uploaded files record
-- ============================================================
CREATE TABLE IF NOT EXISTS documents (
    doc_id                  INT AUTO_INCREMENT PRIMARY KEY,
    soldier_id              INT NOT NULL,
    uploaded_by_family_id   INT,
    doc_type                ENUM('DEATH_CERTIFICATE','IDENTITY_PROOF','BANK_PASSBOOK',
                                 'SERVICE_RECORD','FAMILY_PHOTO','MARRIAGE_CERTIFICATE',
                                 'BIRTH_CERTIFICATE','PENSION_FORM','AWARD_CERTIFICATE','OTHER') NOT NULL,
    doc_name                VARCHAR(200) NOT NULL,
    file_path               VARCHAR(500) NOT NULL,     -- Saved filename on server
    file_size               BIGINT,
    file_type               VARCHAR(50),               -- PDF, JPG, PNG
    verification_status     ENUM('PENDING','VERIFIED','REJECTED','RESUBMIT_REQUIRED')
                            DEFAULT 'PENDING',
    verified_by_officer_id  INT,
    verification_remarks    VARCHAR(500),
    uploaded_at             DATETIME DEFAULT CURRENT_TIMESTAMP,
    verified_at             DATETIME,
    INDEX idx_soldier_docs  (soldier_id),
    INDEX idx_verify_status (verification_status)
) ENGINE=InnoDB;

-- ============================================================
--  TABLE: grievances
--  Family complaints tracking
-- ============================================================
CREATE TABLE IF NOT EXISTS grievances (
    grievance_id        INT AUTO_INCREMENT PRIMARY KEY,
    soldier_id          INT NOT NULL,
    family_id           INT NOT NULL,
    grievance_number    VARCHAR(20) UNIQUE,            -- GRV-2024-0001
    subject             VARCHAR(200) NOT NULL,
    description         TEXT NOT NULL,
    category            ENUM('BENEFIT_DELAY','DOCUMENT_ISSUE','PENSION_PROBLEM',
                             'INFORMATION_REQUIRED','INCORRECT_DATA','OTHER'),
    status              ENUM('OPEN','IN_PROGRESS','RESOLVED','CLOSED') DEFAULT 'OPEN',
    response            TEXT,
    responded_by_id     INT,
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    resolved_at         DATETIME,
    INDEX idx_family_grievances (family_id),
    INDEX idx_griev_status      (status)
) ENGINE=InnoDB;

-- ============================================================
--  SAMPLE DATA (Optional — for testing)
-- ============================================================

-- Sample Officer account (password: Temp@1234)
INSERT INTO users (username, password, role, full_name, email, is_password_changed, is_active, created_at)
VALUES (
    'raj.kumar',
    '$2a$10$8K1p/a0dclxCnU3yH.DY9.GVBGk2hXqblq5JfNPjT5XkAn8E.xtmS',   -- Temp@1234
    'OFFICER',
    'Raj Kumar Singh',
    'raj.kumar@defence.gov.in',
    0,  -- Pehle login pe change karna hoga
    1,
    NOW()
);

-- Sample Shaheed record
INSERT INTO soldiers (service_number, full_name, rank, unit, regiment,
                      date_of_birth, date_of_joining, date_of_martyrdom,
                      place_of_martyrdom, martyrdom_category, awards,
                      case_status, application_id, created_by_admin_id)
VALUES (
    'IC-12345X', 'Havaldar Ramswaroop Yadav', 'Havaldar',
    '10 RAJPUT', 'RAJPUTANA RIFLES',
    '1985-03-15', '2005-06-01', '2024-01-26',
    'Northern Command, J&K',
    'BATTLE_CASUALTY',
    'Sena Medal, Army Chief Commendation',
    'PENDING',
    'SP-2024-00001',
    1
);

-- Sample Family member for above soldier
INSERT INTO family_members (soldier_id, full_name, relation, aadhar_number,
                             address, city, state, pincode, phone,
                             bank_account_number, bank_name, ifsc_code,
                             is_primary_beneficiary, has_login_access)
VALUES (
    1, 'Sunita Devi Yadav', 'WIFE', '1234-5678-9012',
    'Village Rampur, Post Ballia', 'Ballia', 'Uttar Pradesh', '277001',
    '9876543210', '12345678901234', 'State Bank of India', 'SBIN0012345',
    1, 1
);

-- Sample Family login (password: Family@123)
INSERT INTO users (username, password, role, full_name, email,
                   is_password_changed, is_active, linked_family_id, created_at)
VALUES (
    'FAM00001',
    '$2a$10$dTnYs3ROuF/i2p8D95Pl1eJi4T1DJVEhq.2J59mkd9GXt.bMTlBi.',  -- Family@123
    'FAMILY',
    'Sunita Devi Yadav',
    'sunita.yadav@family.in',
    0,
    1,
    1,
    NOW()
);

-- ============================================================
--  Summary
--  Admin   → admin@gov.in  / Admin@123
--  Officer → raj.kumar     / Temp@1234  (change on first login)
--  Family  → FAM00001      / Family@123 (change on first login)
-- ============================================================

SELECT 'Database setup complete! ✅' AS STATUS;
SELECT 'Default admin: admin@gov.in / Admin@123' AS LOGIN_INFO;
