create table boards (
  id varchar(36) not null ,
  name varchar(255) not null ,
  description text,
  category_id varchar(36) not null,
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET="utf8mb4";

create table categories (
	id varchar(36) not null ,
    name varchar(255) not null ,
    description text,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET="utf8mb4";

create table credentials (
    email varchar(255) not null ,
    password text not null ,
    profile_id varchar(36) not null ,
    unique index email_unique(email),
    primary key (profile_id)
) ENGINE=InnoDB DEFAULT CHARSET="utf8mb4";

create table groups (
    id varchar(36) not null ,
    name varchar(100) not null ,
    unique index name_unique(name),
    primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET="utf8mb4";

create table posts (
    id varchar(36) unique not null ,
    content text not null ,
    author_profile_id varchar(36) not null ,
    topic_id varchar(36) not null ,
    primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET="utf8mb4";

create table profiles (
    id varchar(36) unique not null ,
    username varchar(50) unique not null ,
    birthday date,
    title varchar(45),
    primary_group_id varchar(36),
    unique index username_unique(username),
    primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET="utf8mb4";

create table topics (
    id varchar(36) unique not null ,
    name varchar(100) not null ,
    board_id varchar(36) not null ,
    primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET="utf8mb4";

alter table boards add foreign key category_fk(category_id) references categories(id);
alter table credentials add foreign key profile_fk(profile_id) references profiles(id);
alter table posts add foreign key author_profile_fk(author_profile_id) references profiles(id);
alter table posts add foreign key topic_fk(topic_id) references topics(id);
alter table profiles add foreign key primary_group_fk(primary_group_id) references groups(id);
alter table topics add foreign key board_fk(board_id) references boards(id);

insert into groups(id, name) values ("1c55a568-19f5-498a-a9b6-37ebd7ad633c", "Default Group");
