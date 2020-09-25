from .models import Movie, Rating
from rest_framework import serializers

class MovieSerializer(serializers.ModelSerializer):
    genres_array = serializers.ReadOnlyField()
    class Meta:
        model = Movie
        fields = ('id', 'title', 'genres_array','views','slug','avg_rating','visited_user','poster','runtime',
        'director','writer','actor','plot','country')

class RatingSerializer(serializers.ModelSerializer):
    class Meta:
        model = Rating
        fields = '__all__'
