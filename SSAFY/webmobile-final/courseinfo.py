import pprint
import pymysql
import requests
import webbrowser
from bs4 import BeautifulSoup as bs
from selenium import webdriver

tabledict = {12 :'tourplace', 14 : 'culture' , 15 :'festival', 25 : 'course', 28 : 'leports' , 32 : 'lodgment', 38 : 'shopping', 39 : 'restaurant'}

URL = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=gAgIdPkL9wh55d59ACpjljEduRtTrvBooU9ZLJJmtESF%2Fyfe3i1F65XlDslHoWTjN10OxyuoRJXavvQpnJRLZw%3D%3D&contentTypeId=&areaCode=&sigunguCode=&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=30000&pageNo=1&_type=json'
res = requests.get(URL)
respon = res.json()
for i in range(25344):
    print('i = {}'.format(i))
    temp = respon['response']['body']['items']['item'][i]
    contentid = temp['contentid']
    contenttypeid = temp['contenttypeid']
    if contenttypeid == 25:
        # 공통정보에서 overview 가지고 오기 ( 웹 크롤링)
        commonurl = 'http://api.visitkorea.or.kr/guide/tourDetail.do?contentId={}&langtype=KOR&typeid={}&oper=area&burl=&contentTypeId=&areaCode=&sigunguCode=&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1'.format(
            contentid, contenttypeid)
        path = "C:/chromedriver"
        chrome_options = webdriver.ChromeOptions()
        chrome_options.add_argument('headless')
        driver = webdriver.Chrome(path, chrome_options=chrome_options)
        driver.get(commonurl)
        html = driver.page_source
        soup = bs(html, 'html.parser')
        table = soup.find('div', class_="summary")
        overview = str(table.find("p").get_text()).strip()
        tempdict = {}
        table2 = soup.find("div", id="coinfo").find("div",class_="tourView")
        lists = table2.find_all("div",class_="txtData01")
        nums = 1
        for listz in lists :
            subdetailalt, subdetailimg, subdetailoverview = '', '', ''  # 제목 # 이미지 # 개요
            if listz.find("h3",class_="h3_text") :
                temptitle = str(listz.find("h3",class_="h3_text"))
                temptitle = temptitle.lstrip('<h3 class="h3_text">')
                temptitle = temptitle.lstrip('rong>')
                temptitle = temptitle.strip('<strong>')
                temptitle = temptitle[1:].strip()
                temptitle = temptitle.lstrip('코스  : </strong>').strip()
                temptitle = temptitle.rstrip("</h3>")
                temptitle = str(nums) + '코스: ' + temptitle
                subdetailalt = temptitle
                nums +=1
            if listz.find("li",class_="courseImg") :
                subdetailimg = listz.find("img").get('src')

                if listz.find("li", class_="courseTxt"):
                    tempoverview = str(listz.find("li", class_="courseTxt"))
                    tempoverview = tempoverview.lstrip(
                        '<li class="courseTxt"><strong>&lt;&lt; 코스개요 &gt;&gt; </strong><br/>')
                    tempoverview = tempoverview.rstrip("</li>")
                    subdetailoverview = tempoverview
            else :
                temp = str(listz.find("ul",class_="courseCon"))
                temp = temp.lstrip('<ul class="courseCon">').strip().lstrip('<li><strong>&lt;&lt; 코스개요 &gt;&gt; </strong><br/>')
                temp = temp.rstrip("</ul>").strip().rstrip("</li>")
                subdetailoverview = temp
            conn = pymysql.connect(host='localhost', port=3306, user='root', password='123123',
                                   db='mydb', charset='utf8')
            coursesql = "INSERT INTO courseinfo (subdetailalt, subdetailimg, subdetailoverview, contentid) VALUES (%s,%s,%s,%s)"
            courseval = (str(subdetailalt), str(subdetailimg), str(subdetailoverview), int(contentid))
            curs = conn.cursor()
            curs.execute(coursesql, courseval)
            conn.commit()

conn.close()