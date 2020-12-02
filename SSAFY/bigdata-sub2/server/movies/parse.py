from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view

from .models import Movie, Rating

# 영화정보를 저장합니다
@api_view(['POST'])
def get_movies(request):
  if request.method == 'POST':
    movies = request.data.get('movies', None)
    for movie in movies:
      id = movie.get('id', None)
      title = movie.get('title', None)
      genres = movie.get('genres', None)
      # print(f'{id} {title} {genres}')
      if not (id and title and genres):
          continue
      if Movie.objects.filter(id=id).count() > 0 or Movie.objects.filter(title=title).count() > 0:
          continue
      Movie(id=id, title=title, genres='|'.join(genres)).save()
    return Response(status=status.HTTP_200_OK)

# 평점정보를 저장합니다.
@api_view(['POST'])
def get_ratings(request):
  if request.method == 'POST':
    ratings = request.data.get('ratings')
    for ratingg in ratings:
      uid = ratingg.get('uid',None)
      mid = ratingg.get('mid',None)
      rating = ratingg.get('rating',None)
      timestamp = ratingg.get('timestamp',None)
      movie = Movie.objects.get(pk=mid)    
      user = User.objects.get(pk=uid)    
      Rating(user=user, movie=movie, rating=rating, timestamp=timestamp).save()        
    return Response(status=status.HTTP_200_OK)

# Rating을 모두 읽어서 Movie model에 저장합니다.
# custom 함수, 한번만 실행해야해요. 작업이 오래걸림
@api_view(['GET'])
def create_avg_rating(request):
  # 영화 평점을 모두 초기화 합니다.
  movies = Movie.objects.all()
  movie_info = dict()
  for movie in movies:
    movie.avg_num = 0
    movie.avg_length = 0
    movie.avg_rating = 0.0
    movie_info.update({movie.pk:[0,0]}) #점수/길이 = 평균
  ratings = Rating.objects.all()
  for rating in ratings:
    movie = rating.movie
    movie_info[movie.pk][0] += rating.rating
    movie_info[movie.pk][1] += 1
  for (key,value) in movie_info.items():
    print(value[0],value[1])
    if (value[1]):
      movie = Movie.objects.get(pk=key)
      movie.avg_num = value[0]
      movie.avg_length = value[1]
      movie.avg_rating = round(value[0]/value[1],2)
      movie.save()
  return Response(status=status.HTTP_200_OK)



# # 영화 상세정보 페이지를 불러옵니다.
# @api_view(['GET'])
# def api_movie_detail_view(request, slug):
#   try:
#     movie = Movie.objects.get(slug=slug)
#     movie.views += 1
#     movie.save()
#   except Movie.DoesNotExist:
#     return Response(status=status.HTTP_404_NOT_FOUND)
#   if request.method == 'GET':
#     serializer = MovieSerializer(movie)
#     data = {}
#     fields = ['id','title','genres_array','views','slug','avg_rating']
#     for f in fields:
#       data[f] = serializer.data[f]
#     return Response(data=data)
