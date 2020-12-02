from django.utils.text import slugify
from django.contrib.auth.models import User
from django.utils import timezone

from rest_framework import status, generics, filters
from rest_framework.decorators import api_view, permission_classes
from rest_framework.response import Response
from rest_framework.permissions import AllowAny, IsAdminUser, IsAuthenticated

# from .filters import UserAgeFilter

from movies.models import Movie
from rest_framework.pagination import PageNumberPagination

from rest_framework_simplejwt import tokens, views
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer

from .serializers import ProfileSerializer, RegisterSerializer, UpdateSerializer
from .models import create_profile, Profile, Token
from movies.serializers import MovieSerializer

import datetime

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
    fields = ['id','username','age','slug','occupation','gender','is_admin','is_staff']
    for f in fields:
      data[f] = serializer.data[f]
    # data['watched_movie'] = watched_movie
    return Response(data=data)

# @permission_classes((IsAdminUser, ))
class UserListView(generics.ListAPIView):
  queryset = Profile.objects.all()
  serializer_class = ProfileSerializer
  pagination_class = PageNumberPagination
  filter_backends = ( filters.SearchFilter, )
  search_fields = ('slug',) # 조회순 영화정렬 ?ordering=-views

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

# =======================USER=================================
# =======================USER REGISTER========================
@permission_classes((AllowAny, ))
class RegisterView(generics.CreateAPIView):
  queryset = Profile.objects.all()
  serializer_class = RegisterSerializer

# PUT 요청시 페이지를 수정한다.
@permission_classes((IsAdminUser,))
class UserUpdateView(generics.UpdateAPIView):
  queryset = Profile.objects.all()
  serializer_class = UpdateSerializer
  lookup_field = 'slug'
  def put(self, request, *args, **kwargs):
    beforeSlug = kwargs['slug']
    afterSlug = request.data['username']
    gender = request.data['gender']
    occupation = request.data['occupation']
    age = int(request.data['age'])

    if age < 8 or age > 60:
      return Response(data={"response":"나이에러"},status=status.HTTP_400_BAD_REQUEST)
    before = Profile.objects.filter(slug=slugify(beforeSlug))
    after = Profile.objects.filter(slug=slugify(afterSlug))

    if before.exists():
      # 바꿀이름이 db에 없어야한다. 또는 같은 이름을 때
      if after.exists() == False or slugify(beforeSlug) == slugify(afterSlug):
        profile = Profile.objects.get(slug=slugify(beforeSlug))
        profile.user.username = afterSlug
        profile.slug = slugify(afterSlug)
        profile.age = age
        profile.gender = gender
        profile.occupation = occupation
        profile.save()
        profile.user.save()
        data = {};data['username']=afterSlug;data['slug']=slugify(afterSlug)
        data['age']=age;data['gender']=gender;data['occupation']=occupation
        return Response(data=data)
      else:
        return Response({"response":"중복아이디 존재"},status=status.HTTP_409_CONFLICT)
    return Response(data={"response":"아이디없어요~"},status=status.HTTP_404_NOT_FOUND)
# ============================================================

# =====================LOGIN==================================
# Login 검증작업이 완료되면 refresh 토큰을 발급받는다.
class MyTokenObtainPairSerializer(TokenObtainPairSerializer):
  @classmethod
  def get_token(cls, user):
    refresh_token = super().get_token(user)
    # try:
    #   user_token = Token.objects.get(user=user)
    #   user_token.token = refresh_token
    #   user_token.save()
    #   print('유저토큰이 존재해요. 재 발급할게요.')
    # except Token.DoesNotExist:
    #   print('토큰을 발급해드릴게요')
    #   Token.objects.create(
    #     user=user,
    #     token=refresh_token
    #   )
    return refresh_token
# 토큰 발급받은 뒤 직렬화 한다.
class MyTokenObtainPairView(views.TokenObtainPairView):
  serializer_class = MyTokenObtainPairSerializer
# ==========================================================

# =====================GET myUSER INFO==============================
@api_view(['GET'])
@permission_classes((IsAuthenticated, ))
def getUserInfo(request):
  if request.method == 'GET':
    slug = slugify(request.user.username)
    profile = Profile.objects.get(slug=slug)
    serializer = ProfileSerializer(profile)
    return Response(data=serializer.data)
# ==============================================================

@api_view(['GET'])
def UserListGenderView(request,gender):
  if request.method == 'GET':
    profile=Profile.objects.filter(gender__icontains=gender)

@api_view(['GET'])
def UserListJobView(request, job):
  if request.method == 'GET':
    profile=Profile.objects.filter(occupation__icontains=job)
    result = {}
    for i in profile:
      for j in i.user_visited.all() :
        if j.id not in result :
          result[j.id] = 1
        else :
          result[j.id] += 1
    answer = sorted(result.items(), key=lambda k : k[1], reverse=True)
    print(answer)
    data = []

    for i in answer: #answer = [(id, views), ... ]
      movie = Movie.objects.get(pk=i[0])
      serializer = MovieSerializer(movie)
      # print(serializer.data)
      data.append(serializer.data)
    print(data)
    # serializer=ProfileSerializer(profile, many = True)
    return Response(data=data, status=status.HTTP_200_OK)

@api_view(['GET'])
@permission_classes((IsAuthenticated,))
def sub(request):
  if request.method == 'GET':
    profile = Profile.objects.get(pk=request.user.pk - 2)
    if profile.is_subscription == False:
      profile.is_subscription = True
      profile.save()
    else:
      profile.is_subscription = False
      profile.save()
    serializer = ProfileSerializer(profile)

    return Response(data=serializer.data)

# @api_view(['GET'])
# @permission_classes((IsAuthenticated, ))
# def subscription(request):
#   if request.method == 'GET':

