# Movie PJT



### Account

* 회원가입, 로그인, 로그아웃 기능
* 유저 상세페이지(팔로워, 팔로잉, 좋아하는 영화, 작성한 리뷰)

```python
## Model.py
from django.db import models
from django.contrib.auth.models import AbstractUser
from django.conf import settings
# Create your models here.
class User(AbstractUser):
    followers = models.ManyToManyField(
                        settings.AUTH_USER_MODEL,
                        related_name='followings'
                        )
    
## form.py
from django import forms
from django.contrib.auth import get_user_model
from django.contrib.auth.forms import UserCreationForm

#회원가입 form
class UserCustomCreationForm(UserCreationForm):
    class Meta:
        model = get_user_model()
        fields = ['username','email','last_name','first_name',]

```





### Movie Model

* 메인 페이지

* 배우  및 감독 페이지
* 장르별 페이지
* 영화 페이지
* 영화 평가 시스템
* 마이페이지

```python
##model.py

from django.shortcuts import reverse
from django.db import models
from django.conf import settings

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

    
## form.py

from django import forms
from .models import Score

class ScoreForm(forms.ModelForm):
    class Meta:
        model = Score
        fields = ('content', 'value')

```



**views.py** 참고

