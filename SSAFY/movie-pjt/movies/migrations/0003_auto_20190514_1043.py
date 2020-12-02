# Generated by Django 2.1.8 on 2019-05-14 01:43

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('movies', '0002_auto_20190513_1105'),
    ]

    operations = [
        migrations.AddField(
            model_name='movie',
            name='age',
            field=models.TextField(default=1),
            preserve_default=False,
        ),
        migrations.AlterField(
            model_name='movie',
            name='actors',
            field=models.ManyToManyField(related_name='actormovies', to='movies.Actor'),
        ),
        migrations.AlterField(
            model_name='movie',
            name='directors',
            field=models.ManyToManyField(related_name='directormovies', to='movies.Director'),
        ),
        migrations.AlterField(
            model_name='movie',
            name='genre',
            field=models.ManyToManyField(related_name='genremovies', to='movies.Genre'),
        ),
    ]