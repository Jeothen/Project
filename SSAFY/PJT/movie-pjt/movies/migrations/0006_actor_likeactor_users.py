# Generated by Django 2.1.8 on 2019-05-15 06:52

from django.conf import settings
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('movies', '0005_movie_watch'),
    ]

    operations = [
        migrations.AddField(
            model_name='actor',
            name='likeactor_users',
            field=models.ManyToManyField(blank=True, related_name='like_actors', to=settings.AUTH_USER_MODEL),
        ),
    ]