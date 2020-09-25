CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 192.168.31.138    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `review` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL,
  `content` text,
  `time_stamp` text NOT NULL,
  `star` int(11) DEFAULT '0',
  `id` varchar(45) NOT NULL,
  `contentid` int(11) NOT NULL,
  PRIMARY KEY (`num`),
  KEY `fk_review_member1_idx` (`id`),
  KEY `fk_review_tourplace1_idx` (`contentid`),
  CONSTRAINT `fk_review_member1` FOREIGN KEY (`id`) REFERENCES `member` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_review_tourplace1` FOREIGN KEY (`contentid`) REFERENCES `place` (`contentid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (78,'','음식은 괜찮으나... 사람이 너무 많아 서비스가 불 친절하다고 느낄 수 있습니다.','2019-08-14',4,'loucks',1923993),(79,'','볼링장 헬스장 수영장 등 축구 관련 컨텐츠 외에도 많은 부대시설이 존재 합니다.\r\n아이들과 함께가도 좋은 장소입니다.','2019-08-14',5,'loucks',131720),(81,'','유일한 단점은 냉면치곤 너무 비싸다는것뿐 맛은 훌륭함 면도 메밀함량이 높고 육수도 처음 맛보는 사람한텐 생소해서 호불호가 갈릴 수 있지만 중독되면 숯골냉면없인 살 수 없는 몸이 되버림...같이 나오는 지단이 냉면육수와 잘어우려져 지단의 고소한맛을 느낄수 있게 해준 이집 꿀팁은 동치미 무를 최대한 많이 넣어서 드시면 진짜 맛있습니다.','2019-08-14',4,'hb1398',1923993),(82,'','호국영령들에 대해 생각할 수 있는 시간을 가졌습니다. 시간 나실 때 꼭 들려보시면 좋을 것 같습니다.','2019-08-14',5,'yooco0618',126003),(87,'','조용하니 골프에 집중하기 좋습니다. 접근성이 낮은 것 빼고는 골프를 즐기기에는 나쁘지 않은 장소입니다.','2019-08-14',3,'loucks',130981),(88,'','같은 대전에 살면서 현충원에 들를 기회가 얼마나 있을까? 아마 현충원에 안치 되어 계시는 분의 가족이 아닌 이상 찾아갈 기회가 없을거 같다. 나 또한 그렇다. 초등학교 중학교를 다니면서 현충원은 현장체험 하는 곳 이라 많이 생각 했다. 이번에 대한사랑을 통해 고등학생이 되어서 처음으로 현충원에 갔다. 어렷을때에는 학교의 행사로 갔지만 대한사랑의 회원으로 들어가니 느낌이 달랐다. 많이 얘기를 들어본 임시정부의 일원이신, 곽낙원여사의 묘와 그 옆에 계시는 김인선생님의 묘지를 방문했다. 처음의로 곽낙원여사의 묘비문을 읽게 될 기회를 얻어서 굉장히 뿌듲했다. 곽낙원여사와 김인선생님의 생애를 들으니 그 때 당시의 나라면 이렇게 할 수 있었을까 생각 했다. 그 다음 김흥규 선생님의 묘에 방문했다. 처음에 답사 자료집을 보앗을 때 익숙치 않은 이름이라 생소 했지만 자료집을 읽어보니 왜 이러한 준이 역사 교과서에 수록되지 않은지 생각했다. 그 다음 김 신 장군의 묘비에 갔다. 비교적 최근에 돌아가셨는데 난 이 사실을 왜 이저 알았을까? 의문이 들었다. 공군으로써 우리나라를 지키셨다는 사실에 너무너무 멋지셨다. 이어서 우리는 연평해전과 연평도 포격사건의 용사분들이 안치 되어 있는 곳으로 갔다. 이 사건이 나와 같은 시대에 일어 났단게 실감이 나지 않았다. 틀히 연평해전을 모티브로 한 영화를 보았기 때문에 뭔가 턱 막히는 느낌을 받았다. 이 분들이 대전현충언에 안치 되었다는 사실을 지금 안게 부끄러웠다. 용사들의 묘비 앞에 있는 새거같은 음료수캔이 무엇이가 마음을 적적하게 만들었다. 이 이후로는 친일파 김창룡의 묘로 갔다. 왜 있는지 모르겠다. 이번 현추언 답사를 갔다 와서 많은 새로운 사실을 알게 되었고 진정한 역사 교육을 할려면 현충원에 꼭 가야하는거 갔다. 우리가 서고 있는 이 땅을 김창룡을 제외하고 오늘 만난 분들이 일궈낸 뜨거운 결과물 같아 멋지다고 생각했다. 기회가 된다면 성인이 된 이후 한 번 더 방문 하고 싶다.','2019-08-14',4,'hb1398',126003),(89,'','자기 말을 맡기고 승마장에서 관리하고(비용 발생) 마주가 승마 연습하는 곳이고 여기서 관리하는 말을 비용을 내고 승마 연습 할 수 있으며 조랑말 2마리로 어린이 조랑말 3바퀴 탈 수 있어요. 주차장 좁고, 화장실이 불편해요','2019-08-14',2,'hb1398',131579),(92,'','비싸지만 맛나요. 남기면 싸갈 수 있는데 포장비 2000원 발생합니다(압축자동포장기). 반찬으로 나오는 장아찌들이 별미네요. 오리누룽지백숙은 40분 걸리니 미리 전화하고 가야합니다. 자리는 2층에 식탁의자로 되어 있어 편하고 주차도 여유로워요.','2019-08-14',4,'hb1398',1923497),(93,'','전체적으로 까다롭지 않고\r\n그저 무난하게 라운딩해갈 수 있는\r\n코스 레이아웃이었습니다\r\n오랜만에 여유있는 라운딩이 가능한\r\n골프장이었기에 개인적으로는 만족스럽네요','2019-08-14',3,'hb1398',130981),(95,'','고기의 질도 최상이며 직원들의 서비스가 아주 좋습니다. \r\n서비스로 제공되는 음료수와 그 외 다른 음식들도 기대하고 가도 될 정도\r\n육회도 아주 맛있음','2019-08-14',5,'hb1398',1924258),(96,'','공기가 맑고 접근성은 좋았습니다.\r\n대전권에서 가장 괜찮은 코스라 생각하지만, 시설의 노후화로 인해 또 가고 싶다는 생각은 들지 않네요.','2019-08-14',3,'123123123',130981);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-14  9:17:27
