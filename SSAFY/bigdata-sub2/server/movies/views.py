from django.shortcuts import render
from django.contrib.auth.models import User
from django_filters.rest_framework import DjangoFilterBackend

from rest_framework import status, generics
from rest_framework.filters import OrderingFilter
from rest_framework.response import Response
from rest_framework.decorators import api_view, action
from rest_framework.pagination import PageNumberPagination

from .filters import MovieFilter
from .models import Movie, Rating
from .serializers import MovieSerializer, RatingSerializer
from accounts.models import Profile

from rest_framework.decorators import api_view
from rest_framework import status
from rest_framework.response import Response
from django.db.models import Avg

# ================ single 영화 접근 ======================== #
# GET 요청시 상세페이지, PUT 요청시 업데이트, DELETE 요청시 삭제

# GET 요청시 영화정보를 반환한다.
class MovieDetailView(generics.RetrieveAPIView):
  queryset = Movie.objects.all()
  serializer_class = MovieSerializer
  lookup_field = 'slug'

  ## GET 요청인 경우 조회수를 더한다.
  def get(self, request, *args, **kwargs):
    try:
      movie = Movie.objects.get(slug=kwargs['slug'])
      movie.views += 1
      # 로그인하면 로그인 유저의 프로필이 visited_user에 담기도록 함.
      # movie.visited_user.append(user의profile)
      movie.save()
    except Movie.DoesNotExist:
      return Response(status=status.HTTP_404_NOT_FOUND)
    return self.retrieve(request, *args, **kwargs)

# PUT 요청시 페이지를 수정한다.
class MovieUpdateView(generics.UpdateAPIView):
  queryset = Movie.objects.all()
  serializer_class = MovieSerializer
  lookup_field = 'slug'

# Delete 요청시 페이지를 삭제한다.
class MovieDeleteView(generics.DestroyAPIView):
  queryset = Movie.objects.all()
  serializer_class = MovieSerializer
  lookup_field = 'slug'


# ====================== collection 영화 접근 ======================== #
# GET 요청시 영화 리스트, 필터링 적용가능
class MovieListView(generics.ListAPIView):
  queryset = Movie.objects.all()
  serializer_class = MovieSerializer
  filterset_class = MovieFilter
  filter_backends = (OrderingFilter, DjangoFilterBackend)
  ordering_fields = ('views','title','id','avg_rating') # 조회순 영화정렬 ?ordering=-views
  pagination_class = PageNumberPagination
