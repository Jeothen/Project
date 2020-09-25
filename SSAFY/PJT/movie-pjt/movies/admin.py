from django.contrib import admin
from django.contrib.admin.views.main import ChangeList
from .models import Genre, Movie, Actor, Director

# Register your models here.
class GenreAdmin(admin.ModelAdmin):
    list_display = ['name']
admin.site.register(Genre, GenreAdmin)


class MovieAdmin(admin.ModelAdmin):
    list_display = ['title','audience','poster_url','description']
    
admin.site.register(Movie, MovieAdmin)

class ActorAdmin(admin.ModelAdmin):
    list_display = ['name']
admin.site.register(Actor, ActorAdmin)

class DirectorAdmin(admin.ModelAdmin):
    list_display = ['name']
admin.site.register(Director, DirectorAdmin)