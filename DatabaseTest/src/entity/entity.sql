insert into "APP"."GLOCOMM_ACCOUNT" ("ID", "PASS", "USERACCOUNT_GLOCOMM_ID") values('user', 'pass', 'user');
insert into "APP"."GLOCOMM_ACCOUNT" ("ID", "PASS", "USERACCOUNT_GLOCOMM_ID") values('user1', 'pass', 'user1');
insert into "APP"."GLOCOMM_ACCOUNT" ("ID", "PASS", "USERACCOUNT_GLOCOMM_ID") values('user2', 'pass', 'user2');

insert into "APP"."USER_ACCOUNT" ("GLOCOMM_ID", "MONEY", "NAME", "POINT", "ROOM_NUMBER") values('user', 0, 'Ａさん', 200, 120);
insert into "APP"."USER_ACCOUNT" ("GLOCOMM_ID", "MONEY", "NAME", "POINT", "ROOM_NUMBER") values('user1', 0, 'Ｂさん', 300, 330);
insert into "APP"."USER_ACCOUNT" ("GLOCOMM_ID", "MONEY", "NAME", "POINT", "ROOM_NUMBER") values('user2', 0, 'Ｃさん', 150, 300);

insert into "APP"."SELLER_ACCOUNT" ("NUMBER", "ID", "MAIL", "PASSWORD") values(0, 'seller', 'test', 'pass');

insert into "APP"."ITEM" ("ID", "CATEGORY", "DESCRIPTION", "IMAGE_PATH", "LAST_EDIT", "NAME", "PUBLIC_STAT", "REGIST_DATE", "STOCK") values(0, 0, '説明', '/test.png', '2016-05-16', '勉強机', 0, '2016-05-16', 100);
insert into "APP"."ITEM" ("ID", "CATEGORY", "DESCRIPTION", "IMAGE_PATH", "LAST_EDIT", "NAME", "PUBLIC_STAT", "REGIST_DATE", "STOCK") values(1, 0, '説明', '/test.png', '2016-05-17', '小さな机', 0, '2016-05-16', 100);
insert into "APP"."ITEM" ("ID", "CATEGORY", "DESCRIPTION", "IMAGE_PATH", "LAST_EDIT", "NAME", "PUBLIC_STAT", "REGIST_DATE", "STOCK") values(2, 0, '説明', '/test.png', '2016-05-18', '大きな椅子', 0, '2016-05-16', 100);
insert into "APP"."ITEM" ("ID", "CATEGORY", "DESCRIPTION", "IMAGE_PATH", "LAST_EDIT", "NAME", "PUBLIC_STAT", "REGIST_DATE", "STOCK") values(3, 1, '説明', '/test.png', '2016-05-19', '座椅子', 0, '2016-05-16', 5000);
insert into "APP"."ITEM" ("ID", "CATEGORY", "DESCRIPTION", "IMAGE_PATH", "LAST_EDIT", "NAME", "PUBLIC_STAT", "REGIST_DATE", "STOCK") values(4, 1, '説明', '/test.png', '2016-06-26', '机', 0, '2016-06-26', 5000);
insert into "APP"."ITEM" ("ID", "CATEGORY", "DESCRIPTION", "IMAGE_PATH", "LAST_EDIT", "NAME", "PUBLIC_STAT", "REGIST_DATE", "STOCK") values(5, 2, '説明', '/test.png', '2016-06-27', '椅子', 0, '2016-06-26', 5000);

insert into "APP"."AVATAR" ("ID", "IMAGE_PATH", "NAME", "POINT") values(0, '/test.png', '真顔', 10);
insert into "APP"."AVATAR" ("ID", "IMAGE_PATH", "NAME", "POINT") values(1, '/test.png', '笑顔', 20);
insert into "APP"."AVATAR" ("ID", "IMAGE_PATH", "NAME", "POINT") values(2, '/test.png', 'ピース', 30);

insert into "APP"."REVIEW" ("ID", "COMMENT", "DATE", "GOOD_COUNT", "IMG_ID", "ITEM_ID", "STAR", "ACCOUNT_ID") values(0, 'あああ', '1991-11-10', 120, 0, 1, 3, 'user');
insert into "APP"."REVIEW" ("ID", "COMMENT", "DATE", "GOOD_COUNT", "IMG_ID", "ITEM_ID", "STAR", "ACCOUNT_ID") values(1, 'いいい', '2016-10-29', 11, 0, 1, 4, 'user1');
insert into "APP"."REVIEW" ("ID", "COMMENT", "DATE", "GOOD_COUNT", "IMG_ID", "ITEM_ID", "STAR", "ACCOUNT_ID") values(2, 'ううう', '2015-08-17', 0, 0, 1, 2, 'user2');
