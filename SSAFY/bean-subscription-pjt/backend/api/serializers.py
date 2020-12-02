from .models import Delivery, Blend, Item, Profile, User, Rating, Subscription_manager
from rest_framework import serializers
from django.utils.text import slugify

class DeliverySerializer(serializers.ModelSerializer) :
    class Meta:
        model = Delivery
        fields = ('id','weeks','month','in_item','created_at','updated_at')

class ItemSerializer(serializers.ModelSerializer) :
    class Meta:
        model = Item
        fields = '__all__'

class BlendSerializer(serializers.ModelSerializer) :
    class Meta:
        model = Blend
        fields = '__all__'

class RatingSerialiazer(serializers.ModelSerializer) :
    class Meta :
        model = Rating
        fields = '__all__'

class ProfileSerializer(serializers.ModelSerializer):
    id = serializers.ReadOnlyField()
    username = serializers.SerializerMethodField('get_username')
    email = serializers.SerializerMethodField('get_email')
    user_pk = serializers.SerializerMethodField('get_user_pk')
    class Meta:
        model = Profile
        fields = ('id', 'username', 'user_pk', 'email', 'address', 'approval', 'subscription')
    
    def get_username(self, obj):
        return obj.user.username
    
    def get_email(self, obj):
        return obj.user.email

    def get_user_pk(self, obj):
        return obj.user.pk

class SubscriptionSerializer(serializers.ModelSerializer):
    username = serializers.SerializerMethodField('get_username')

    class Meta:
        model = Subscription_manager
        fields = ('id', 'profile', 'request', 'approval', 'request_day', 'apply_day', 'username')

    def get_username(self, obj):
        profile = obj.profile
        user = User.objects.get(pk=profile.user_id)
        return user.username

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        # fields =  "__all__"
        fields = ('id', 'username')