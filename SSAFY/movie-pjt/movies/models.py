from django.shortcuts import reverse
from django.db import models
from django.conf import settings
# Create your models here.

class Genre(models.Model):
    name = models.CharField(max_length=20)
    def __str__(self):
        return self.name

class Actor(models.Model):
    name = models.CharField(max_length=30)
    image_url = models.TextField()
    likeactor_users = models.ManyToManyField(settings.AUTH_USER_MODEL,blank=True,related_name='like_actors')
    
    def likeactor_count(self):
        return self.likeactor_users.count()
        
    def __str__(self):
        return self.name
    
class Director(models.Model):
    name = models.CharField(max_length=30)
    image_url = models.TextField()
    likedirector_users = models.ManyToManyField(settings.AUTH_USER_MODEL,blank=True,related_name='like_directors')
    
    def likedirector_count(self):
        return self.likedirector_users.count()
        
    def __str__(self):
        return self.name
    
class Movie(models.Model):
    title = models.CharField(max_length=30)
    code = models.IntegerField()
    dates = models.DateField(blank=True)
    age = models.TextField()
    watch = models.IntegerField()
    audience = models.IntegerField()
    poster_url = models.TextField()
    description = models.TextField()
    genre = models.ManyToManyField(Genre, related_name='genremovies')
    score_users = models.ManyToManyField(settings.AUTH_USER_MODEL, 
                                    through='Score',
                                    related_name='score_movies'
                                    )
    like_users = models.ManyToManyField(settings.AUTH_USER_MODEL, related_name='like_posts')
    actors = models.ManyToManyField(Actor, related_name='actormovies')
    directors = models.ManyToManyField(Director, related_name='directormovies')
    
    def like_count(self):
        return self.like_users.count()
    def get_absolute_url(self):
        return reverse("movies:detail", args=[self.pk])


class Score(models.Model):
    content = models.CharField(max_length=140)
    value = models.IntegerField()
    movie = models.ForeignKey(Movie, on_delete=models.CASCADE)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
    

