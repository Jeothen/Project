# Generated by Django 2.2.4 on 2019-08-23 02:37

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('movies', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Profile',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('gender', models.CharField(default='M', max_length=10)),
                ('age', models.IntegerField(default=25)),
                ('occupation', models.CharField(max_length=200)),
                ('slug', models.SlugField(blank=True)),
                ('user', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
                ('watched_movie', models.ManyToManyField(blank=True, related_name='visited_user', to='movies.Movie')),
            ],
        ),
    ]