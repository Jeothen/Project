from django.contrib.auth.models import User
from django.utils.text import slugify
from .models import Profile, create_profile
from rest_framework import serializers
import re


class ProfileSerializer(serializers.ModelSerializer):
  id = serializers.ReadOnlyField()
  username = serializers.SerializerMethodField('get_username')
  is_staff = serializers.SerializerMethodField('get_is_staff')
  
  class Meta:
    model = Profile
    fields = ('id', 'username', 'is_staff', 'gender', 'age', 'occupation','slug','user_visited','is_admin','is_subscription')

  def get_username(self, obj):
    return obj.user.username

  def get_is_staff(self, obj):
    return obj.user.is_staff

class RegisterSerializer(serializers.ModelSerializer):
  username = serializers.CharField(write_only=True)
  password1 = serializers.CharField(style={'input_type':'password'}, write_only=True)
  password2 = serializers.CharField(style={'input_type':'password'}, write_only=True)
  gender = serializers.CharField(write_only=True)
  age = serializers.IntegerField(write_only=True)
  occupation = serializers.CharField(write_only=True)

  class Meta:
    model = Profile
    fields = ('username','age','password1','password2','gender','occupation')
    
  def save(self):
    username = self.validated_data['username']
    password1 = self.validated_data['password1']
    password2 = self.validated_data['password2']
    gender = self.validated_data['gender']
    age = self.validated_data['age']
    occupation = self.validated_data['occupation']

    if re.match(r'^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$',password1) == None:
      raise serializers.ValidationError({'password':'암호는 8자리이상, 영어 숫자 특수문자를 최소 하나이상 포함해야해요'})

    if password1 != password2:
      raise serializers.ValidationError({'password': '암호불일치!!'})
    
    if age < 8 or age > 60:
      raise serializers.ValidationError({'age': '나이는 8이상 60이하'})

    us = slugify(username)
    profile = Profile.objects.filter(slug=us)
    if profile.exists():
      raise serializers.ValidationError({'username': '유저아이디 중복'})

    create_profile(username=username, password=password1, age=age,
      occupation=occupation, gender=gender)

class UpdateSerializer(serializers.ModelSerializer):
  username = serializers.CharField(write_only=True)
  gender = serializers.CharField(write_only=True)
  age = serializers.IntegerField(write_only=True)
  occupation = serializers.CharField(write_only=True)

  class Meta:
    model = Profile
    fields = ('username','age','gender','occupation')

  # def save(self):
  #   username = self.validated_data['username']
  #   age = self.validated_data['age']
  #   gender = self.validated_data['gender']
  #   occupation = self.validated_data['occupation']

  #   us = slugify(username)
  #   profile = Profile.objects.filter(slug=us)
  #   if age < 8 or age > 60:
  #     raise serializers.ValidationError({'age': '나이는 8이상 60이하'})

  #   if profile.exists():


    # print(profiler.user.username, ' zzz ')
    # print(username, gender, age, occupation)

    # profiler.gender = gender
    # profiler.age = age
    # profiler.occupation = occupation
    # profiler.save()

    # print(profiler)

    
    