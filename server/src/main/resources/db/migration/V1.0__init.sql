create table if not exists member
(
    id bigint auto_increment,
    uuid varchar(50),
    email varchar(255) not null,
    password varchar(255) not null,
    name varchar(10) not null,
    phone varchar(255),
    user_status varchar(20) not null,
    profile_image text,
    is_delete boolean default false,
    created_at timestamp,
    last_modified_at timestamp,
    created_by varchar(255),
    modified_by varchar(255),

    constraint pk_member_id primary key (id),
    constraint uk_member_email unique (email),
    constraint uk_member_phone unique (phone)
    );

create table if not exists user_roles
(
     user_id   bigint not null,
     roles     varchar(255)
);

create table if not exists cv
(
    cv_id bigint auto_increment,
    title varchar(255) not null,
    email varchar(255),
    name varchar(255),
    address varchar(255),
    phone varchar(255),
    self_introduction text,
    development_job varchar(255),
    image_url text,
    birth_year varchar(255),
    birth_month varchar(255),
    birth_day varchar(255),
    is_delete boolean default false,
    created_at     timestamp,
    last_modified_at  timestamp,
    created_by     varchar(255),
    modified_by     varchar(255),
    user_id bigint,

    constraint pk_cv_cv_id primary key (cv_id)
    );

alter table cv
    add constraint fk_cv_user_id
        foreign key (user_id) references member(id);

create table if not exists skill_stack
(
    skill_stack_id bigint auto_increment,
    skill_name varchar(255) not null,

    constraint pk_skill_stack_skill_stack_id primary key (skill_stack_id)
    );

create table if not exists cv_skill_stack
(
    cv_skill_stack_id bigint auto_increment,
    skill_stack_id bigint,
    cv_id bigint,

    constraint pk_cv_skill_stack_cv_skill_stack_id primary key (cv_skill_stack_id)
    );

alter table cv_skill_stack
    add constraint fk_cv_skill_stack_cv_id
        foreign key (cv_id) references cv(cv_id) on delete cascade;

alter table cv_skill_stack
    add constraint fk_cv_skill_stack_skill_stack_id
        foreign key (skill_stack_id) references skill_stack(skill_stack_id) on delete cascade;

create table if not exists link
(
    link_id bigint auto_increment,
    link_name varchar(255),
    link_address varchar(255),
    cv_id bigint,

    constraint pk_link_link_id primary key (link_id)
    );

alter table link
    add constraint fk_link_cv_id
        foreign key (cv_id) references cv(cv_id) on delete cascade;

create table if not exists portfolio
(
    portfolio_id bigint auto_increment,
    portfolio_address varchar(255),
    cv_id bigint,

    constraint pk_portfolio_portfolio_id primary key (portfolio_id)
    );

alter table portfolio
    add constraint fk_portfolio_cv_id
        foreign key (cv_id) references cv(cv_id) on delete cascade;

create table if not exists custom_section
(
    custom_section_id bigint auto_increment,
    custom_name varchar(255),
    custom_content text,
    cv_id bigint,

    constraint pk_custom_section_custom_section_id primary key (custom_section_id)
    );

alter table custom_section
    add constraint fk_custom_section_cv_id
        foreign key (cv_id) references cv(cv_id) on delete cascade;

create table if not exists career
(
    career_id bigint auto_increment,
    join_year varchar(10),
    join_month varchar(10),
    retirement_year varchar(10),
    retirement_month varchar(10),
    company_name varchar(255),
    duty varchar(255),
    development_job varchar(255),
    description text,
    cv_id bigint,

    constraint pk_career_career_id primary key (career_id)
    );

alter table career
    add constraint fk_career_cv_id
        foreign key (cv_id) references cv(cv_id) on delete cascade;

create table if not exists career_skill_stack
(
    career_skill_stack_id bigint auto_increment,
    career_id bigint,
    skill_stack_id bigint not null,

    constraint pk_career_skill_stack_career_skill_stack_id primary key (career_skill_stack_id)
    );

alter table career_skill_stack
    add constraint fk_career_skill_stack_career_id
        foreign key (career_id) references career(career_id) on delete cascade;

alter table career_skill_stack
    add constraint fk_career_skill_stack_skill_stack_id
        foreign key (skill_stack_id) references skill_stack(skill_stack_id);

create table if not exists education
(
    education_id bigint auto_increment,
    admission_year varchar(255),
    admission_month varchar(255),
    graduation_year varchar(255),
    graduation_month varchar(255),
    school_name varchar(255),
    major varchar(255),
    degree varchar(255),
    description text,
    cv_id bigint,

    constraint pk_education_education_id primary key (education_id)
    );

alter table education
    add constraint fk_education_cv_id
        foreign key (cv_id) references cv(cv_id) on delete cascade;

create table if not exists project
(
    project_id bigint auto_increment,
    part varchar(255),
    start_year varchar(255),
    start_month varchar(255),
    end_year varchar(255),
    end_month varchar(255),
    project_subject varchar(255),
    description text,
    link varchar(255),
    cv_id bigint,

    constraint pk_project_project_id primary key (project_id)
    );

alter table project
    add constraint fk_project_cv_id
        foreign key (cv_id) references cv(cv_id) on delete cascade;

create table if not exists project_skill_stack
(
    project_skill_stack_id bigint auto_increment,
    project_id bigint,
    skill_stack_id bigint,

    constraint pk_project_skill_stack_project_skill_stack_id primary key (project_skill_stack_id)
    );

alter table project_skill_stack
    add constraint fk_project_skill_stack_project_id
        foreign key (project_id) references project(project_id) on delete cascade;

alter table project_skill_stack
    add constraint fk_project_skill_stack_skill_stack_id
        foreign key (skill_stack_id) references skill_stack(skill_stack_id) on delete cascade;