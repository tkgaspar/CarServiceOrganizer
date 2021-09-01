CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR,
  password VARCHAR,
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);
CREATE TABLE IF NOT EXISTS REPREQUEST (
    repreqid INT PRIMARY KEY auto_increment,
    timestamp TIMESTAMP,
    clientname VARCHAR(20),
    licenceplate VARCHAR(20),
    vinnumber VARCHAR(20),
    defectdescription VARCHAR (1000),
    userid INT,
    foreign key (userid) references USERS(userid)
);

    CREATE TABLE IF NOT EXISTS SCHEDULE(
    scheduleid INT PRIMARY KEY auto_increment,
    mechanic VARCHAR(20),
    beginningtime TIME,
    endtime TIME,
    reparationdate DATE,
    foreign key (repreqid) references REPREQUEST (repreqid)
);