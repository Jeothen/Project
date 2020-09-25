CREATE TABLE IF NOT EXISTS `member` (
  `id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  `phonenum` VARCHAR(45) NULL,
  `email` VARCHAR(100) NULL,
  `membertype` VARCHAR(45) NULL,
  `nickname` VARCHAR(45) NULL,
  `image` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `place` (
  `contentid` INT NOT NULL,
  `contenttypeid` INT NOT NULL,
  `mapx` DOUBLE NULL,
  `mapy` DOUBLE NULL,
  `overview` TEXT NULL,
  `tel` VARCHAR(100) NULL,
  `address` VARCHAR(45) NULL,
  `title` VARCHAR(100) NULL,
  `zipcode` VARCHAR(45) NULL,
  `image` VARCHAR(100) NULL,
  `homepage` VARCHAR(100) NULL,
  `areacode` INT NOT NULL,
  `sigunguCode` INT NULL,
  PRIMARY KEY (`contentid`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `review` (
  `num` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `content` TEXT NULL,
  `time_stamp` VARCHAR(45) NOT NULL,
  `id` VARCHAR(45) NOT NULL,
  `contentid` INT NOT NULL,
  PRIMARY KEY (`num`),
  INDEX `fk_review_member1_idx` (`id` ASC) VISIBLE,
  INDEX `fk_review_tourplace1_idx` (`contentid` ASC) VISIBLE,
  CONSTRAINT `fk_review_member1`
    FOREIGN KEY (`id`)
    REFERENCES `member` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_tourplace1`
    FOREIGN KEY (`contentid`)
    REFERENCES `place` (`contentid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `review_comment` (
  `comment_no` INT NOT NULL AUTO_INCREMENT,
  `text` TEXT NOT NULL,
  `time_Stamp` VARCHAR(45) NOT NULL,
  `id` VARCHAR(45) NOT NULL,
  `num` INT NOT NULL,
  INDEX `fk_review_comment_member1_idx` (`id` ASC) VISIBLE,
  INDEX `fk_review_comment_review1_idx` (`num` ASC) VISIBLE,
  PRIMARY KEY (`comment_no`),
  CONSTRAINT `fk_review_comment_member1`
    FOREIGN KEY (`id`)
    REFERENCES `member` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_comment_review1`
    FOREIGN KEY (`num`)
    REFERENCES `review` (`num`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `review_picture` (
  `picture_no` INT NOT NULL AUTO_INCREMENT,
  `num` INT NOT NULL,
  `review_picture_url` VARCHAR(100) NULL,
  INDEX `fk_review_picture_review1_idx` (`num` ASC) VISIBLE,
  PRIMARY KEY (`picture_no`),
  CONSTRAINT `fk_review_picture_review1`
    FOREIGN KEY (`num`)
    REFERENCES `review` (`num`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `repeat` (
  `content_no` INT NOT NULL AUTO_INCREMENT,
  `contentid` INT NOT NULL,
  `contentname` VARCHAR(45) NOT NULL,
  `contenttext` TEXT NULL,
  INDEX `fk_repeat_place1_idx` (`contentid` ASC) VISIBLE,
  PRIMARY KEY (`content_no`),
  CONSTRAINT `fk_repeat_place1`
    FOREIGN KEY (`contentid`)
    REFERENCES `place` (`contentid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `image` (
  `image_no` INT NOT NULL AUTO_INCREMENT,
  `contentid` INT NOT NULL,
  `originimgurl` VARCHAR(100) NOT NULL,
  INDEX `fk_image_place1_idx` (`contentid` ASC) VISIBLE,
  PRIMARY KEY (`image_no`),
  CONSTRAINT `fk_image_place1`
    FOREIGN KEY (`contentid`)
    REFERENCES `place` (`contentid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `samething` (
  `place_contentid` INT NOT NULL,
  `accomcount` VARCHAR(100) NULL,
  `chkbabycarriage` VARCHAR(45) NULL,
  `chkcreditcard` VARCHAR(45) NULL,
  `chkpet` VARCHAR(45) NULL,
  `parking` TEXT NULL,
  `infocenter` TEXT NULL,
  `restdate` VARCHAR(100) NULL,
  PRIMARY KEY (`place_contentid`),
  CONSTRAINT `fk_samething_place1`
    FOREIGN KEY (`place_contentid`)
    REFERENCES `place` (`contentid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `festival` (
  `festival_no` INT NOT NULL AUTO_INCREMENT,
  `contentid` INT NOT NULL,
  `agelimit` VARCHAR(100) NULL,
  `bookingplace` VARCHAR(100) NULL,
  `discountinfofestival` VARCHAR(100) NULL,
  `eventenddate` VARCHAR(45) NULL,
  `eventplace` TEXT NULL,
  `eventstartdate` VARCHAR(45) NULL,
  `placeinfo` TEXT NULL,
  `playtime` VARCHAR(45) NULL,
  `program` VARCHAR(45) NULL,
  `sponsor1` VARCHAR(100) NULL,
  `sponsor2` VARCHAR(100) NULL,
  `sponsor1tel` VARCHAR(45) NULL,
  `sponsor2tel` VARCHAR(45) NULL,
  `subevent` TEXT NULL,
  `usetimefestival` VARCHAR(100) NULL,
  `spendtimefestival` VARCHAR(100) NULL,
  INDEX `fk_festival_samething1_idx` (`contentid` ASC) VISIBLE,
  PRIMARY KEY (`festival_no`),
  CONSTRAINT `fk_festival_samething1`
    FOREIGN KEY (`contentid`)
    REFERENCES `samething` (`place_contentid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `course` (
  `course_no` INT NOT NULL AUTO_INCREMENT,
  `contentid` INT NOT NULL,
  `distance` VARCHAR(45) NULL,
  `taketime` VARCHAR(45) NULL,
  INDEX `fk_course_samething1_idx` (`contentid` ASC) VISIBLE,
  PRIMARY KEY (`course_no`),
  CONSTRAINT `fk_course_samething1`
    FOREIGN KEY (`contentid`)
    REFERENCES `samething` (`place_contentid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `cultural` (
  `culture_no` INT NOT NULL AUTO_INCREMENT,
  `contentid` INT NOT NULL,
  `parkingfee` VARCHAR(45) NULL,
  `usefee` TEXT NULL,
  `usetimeculture` TEXT NULL,
  INDEX `fk_cultural_samething1_idx` (`contentid` ASC) VISIBLE,
  PRIMARY KEY (`culture_no`),
  CONSTRAINT `fk_cultural_samething1`
    FOREIGN KEY (`contentid`)
    REFERENCES `samething` (`place_contentid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `restaurant` (
  `restaurant_no` INT NOT NULL AUTO_INCREMENT,
  `contentid` INT NOT NULL,
  `firstmenu` VARCHAR(100) NULL,
  `kidsfacility` VARCHAR(45) NULL,
  `opentimefood` TEXT NULL,
  `packing` VARCHAR(45) NULL,
  `reservationfood` TEXT NULL,
  `smoking` VARCHAR(45) NULL,
  `treatmenu` TEXT NULL,
  INDEX `fk_restaurant_samething1_idx` (`contentid` ASC) VISIBLE,
  PRIMARY KEY (`restaurant_no`),
  CONSTRAINT `fk_restaurant_samething1`
    FOREIGN KEY (`contentid`)
    REFERENCES `samething` (`place_contentid`)
    ON DELETE cascade
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `tourplace` (
  `tour_no` INT NOT NULL AUTO_INCREMENT,
  `contentid` INT NOT NULL,
  `expagerange` VARCHAR(45) NULL,
  `expguide` TEXT NULL,
  `usetime` TEXT NULL,
  INDEX `fk_tourplace_samething1_idx` (`contentid` ASC) VISIBLE,
  PRIMARY KEY (`tour_no`),
  CONSTRAINT `fk_tourplace_samething1`
    FOREIGN KEY (`contentid`)
    REFERENCES `samething` (`place_contentid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `shopping` (
  `shopping_no` INT NOT NULL AUTO_INCREMENT,
  `contentid` INT NOT NULL,
  `opentime` VARCHAR(100) NULL,
  `saleitem` VARCHAR(45) NULL,
  `shopguide` TEXT NULL,
  INDEX `fk_shopping_samething1_idx` (`contentid` ASC) VISIBLE,
  PRIMARY KEY (`shopping_no`),
  CONSTRAINT `fk_shopping_samething1`
    FOREIGN KEY (`contentid`)
    REFERENCES `samething` (`place_contentid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `leports` (
  `leports` INT NOT NULL AUTO_INCREMENT,
  `contentid` INT NOT NULL,
  `openperiod` VARCHAR(100) NULL,
  `parkingfeeleports` VARCHAR(45) NULL,
  `reservation` TEXT NULL,
  `usefeeleports` VARCHAR(45) NULL,
  `usetimeleports` VARCHAR(100) NULL,
  INDEX `fk_leports_samething1_idx` (`contentid` ASC) VISIBLE,
  PRIMARY KEY (`leports`),
  CONSTRAINT `fk_leports_samething1`
    FOREIGN KEY (`contentid`)
    REFERENCES `samething` (`place_contentid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `lodgment` (
  `lodgment` INT NOT NULL AUTO_INCREMENT,
  `contentid` INT NOT NULL,
  `barbecue` VARCHAR(45) NULL,
  `campfire` VARCHAR(45) NULL,
  `checkintime` VARCHAR(45) NULL,
  `checkouttime` VARCHAR(45) NULL,
  `chkcooking` VARCHAR(45) NULL,
  `foodplace` VARCHAR(45) NULL,
  `pickup` VARCHAR(100) NULL,
  `publicbath` VARCHAR(45) NULL,
  `reservationlodgin` VARCHAR(100) NULL,
  `reservationurl` VARCHAR(100) NULL,
  `roomcount` VARCHAR(45) NULL,
  `roomtype` VARCHAR(45) NULL,
  `subfacility` TEXT NULL,
  INDEX `fk_lodgment_samething1_idx` (`contentid` ASC) VISIBLE,
  PRIMARY KEY (`lodgment`),
  CONSTRAINT `fk_lodgment_samething1`
    FOREIGN KEY (`contentid`)
    REFERENCES `samething` (`place_contentid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `areacode` (
  `areacode` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`areacode`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `sigungucode` (
  `sigungucode` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `areacode_areacode` INT NOT NULL,
  PRIMARY KEY (`sigungucode`, `areacode_areacode`),
  INDEX `fk_sigungucode_areacode1_idx` (`areacode_areacode` ASC) VISIBLE,
  CONSTRAINT `fk_sigungucode_areacode1`
    FOREIGN KEY (`areacode_areacode`)
    REFERENCES `areacode` (`areacode`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `type` (
  `contenttypeid` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`contenttypeid`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS courseinfo (
 courseinfo_no INT NOT NULL AUTO_INCREMENT,
 subdetailalt TEXT NULL,
 subdetailimg TEXT NULL,
 subdetailoverview TEXT NULL,
 contentid INT NOT NULL,
 PRIMARY KEY (courseinfo_no),
 INDEX fk_courseinfo_place1_idx (contentid ASC) VISIBLE,
 CONSTRAINT fk_courseinfo_place1
   FOREIGN KEY (contentid)
   REFERENCES place (contentid)
   ON DELETE CASCADE
   ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO areacode(areacode,name) VALUES (1,'서울');
INSERT INTO areacode(areacode,name) VALUES (2,'인천');
INSERT INTO areacode(areacode,name) VALUES (3,'대전');
INSERT INTO areacode(areacode,name) VALUES (4,'대구');
INSERT INTO areacode(areacode,name) VALUES (5,'광주');
INSERT INTO areacode(areacode,name) VALUES (6,'부산');
INSERT INTO areacode(areacode,name) VALUES (7,'울산');
INSERT INTO areacode(areacode,name) VALUES (8,'세종특별자치시');
INSERT INTO areacode(areacode,name) VALUES (31,'경기도');
INSERT INTO areacode(areacode,name) VALUES (32,'강원도');
INSERT INTO areacode(areacode,name) VALUES (33,'충청북도');
INSERT INTO areacode(areacode,name) VALUES (34,'충청남도');
INSERT INTO areacode(areacode,name) VALUES (35,'경상북도');
INSERT INTO areacode(areacode,name) VALUES (36,'경상남도');
INSERT INTO areacode(areacode,name) VALUES (37,'전라북도');
INSERT INTO areacode(areacode,name) VALUES (38,'전라남도');
INSERT INTO areacode(areacode,name) VALUES (39,'제주도');
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'강남구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'강동구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'강북구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'강서구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'관악구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (6,'광진구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (7,'구로구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (8,'금천구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (9,'노원구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (10,'도봉구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (11,'동대문구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (12,'동작구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (13,'마포구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (14,'서대문구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (15,'서초구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (16,'성동구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (17,'성북구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (18,'송파구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (19,'양천구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (20,'영등포구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (21,'용산구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (22,'은평구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (23,'종로구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (24,'중구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (25,'중랑구',1);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'강화군',2);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'계양구',2);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'미추홀구',2);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'남동구',2);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'동구',2);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (6,'부평구',2);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (7,'서구',2);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (8,'연수구',2);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (9,'옹진군',2);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (10,'중구',2);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'대덕구',3);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'동구',3);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'서구',3);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'유성구',3);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'중구',3);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'남구',4);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'달서구',4);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'달성군',4);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'동구',4);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'북구',4);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (6,'서구',4);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (7,'수성구',4);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (8,'중구',4);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'광산구',5);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'남구',5);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'동구',5);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'북구',5);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'서구',5);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'강서구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'금정구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'기장군',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'남구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'동구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (6,'동래구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (7,'부산진구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (8,'북구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (9,'사상구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (10,'사하구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (11,'서구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (12,'수영구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (13,'연제구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (14,'영도구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (15,'중구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (16,'해운대구',6);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'중구',7);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'남구',7);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'동구',7);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'북구',7);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'울주군',7);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'세종특별자치시',8);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'가평군',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'고양시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'과천시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'광명시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'광주시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (6,'구리시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (7,'군포시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (8,'김포시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (9,'남양주시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (10,'동두천시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (11,'부천시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (12,'성남시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (13,'수원시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (14,'시흥시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (15,'안산시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (16,'안성시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (17,'안양시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (18,'양주시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (19,'양평군',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (20,'여주시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (21,'연천군',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (22,'오산시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (23,'용인시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (24,'의왕시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (25,'의정부시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (26,'이천시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (27,'파주시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (28,'평택시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (29,'포천시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (30,'하남시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (31,'화성시',31);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'강릉시',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'고성군',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'동해시',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'삼척시',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'속초시',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (6,'양구군',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (7,'양양군',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (8,'영월군',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (9,'원주시',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (10,'인제군',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (11,'정선군',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (12,'철원군',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (13,'춘천시',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (14,'태백시',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (15,'평창군',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (16,'홍천군',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (17,'화천군',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (18,'횡성군',32);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'괴산군',33);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'단양군',33);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'보은군',33);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'영동군',33);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'옥천군',33);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (6,'음성군',33);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (7,'제천시',33);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (8,'진천군',33);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (9,'청원군',33);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (10,'청주시',33);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (11,'충주시',33);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (12,'증평군',33);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'공주시',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'금산군',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'논산시',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'당진시',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'보령시',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (6,'부여군',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (7,'서산시',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (8,'서천군',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (9,'아산시',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (11,'예산군',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (12,'천안시',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (13,'청양군',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (14,'태안군',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (15,'홍성군',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (16,'계룡시',34);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'경산시',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'경주시',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'고령군',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'구미시',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'군위군',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (6,'김천시',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (7,'문경시',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (8,'봉화군',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (9,'상주시',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (10,'성주군',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (11,'안동시',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (12,'영덕군',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (13,'영양군',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (14,'영주시',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (15,'영천시',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (16,'예천군',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (17,'울릉군',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (18,'울진군',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (19,'의성군',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (20,'청도군',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (21,'청송군',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (22,'칠곡군',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (23,'포항시',35);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'거제시',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'거창군',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'고성군',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'김해시',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'남해군',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (6,'마산시',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (7,'밀양시',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (8,'사천시',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (9,'산청군',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (10,'양산시',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (12,'의령군',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (13,'진주시',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (14,'진해시',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (15,'창녕군',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (16,'창원시',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (17,'통영시',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (18,'하동군',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (19,'함안군',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (20,'함양군',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (21,'합천군',36);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'고창군',37);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'군산시',37);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'김제시',37);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'남원시',37);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'무주군',37);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (6,'부안군',37);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (7,'순창군',37);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (8,'완주군',37);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (9,'익산시',37);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (10,'임실군',37);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (11,'장수군',37);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (12,'전주시',37);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (13,'정읍시',37);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (14,'진안군',37);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'강진군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'고흥군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'곡성군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'광양시',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (5,'구례군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (6,'나주시',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (7,'담양군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (8,'목포시',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (9,'무안군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (10,'보성군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (11,'순천시',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (12,'신안군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (13,'여수시',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (16,'영광군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (17,'영암군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (18,'완도군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (19,'장성군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (20,'장흥군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (21,'진도군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (22,'함평군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (23,'해남군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (24,'화순군',38);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (1,'남제주군',39);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (2,'북제주군',39);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (3,'서귀포시',39);
INSERT INTO sigunguCode(sigungucode,name,areacode_areacode) VALUES (4,'제주시',39);

INSERT INTO type (contenttypeid,name) values(12,"관광지");
INSERT INTO type (contenttypeid,name) values(14,"문화시설");
INSERT INTO type (contenttypeid,name) values(15,"축제공연행사");
INSERT INTO type (contenttypeid,name) values(25,"여행코스");
INSERT INTO type (contenttypeid,name) values(28,"레포츠");
INSERT INTO type (contenttypeid,name) values(32,"숙박");
INSERT INTO type (contenttypeid,name) values(38,"쇼핑");
INSERT INTO type (contenttypeid,name) values(39,"음식점");
