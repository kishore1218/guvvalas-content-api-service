create table COURSE(
	ID SERIAL primary key,
	URL VARCHAR(200) not null,
	COURSE_NAME VARCHAR(200) unique not null,
	DESCRIPTION VARCHAR(500),
	LOGO_PATH varchar(500),
	IS_ACTIVE BOOLEAN default false,
	IS_PUBLISH BOOLEAN default FALSE
);

create table CHAPTER(
	ID SERIAL primary key,
	CHAPTER_CODE VARCHAR(200) unique not null,
	CHAPTER_NAME VARCHAR(200) unique not null,
	DESCRIPTION VARCHAR(500),
	CHAPTER_CONTENT VARCHAR(5024),
	SEQ INT not null,
	COURSE_ID INT not null,
	IS_ACTIVE BOOLEAN default false,
	IS_PUBLISH BOOLEAN default false,
	FOREIGN KEY(COURSE_ID) REFERENCES course(id)
);




insert into course(url,course_name,description) values('/kubernetes','Kubernetes','kuberntes');

insert into chapter(chapter_name,description,course_id,seq) values('Kubernetes Intro','Kubernetes Intro',1,1);

insert into chapter(chapter_name,description,course_id,seq) values('Kubernetes Architecture','Kubernetes Architecture',1,2);


insert into course(url,course_name,description) values('/hazelcast','Hazelcast','hazelcast');

insert into chapter(chapter_name,description,course_id,seq) values('hazelcast Intro','hazelcast Intro',2,1);

insert into chapter(chapter_name,description,course_id,seq) values('hazelcast Architecture','hazelcast Architecture',2,2);


insert into course(url,course_name,description) values('/CoreJava','core_java','CoreJava');



  GRANT ALL ON course TO admin;

   GRANT ALL ON chapter TO admin;

   GRANT USAGE, SELECT ON SEQUENCE course_id_seq TO admin;

   GRANT USAGE, SELECT ON SEQUENCE chapter_id_seq TO admin;