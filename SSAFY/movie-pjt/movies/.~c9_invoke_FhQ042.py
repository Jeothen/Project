from django.urls import path
from . import views

app_name = 'movies'
urlpatterns = [
    path('', views.list, name='list'),
    path('<int:movie_pk>/', views.detail, name='detail'),
    path('<int:movie_pk>/score', views.score_create, name='score_create'),
    path('<int:movie_pk>/score/<int:score_pk>/delete/',views.score_delete,name='score_delete'),
    path('search/', views.search, name='search'),
    path('<int:post_pk>/like/', views.like, name='like'),
    path('searching/',views.searching,name='searching'),
    path('genremovie/<int:genre_pk>/', views.genremovie, name='genremovie'),
    path('datemovie/<string:date_pk>/', views.datemovie, name='datemovie'),
    path('agemovie/<int:age_pk>/', views.agemovie, name='agemovie'),
]
