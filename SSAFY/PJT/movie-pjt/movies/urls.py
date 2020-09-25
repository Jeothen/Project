from django.urls import path
from . import views

app_name = 'movies'
urlpatterns = [
    path('', views.list, name='list'),
    path('actors/', views.actorlist, name='actorlist'),
    path('directors/', views.directorlist, name='directorlist'),
    path('<int:movie_pk>/', views.detail, name='detail'),
    path('<int:movie_pk>/score', views.score_create, name='score_create'),
    path('<int:movie_pk>/score/<int:score_pk>/delete/',views.score_delete,name='score_delete'),
    path('search/', views.search, name='search'),
    path('<int:movie_pk>/like/', views.like, name='like'),
    path('genremovie/<int:genre_pk>/', views.genremovie, name='genremovie'),
    path('yearmovie/<int:year>', views.yearmovie, name='yearmovie'),
    path('agemovie/<age>/', views.agemovie, name='agemovie'),
    path('manywatching/',views.manywatching,name='manywatching'),
    path('notwatching/',views.notwatching,name='notwatching'),
    path('genrerecommend/',views.genrerecommend,name='genrerecommend'),
    path('actordetail/<int:actor_pk>',views.actordetail,name='actordetail'),
    path('directordetail/<int:director_pk>',views.directordetail,name='directordetail'),
    path('<int:actor_pk>/likeactor/',views.likeactor,name='likeactor'),
    path('<int:director_pk>/likedirector/',views.likedirector,name='likedirector'),
    path('actorrecommend/',views.actorrecommend,name='actorrecommend'),
    path('directorrecommend/',views.directorrecommend,name='directorrecommend'),
    path('mypage/',views.mypage,name='mypage'),
]
