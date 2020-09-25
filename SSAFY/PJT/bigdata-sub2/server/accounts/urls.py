from django.conf.urls import url
from . import views
from django.urls import path
urlpatterns = [
  url(r'^auth/signup-many/',views.signup_many),
  url(r'^auth/list/',views.UserListView.as_view()),
  url(r'^auth/<slug>/',views.api_profile_detail_view),
  path('auth/age/<int:age>', views.UserListAgeView)
]
