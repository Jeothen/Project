from rest_framework import status, generics
from rest_framework.decorators import api_view
from rest_framework.response import Response
from django.contrib.auth.models import User
# from .filters import UserAgeFilter

from .models import create_profile, Profile
from .serializers import ProfileSerializer
from movies.models import  Movie
from rest_framework.pagination import PageNumberPagination
from movies.models import Movie

from movies.serializers import MovieSerializer
# 유저 정보를 저장합니다.
@api_view(['POST'])
def signup_many(request):
  if request.method == 'POST':
    profiles = request.data.get('profiles', None)
    for profile in profiles:
      username = profile.get('username', None)
      password = profile.get('password', None)
      age = profile.get('age', None)
      occupation = profile.get('occupation', None)
      gender = profile.get('gender', None)
      create_profile(username=username, password=password, age=age,
                      occupation=occupation, gender=gender)
    return Response(status=status.HTTP_201_CREATED)


# 프로필 상세보기입니다.                                                                                                                                                                                                                      페이지를 불러옵니다.
@api_view(['GET'])
def api_profile_detail_view(request, slug):
  try:
    profile = Profile.objects.get(slug=slug)
  except Profile.DoesNotExist:
    return Response(status=status.HTTP_404_NOT_FOUND)

  if request.method == 'GET':
    serializer = ProfileSerializer(profile)
    data = {}
    watched_movie = []
    # print(profile.watched_movie.all())
    # for m in serializer.data['watched_movie']:
    #   watched_movie.append(Movie.objects.get(pk=m).slug)
    fields = ['id','username','age','slug','occupation','gender']
    for f in fields:
      data[f] = serializer.data[f]
    # data['watched_movie'] = watched_movie
    return Response(data=data)

class UserListView(generics.ListAPIView):
  queryset = Profile.objects.all()
  serializer_class = ProfileSerializer
  pagination_class = PageNumberPagination

@api_view(['GET'])
def UserListAgeView(request, age):
    # ages=[0,1,2,3,4,5,6]
    result=[]
    data={}
    if request.method == 'GET':
        profile=Profile.objects.filter(age__gt=age-1).filter(age__lt=age+10)
        # print(profile)
        # serializer=ProfileSerializer(profile, many=True)
        # print(serializer.data)
        for user in profile :
            if user.user_visited.all() :
                # print(user.user_visited.all())
                for p in user.user_visited.all() :
                    if p.id in data :
                        data[p.id] += 1
                    else :
                        data[p.id] = 1
        print(data)
        # sorted(data,reverse=True)
        answer = (sorted(data, key=lambda k : data[k], reverse=True))
        print(answer)
        for i in answer: #answer = [(id, views), ... ]
            movie = Movie.objects.get(pk=i)
            serializer = MovieSerializer(movie)
            # print(serializer.data)
            result.append(serializer.data)
            print(result)

        return Response(data=result, status=status.HTTP_200_OK)
