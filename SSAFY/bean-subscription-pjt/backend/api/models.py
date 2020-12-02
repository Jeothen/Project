from django.db import models
from django.contrib.auth.models import User
from django.dispatch import receiver
from django.utils.text import slugify
from django.db.models.signals import post_delete, pre_save
from django.conf import settings
from django.contrib.postgres.fields import ArrayField,HStoreField
import datetime
from django.contrib.postgres.fields import HStoreField
from django.contrib.postgres.fields import JSONField

class Profile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    address = models.TextField(blank=True)
    approval = models.BooleanField(default=False)
    subscription = models.DateTimeField(default=datetime.datetime(2019, 9, 10, 13, 47, 43, 630123))
    gender = models.CharField(max_length=10, default='M')
    birthday = models.CharField(max_length=20, default='')
    years = models.IntegerField(default=0)
    
def create_profile(**kwargs):
    user = User.objects.create_user(
        username=kwargs['username'],
        password=kwargs['password'],
        email=kwargs['email'],
        is_active=True,
    )

    profile = Profile.objects.create(
        user=user,
        address=kwargs['address'],
        gender=kwargs['gender'],
        birthday=kwargs['birthday'],
        years=int(kwargs['birthday'][:4])
    )

    return profile

class Subscription_manager(models.Model):
    profile = models.ForeignKey(Profile, on_delete=models.CASCADE, related_name="profile_sub")
    request = models.IntegerField(default=0)
    approval = models.BooleanField(default=False)
    request_day = models.DateTimeField(auto_now_add=True)
    apply_day = models.DateTimeField(auto_now=True)

class Percent(models.Model) :
    id=models.IntegerField(primary_key=True)
    value=models.IntegerField(default=0)

class Item(models.Model) :
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=500)
    content = models.TextField(blank=True)
    image= models.TextField(blank=True)
    country = models.TextField(blank=True)

    def __str__(self):
        return self.name

# class Flavor(models.Model) :
#     id = models.IntegerField(primary_key=True)
#     name = models.TextField() 
#     item_name = models.ForeignKey(Item,blank=True,on_delete=models.CASCADE)


class Blend(models.Model) :
    id = models.AutoField(primary_key=True)
    method = models.TextField(blank=True)
    items = models.ManyToManyField(Item, blank=True, related_name="to_blend")
    percents = models.TextField(blank=True)
    content=models.TextField(blank=True)

class Delivery(models.Model) :
    id = models.AutoField(primary_key=True)
    weeks = models.IntegerField(default=0)
    month = models.IntegerField(default=0)
    year = models.IntegerField(default=0)
    in_item = models.ForeignKey(Blend,blank=True,on_delete=models.CASCADE)
    created_at=models.DateTimeField(auto_now_add=True)
    updated_at=models.DateTimeField(auto_now=True)

class Rating(models.Model) :
    id = models.AutoField(primary_key=True)
    user = models.ForeignKey(Profile,on_delete=models.CASCADE)
    blend = models.ForeignKey(Blend,on_delete=models.CASCADE)
    number = models.IntegerField(default=0)
    comment = models.TextField(blank=True)
    
    @property
    def rating_count(self) :
        return self.user.count()
