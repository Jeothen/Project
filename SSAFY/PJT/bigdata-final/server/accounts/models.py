from django.db import models
from django.contrib.auth.models import User
# from movies.models import Movie

from django.dispatch import receiver
from django.utils.text import slugify
from django.db.models.signals import pre_save

# Create your models here.

class Profile(models.Model):
  user = models.OneToOneField(User, on_delete=models.CASCADE)
  gender = models.CharField(max_length=10, default='M')
  age = models.IntegerField(default=25)
  occupation = models.CharField(max_length=200)
  # 한 사람은 여러개의 영화를 방문
  # 한 영화는 여러명의 방문자
  slug = models.SlugField(blank=True)
  # watched_movie = models.ManyToManyField(Movie, blank=True)
  is_active = models.BooleanField(default=True)
  is_admin = models.BooleanField(default=False)

  is_subscription = models.BooleanField(default=False, blank=True)

  def __str__(self):
      return f'{self.user.username} | {self.gender} | {self.age} | {self.occupation}'

class Token(models.Model):
  token = models.CharField(max_length=100)
  user = models.OneToOneField(User,on_delete=models.CASCADE)
  def __str__(self):
    return f'{self.user} | {self.token}'

#  wrapper for create user Profile
def create_profile(**kwargs):
  user = User.objects.create_user(
    username=kwargs['username'],
    password=kwargs['password'],
    is_active=True,
  )
  profile = Profile.objects.create(
    user=user,
    gender=kwargs['gender'],
    age=kwargs['age'],
    occupation=kwargs['occupation']
  )
  return profile

def pre_save_profile_receiver(sender, instance, *args, **kwargs):
  if not instance.slug:
    instance.slug = slugify(instance.user.username)

pre_save.connect(pre_save_profile_receiver, sender=Profile)
