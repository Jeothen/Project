# Generated by Django 2.2.4 on 2019-08-27 05:22

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('accounts', '0003_auto_20190826_1419'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='profile',
            name='watched_movie',
        ),
    ]
