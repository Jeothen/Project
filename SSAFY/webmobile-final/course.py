import pprint
import pymysql
import requests
import webbrowser
from bs4 import BeautifulSoup as bs
from selenium import webdriver

URL = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=gAgIdPkL9wh55d59ACpjljEduRtTrvBooU9ZLJJmtESF%2Fyfe3i1F65XlDslHoWTjN10OxyuoRJXavvQpnJRLZw%3D%3D&contentTypeId=&areaCode=&sigunguCode=&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=30000&pageNo=1&_type=json'
res = requests.get(URL)
respon = res.json()
for i in range(2123):
    print('i = {}'.format(i))
    cnt = 0
    temp = respon['response']['body']['items']['item'][i+1037]
    contentid = temp['contentid']
    contenttypeid = temp['contenttypeid']
    if contenttypeid == 25:
        cnt +=1
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
        table2 = soup.find("div", class_="txtData none").find("ul").select('li')
        distance, taketime ='', ''

        for element in table2:
            catag = str(element.find("strong").get_text().rstrip(' :')).strip()
            tempstr = str(element)
            if catag == '총 거리':
                leftcut = tempstr.lstrip("<li><strong>{} : </strong>".format(catag))
                cutting = leftcut.rstrip('</li>')
                distance = cutting
                continue
            if catag == '관람소요시간' or '소요시간':
                leftcut = tempstr.lstrip("<li><strong>{} : </strong>".format(catag))
                cutting = leftcut.rstrip('</li>')
                taketime = cutting
                continue

        conn = pymysql.connect(host='localhost', port=3306, user='root', password='123123',db='mydb', charset='utf8')
        coursesql = "INSERT INTO course (contentid, distance, taketime) VALUES (%s,%s,%s)"
        courseval = (int(contentid), str(distance),str(taketime))
        curs = conn.cursor()
        curs.execute(coursesql, courseval)
        conn.commit()

conn.close()