CREATE TABLE SELLER_ACCOUNT (NUMBER INTEGER NOT NULL, ID VARCHAR(30), MAIL VARCHAR(30), PASSWORD VARCHAR(20), PRIMARY KEY (NUMBER))
CREATE TABLE LUNCH (NAME VARCHAR(255) NOT NULL, PRICE INTEGER, STOCK INTEGER, PRIMARY KEY (NAME))
CREATE TABLE STOCK (ID INTEGER NOT NULL, CATEGORY INTEGER, "COUNT" INTEGER, DESCRIPTION VARCHAR(255), IMG_PATH VARCHAR(255), LAST_EDIT DATE, NAME VARCHAR(255), OPEN_INFO INTEGER, PRIMARY KEY (ID))
CREATE TABLE BBS (ID INTEGER NOT NULL, ACCOUNT_ID VARCHAR(255), COMMENT VARCHAR(255), DATE DATE, IMAGE_ID INTEGER, PARENT INTEGER, PRIMARY KEY (ID))
CREATE TABLE ITEM (ID INTEGER NOT NULL, CATEGORY INTEGER, DESCRIPTION VARCHAR(255), IMAGE_PATH VARCHAR(255), LAST_EDIT DATE, NAME VARCHAR(255), PUBLIC_STAT INTEGER, REGIST_DATE DATE, STOCK INTEGER, PRIMARY KEY (ID))
CREATE TABLE AVATAR (ID INTEGER NOT NULL, IMAGEID INTEGER, NAME VARCHAR(255), POINT INTEGER, PRIMARY KEY (ID))
CREATE TABLE USER_ACCOUNT (GLOCOMM_ID VARCHAR(30) NOT NULL, MONEY INTEGER, NAME VARCHAR(255), POINT INTEGER, ROOM_NUMBER INTEGER, PRIMARY KEY (GLOCOMM_ID))
CREATE TABLE GLOCOMMACCOUNT (ID VARCHAR(255) NOT NULL, PASS VARCHAR(255), USERACCOUNT_GLOCOMM_ID VARCHAR(30), PRIMARY KEY (ID))
CREATE TABLE PRESERVEDAVATAR (ID INTEGER NOT NULL, ACCOUNT_ID VARCHAR(30), DATE DATE, AVATAR_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE REVIEW (ID INTEGER NOT NULL, ACCOUNT_ID VARCHAR(30), COMMENT VARCHAR(255), DATE DATE, GOOD_COUNT INTEGER, IMG_ID INTEGER, ITEM_ID INTEGER, STAR INTEGER, PRIMARY KEY (ID))
CREATE TABLE SALES (ID INTEGER NOT NULL, ACCOUNT_ID VARCHAR(30), COUNT INTEGER, DATE DATE, ITEM_ID INTEGER, PRICE INTEGER, PRIMARY KEY (ID))
CREATE TABLE CART (ID INTEGER NOT NULL, ACCOUNT_ID VARCHAR(30), COUNT INTEGER, ITEM_ID INTEGER, PRIMARY KEY (ID))
ALTER TABLE GLOCOMMACCOUNT ADD CONSTRAINT GLCMMCSRCCNTGLCMMD FOREIGN KEY (USERACCOUNT_GLOCOMM_ID) REFERENCES USER_ACCOUNT (GLOCOMM_ID)
ALTER TABLE PRESERVEDAVATAR ADD CONSTRAINT PRSRVEDAVATARVTRID FOREIGN KEY (AVATAR_ID) REFERENCES AVATAR (ID)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(15), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
