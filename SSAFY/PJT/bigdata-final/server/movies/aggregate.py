from django.db.models import Avg

from rest_framework.decorators import api_view
from rest_framework.response import Response

from .models import Rating

@api_view(['GET'])
def test(request):
    rating = Rating.objects.filter(movie=3408)
    print(rating)
    a = Rating.objects.filter(movie=3408).aggregate(Avg('rating'))
    print(a)
    return Response({'gg':'gg'})