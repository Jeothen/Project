from rest_framework import status
from rest_framework.decorators import api_view
from api.models import Rating, Profile, Movie
from api.serializers import RatingSerializer
from rest_framework.response import Response
from django.contrib.auth.models import User
​
@api_view(['GET', 'POST', 'DELETE'])
def ratings(request):
​
    if request.method == 'GET':
        
        ratings = Rating.objects.all()
      
        serializer = RatingSerializer(ratings, many=True)
        return Response(data=serializer.data, status=status.HTTP_200_OK)


    if request.method == 'POST':
        ratings = request.data.get('ratings', None)
        for rating in ratings:
            score = rating.get('score', None)
            user_id= rating.get('user_id',None)
            movie_id= rating.get('movie_id',None)
            timestamp = rating.get('timestamp',None)
​
            if not (score and user_id and movie_id and timestamp):
                continue
                
​
            Rating(score=score, user=User.objects.get(pk=user_id), movie=Movie.objects.get(pk=movie_id), timestamp= timestamp).save()
​
        return Response(status=status.HTTP_200_OK)
    
​