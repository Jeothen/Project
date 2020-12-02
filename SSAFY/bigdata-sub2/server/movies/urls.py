from django.conf.urls import include, url
from django.urls import path
from . import views
from . import parse
# url('auth/signup-many/$', auth_views.signup_many, name='sign_up_many'),
urlpatterns = [
    url(r'^movie/get/',parse.get_movies), # 영화기록
    url(r'^movie/get/ratings/',parse.get_ratings), # 평점기록
    url(r'^movie/get/avg_rate/',parse.create_avg_rating),

    url(r'^movie/list/',views.MovieListView.as_view()), # 영화정보 요청, 필터링 가능
    url(r'^movie/detail/(?P<slug>[-\w]+)/$', views.MovieDetailView.as_view()), # 상세페이지
    url(r'^movie/update/(?P<slug>[-\w]+)/$', views.MovieUpdateView.as_view()), # 수정페이지
    url(r'^movie/delete/(?P<slug>[-\w]+)/$', views.MovieDeleteView.as_view()), # 삭제페이지
                            # <str:slug> 
]
