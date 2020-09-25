# Generated by Django 2.2.4 on 2019-11-29 06:52

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('accounts', '0002_auto_20190926_1504'),
    ]

    operations = [
        migrations.CreateModel(
            name='Movie',
            fields=[
                ('id', models.IntegerField(primary_key=True, serialize=False)),
                ('title', models.CharField(max_length=200, unique=True)),
                ('genres', models.CharField(max_length=500)),
                ('slug', models.SlugField(blank=True, unique=True)),
                ('views', models.IntegerField(default=0)),
                ('avg_num', models.IntegerField(blank=True, default=0)),
                ('avg_length', models.IntegerField(blank=True, default=0)),
                ('avg_rating', models.FloatField(blank=True, default=0)),
                ('poster', models.TextField(blank=True)),
                ('runtime', models.TextField(blank=True)),
                ('director', models.TextField(blank=True)),
                ('writer', models.TextField(blank=True)),
                ('actor', models.TextField(blank=True)),
                ('plot', models.TextField(blank=True)),
                ('country', models.TextField(blank=True)),
                ('visited_user', models.ManyToManyField(blank=True, related_name='user_visited', to='accounts.Profile')),
            ],
        ),
        migrations.CreateModel(
            name='Rating',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('rating', models.IntegerField()),
                ('timestamp', models.IntegerField(blank=True)),
                ('movie', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='movies.Movie')),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
    ]
