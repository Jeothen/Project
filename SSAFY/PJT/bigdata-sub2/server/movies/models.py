from django.db import models
from django.contrib.auth.models import User
from django.dispatch import receiver
from django.utils.text import slugify
from django.db.models.signals import post_delete, pre_save

from accounts.models import Profile


class Movie(models.Model):
  id = models.IntegerField(primary_key=True)
  title = models.CharField(max_length=200, unique=True)
  genres = models.CharField(max_length=500)
  slug = models.SlugField(blank=True, unique=True)
  views = models.IntegerField(default=0)

  avg_num = models.IntegerField(default=0, blank=True)
  avg_length = models.IntegerField(default=0, blank=True)
  avg_rating = models.FloatField(default=0, blank=True)
  visited_user = models.ManyToManyField(Profile,blank=True,related_name="user_visited")

  def __str__(self):
    return f'{self.id} | {self.title}'

  @property
  def genres_array(self):
    return self.genres.strip().split('|')

  def save(self, *args, **kwargs):
    self.slug = slugify(self.title)
    return super(Movie, self).save(*args, **kwargs)

class Rating(models.Model):
  rating = models.IntegerField()
  timestamp = models.IntegerField()
  user = models.ForeignKey(User, on_delete=models.CASCADE )
  movie = models.ForeignKey(Movie, on_delete=models.CASCADE)

  def __str__(self):
    return f'{self.id} | {self.user} | {self.movie.title}'

def pre_save_movie_receiver(sender, instance, *args, **kwargs):
  if not instance.slug:
    instance.slug = slugify(instance.title)
pre_save.connect(pre_save_movie_receiver, sender=Movie)
