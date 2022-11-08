select name , age from minions ;



UPDATE minions SET name=lower(name) ,age= age+1 where  id = 2 ;


UPDATE newpurchase
SET receive_qty=20,pub_lang='Hindi',receive_dt='2008-07-10'
WHERE purch_price>50;

DROP DATABASE `minions_db`;

