# API LIST

아래 url에 요청을 보내면 그에 따라 응답합니다.

### api/movie/list/

* GET 메서드 핸들러를 사용해야합니다.

* Page_size = 10이므로 10개의 데이터를 불러옵니다.
* 데이터 : ["id" , "title", "genres_array":[...]] , "views","slug","avg_rating"]
* 영화제목과 타이틀의 필터기능을 적용할 수 있습니다. ex) ?title=Toy&genres=Comedy 
* 페이지를 불러올 수 있습니다. ex) ?page=5



### api/movie/detail/\<slug>/

* GET 요청시 영화 상세 정보를 응답합니다. 
* 해당영화가 존재하지 않을 경우 404 Not Found 응답을 반환합니다.



### api/movie/update/\<slug>/

- PUT 요청시 영화 수정이 가능합니다
- 필수항목 : title, id
- 해당영화가 존재하지 않을 경우 404 Not Found 응답을 반환합니다.



### api/movie/delete/\<slug>/

- Delete 요청시 영화 삭제가 가능합니다
- 해당영화가 존재하지 않을 경우 404 Not Found 응답을 반환합니다.



### api/auth/list/

* GET 메서드 핸들러를 사용해야합니다.

* Page_size = 10이므로 10개의 데이터를 불러옵니다.
* fields = ('id', 'username', 'is_staff', 'gender', 'age', 'occupation','slug','watched_movie')



### api/auth/\<slug>/

* GET 메서드 핸들러를 사용해야합니다.

* 유저 상세보기 페이지입니다. 





# 명세

## Req. 1- 영화 검색 기능 확장

### 1-1 연령대 기준 영화 검색

특정 연령대의 유저가 많이 본 영화를 검색할 수 있도록 합니다.

