CREATE TABLE IF NOT EXISTS member (
    userId BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(10) NOT NULL,
    phone VARCHAR(255) UNIQUE,
    roles VARCHAR(255),
    userStatus VARCHAR(20) NOT NULL,
    profileImage TEXT,
    isDelete BOOLEAN DEFAULT FALSE,
    createdAt     TIMESTAMP,
    lastModifiedDate  TIMESTAMP,
    createdBy     varchar(255),
    modifiedBy     varchar(255)
    );


CREATE TABLE IF NOT EXISTS cv (
    cvId BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    name VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(255),
    selfIntroduction TEXT,
    developmentJob VARCHAR(255),
    imageUrl TEXT,
    birthYear VARCHAR(255),
    birthMonth VARCHAR(255),
    birthDay VARCHAR(255),
    isDelete BOOLEAN DEFAULT FALSE,
    createdAt TIMESTAMP,
    lastModifiedDate TIMESTAMP,
    createdBy BIGINT,
    createdAt     TIMESTAMP,
    lastModifiedDate  TIMESTAMP,
    createdBy     varchar(255),
    modifiedBy     varchar(255),
    FOREIGN KEY (createdBy) REFERENCES user(userId)

    );

CREATE TABLE IF NOT EXISTS cv_skill_stack (
    cvSkillStackId BIGINT AUTO_INCREMENT PRIMARY KEY,
    skillStackId BIGINT,
    cvId BIGINT,
    FOREIGN KEY (skillStackId) REFERENCES skill_stack(skillStackId) ON DELETE CASCADE,
    FOREIGN KEY (cvId) REFERENCES cv(cvId) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS link (
    linkId BIGINT AUTO_INCREMENT PRIMARY KEY,
    linkName VARCHAR(255),
    linkAddress VARCHAR(255),
    cvId BIGINT,
    FOREIGN KEY (cvId) REFERENCES cv(cvId) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS portfolio (
    portfolioId BIGINT AUTO_INCREMENT PRIMARY KEY,
    portfolioAddress VARCHAR(255),
    cvId BIGINT,
    FOREIGN KEY (cvId) REFERENCES cv(cvId) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS custom_section (
    customSectionId BIGINT AUTO_INCREMENT PRIMARY KEY,
    customName VARCHAR(255),
    customContent TEXT,
    cvId BIGINT,
    FOREIGN KEY (cvId) REFERENCES cv(cvId) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS career (
    careerId BIGINT AUTO_INCREMENT PRIMARY KEY,
    joinYear VARCHAR(10),
    joinMonth VARCHAR(10),
    retirementYear VARCHAR(10),
    retirementMonth VARCHAR(10),
    companyName VARCHAR(255),
    duty VARCHAR(255),
    developmentJob VARCHAR(255),
    description TEXT,
    cvId BIGINT,
    FOREIGN KEY (cvId) REFERENCES cv(cvId) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS career_skill_stack (
    careerSkillStackId BIGINT AUTO_INCREMENT PRIMARY KEY,
    careerId BIGINT,
    skillStackId BIGINT NOT NULL,
    FOREIGN KEY (careerId) REFERENCES career(careerId) ON DELETE CASCADE,
    FOREIGN KEY (skillStackId) REFERENCES skill_stack(skillStackId)
    );

CREATE TABLE IF NOT EXISTS education (
    educationId BIGINT AUTO_INCREMENT PRIMARY KEY,
    admissionYear VARCHAR(255),
    admissionMonth VARCHAR(255),
    graduationYear VARCHAR(255),
    graduationMonth VARCHAR(255),
    schoolName VARCHAR(255),
    major VARCHAR(255),
    degree VARCHAR(255),
    description TEXT,
    cvId BIGINT,
    FOREIGN KEY (cvId) REFERENCES cv(cvId) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS project (
    projectId BIGINT AUTO_INCREMENT PRIMARY KEY,
    part VARCHAR(255),
    startYear VARCHAR(255),
    startMonth VARCHAR(255),
    endYear VARCHAR(255),
    endMonth VARCHAR(255),
    projectSubject VARCHAR(255),
    description TEXT,
    link VARCHAR(255),
    cvId BIGINT,
    FOREIGN KEY (cvId) REFERENCES cv(cvId) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS project_skill_stack (
    projectSkillStackId BIGINT AUTO_INCREMENT PRIMARY KEY,
    projectId BIGINT,
    skillStackId BIGINT,
    FOREIGN KEY (projectId) REFERENCES project(projectId) ON DELETE CASCADE,
    FOREIGN KEY (skillStackId) REFERENCES skill_stack(skillStackId) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS skill_stack (
   skill_stack_id BIGINT AUTO_INCREMENT PRIMARY KEY,
   skill_name VARCHAR(255) NOT NULL
    );







