from django.shortcuts import render
from django.contrib.auth.models import User
from django_filters.rest_framework import DjangoFilterBackend

from rest_framework import status, generics
from rest_framework.filters import OrderingFilter
from rest_framework.response import Response
from rest_framework.decorators import api_view, action
from rest_framework.pagination import PageNumberPagination
from rest_framework.permissions import IsAdminUser, IsAuthenticated

from .filters import MovieFilter
from .models import Movie, Rating
from .serializers import MovieSerializer, RatingSerializer
from accounts.serializers import ProfileSerializer
from accounts.models import Profile

from rest_framework.decorators import api_view, permission_classes
from rest_framework import status
from django.db.models import Avg
from datetime import datetime
import time
from django.db.models import Max
import random
# ================ single 영화 접근 ======================== #
# GET 요청시 상세페이지, PUT 요청시 업데이트, DELETE 요청시 삭제
@api_view(['GET'])
def MovieRandom(request) :
    max_id = Movie.objects.all().aggregate(max_id = Max("id"))['max_id']
    m_list={}
    for i in range(20) :
        pk = random.randint(1, max_id)
        print(pk)
        try :
            review=Movie.objects.get(id=pk)
            serializer = MovieSerializer(review)
            m_list[i]=serializer.data
        except Exception as DoesNotExist :
            pass
    print(m_list)
    return Response(data=m_list,status=status.HTTP_200_OK)
# GET 요청시 영화정보를 반환한다.
@permission_classes((IsAuthenticated, ))
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
@permission_classes((IsAdminUser,))
class MovieUpdateView(generics.UpdateAPIView):
  queryset = Movie.objects.all()
  serializer_class = MovieSerializer
  lookup_field = 'slug'

# Delete 요청시 페이지를 삭제한다.
@permission_classes((IsAdminUser,))
class MovieDeleteView(generics.DestroyAPIView):
  queryset = Movie.objects.all()
  serializer_class = MovieSerializer
  lookup_field = 'slug'


# ====================== collection 영화 접근 ======================== #
# GET 요청시 영화 리스트, 필터링 적용가능
@permission_classes((IsAuthenticated,))
class MovieListView(generics.ListAPIView):
  queryset = Movie.objects.all()
  serializer_class = MovieSerializer
  filterset_class = MovieFilter
  filter_backends = (OrderingFilter, DjangoFilterBackend)
  ordering_fields = ('views','title','id','avg_rating') # 조회순 영화정렬 ?ordering=-views
  pagination_class = PageNumberPagination

  def list(self, request, *args, **kwargs):
    print('야호')
    print(request.user.pk,'유저있어요/.')
    queryset = self.filter_queryset(self.get_queryset())
    page = self.paginate_queryset(queryset)
    if page is not None:
        serializer = self.get_serializer(page, many=True)
        ratings = []
        for movie in page:
          rating = Rating.objects.filter(movie=movie.id).aggregate(Avg('rating'))
          ratings.append(rating)

        for i in range(len(ratings)):
          serializer.data[i].update(ratings[i])

        return self.get_paginated_response(data=serializer.data)

    serializer = self.get_serializer(queryset, many=True)
    return Response(serializer.data)

@api_view(['POST'])
def create_rating(request, movie_pk, user_pk):
  if request.method == 'POST':
    print(request.data, movie_pk, user_pk, 'asdjkfjsdkfj')
    # rating = request.body
    rating = request.data['rating']
    user = User.objects.get(pk=user_pk+2)
    print('user:', user)
    movie = Movie.objects.get(pk=movie_pk)
    print('movie:', movie)
    profile = Profile.objects.get(pk=user_pk)
    timestamp = int(time.mktime(datetime.today().timetuple()))

    if profile  not in movie.visited_user.all() :
        movie.visited_user.add(user_pk)
        movie.save()
        Rating(rating=rating, user=user, movie=movie, timestamp=timestamp).save()
        newrating = Rating.objects.filter(movie=movie_pk).aggregate(Avg('rating'))
        profile_serializer = ProfileSerializer(profile)

        return Response({'message':'평점 등록완료','data':newrating,'visit':profile_serializer.data['user_visited']})
    else :
        print(user_pk,'이미있음')
        print('user', user_pk)
        print('vistied_user', movie.visited_user.all())
        print(user_pk,'가 방문한 영화',Profile.objects.get(pk=user_pk).user_visited.all())

        return Response(status=status.HTTP_409_CONFLICT)

@api_view(['POST'])
def del_rating(request, movie_pk, user_pk):
  if request.method == 'POST':
    user = User.objects.get(pk=user_pk+2)
    print(user)
    movie = Movie.objects.get(pk=movie_pk)
    movie.visited_user.remove(user_pk)
    print(user_pk, movie_pk, 'aaaaaaa')
    ratings = Rating.objects.filter(user=user_pk+2) # slug 로 남겨야함
    print('ratings', ratings)
    for rating in ratings:
      if rating.movie.pk == movie_pk:
        # rating.remove(movie_pk)
        print(rating.movie.pk, 'asd')
        rate = Rating.objects.get(pk=rating.pk)
        rate.delete()
        print(rating.pk)
        break # 하나만 삭제하고 나감
    newrating = Rating.objects.filter(movie=movie_pk).aggregate(Avg('rating'))
    profile = Profile.objects.get(pk=user_pk)
    print(profile.__dict__)
    profile_serializer = ProfileSerializer(profile)
    # movie.save()
    return Response({'message':'평점삭제완료', 'data':newrating, 'visit':profile_serializer.data['user_visited']})
