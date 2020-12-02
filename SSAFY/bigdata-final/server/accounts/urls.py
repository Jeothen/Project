from django.conf.urls import url
from . import views
from django.urls import path

from rest_framework_simplejwt.views import (
  TokenRefreshView,
  TokenVerifyView,
)

urlpatterns = [
  # path('auth/signup-many/',views.signup_many), 
  path('auth/profileupdate/', views.sub),
  # AllowAny
  path('auth/list/',views.UserListView.as_view()), # 유저 목록을 띄운다 (admin만 가능)
  path('auth/register/',views.RegisterView.as_view()),

  # IsAuthenticatedOrReadOnly

  # IsAuthenticated
  path('auth/getuser/' ,views.getUserInfo), # 내 프로필을 가져온다.
  path('auth/detail/<slug>/',views.api_profile_detail_view),
  path('auth/age/<int:age>', views.UserListAgeView), 
  path('auth/update/<slug>/', views.UserUpdateView.as_view()), # 수정페이지

  # IsAdminUser

  # token
  path('auth/token/obtain/',views.MyTokenObtainPairView.as_view()),
  path('auth/token/verify/',TokenVerifyView.as_view()),
  path('auth/token/refresh/',TokenRefreshView.as_view()),

  path('auth/gender/<gender>/',views.UserListGenderView),
  path('auth/job/<job>/',views.UserListJobView),

  # subscription
  path('auth/subscription/',views.sub),

]
