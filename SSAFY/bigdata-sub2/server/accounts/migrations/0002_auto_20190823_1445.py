# Generated by Django 2.2.4 on 2019-08-23 05:45

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('accounts', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='profile',
            name='watched_movie',
            field=models.ManyToManyField(blank=True, to='movies.Movie'),
        ),
    ]