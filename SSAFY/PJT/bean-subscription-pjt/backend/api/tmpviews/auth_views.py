from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from api.models import create_profile, Profile, User, Subscription_manager
from django.contrib.auth import login as auth_login
from django.contrib.auth import logout as auth_logout
from django.contrib.auth import authenticate
from django.contrib.auth.decorators import login_required
from api.serializers import ProfileSerializer, SubscriptionSerializer, UserSerializer
import datetime

@api_view(['POST'])
def alluser(request):
    if request.method == 'POST':
        users = Profile.objects.all()
        print(users)
        serializer = ProfileSerializer(users, many=True)
        return Response(data=serializer.data, status=status.HTTP_200_OK)

@api_view(['POST'])
def user_delete(request, user_pk):
    if request.method == 'POST':
        user = User.objects.get(id=user_pk)
        user.delete()
        return Response(data=True, status=status.HTTP_200_OK)

@api_view(['POST'])
def signup(request):
    if request.method == 'POST':
        username = request.data.get('username', None)
        if User.objects.filter(username=username):
            return Response(data=False, status=status.HTTP_201_CREATED)
        
        password = request.data.get('password')
        email = request.data.get('email', None)
        address = request.data.get('address', None)
        gender = request.data.get('gender', None)
        birthday = request.data.get('birthday', None)
        
        create_profile(username=username, password=password, email=email, address=address, gender=gender, birthday=birthday)

        return Response(data=True, status=status.HTTP_201_CREATED)

@api_view(['POST'])
def signin(request):
    if request.method == 'POST':
        username = request.data.get('username', None)
        password = request.data.get('password', None)
        user = authenticate(username=username, password=password)
        if user is not None:
            auth_login(request, user)
            data = {'user': user.username, 'id': user.id}
            return Response(data=data, status=status.HTTP_200_OK)
        else:
            data = {'user': '', 'admin':False}
            return Response(data=data, status=status.HTTP_200_OK)

@login_required
def logout(request):
    user = request.user
    auth_logout(request)
    return Response(status=status.HTTP_200_OK)

@api_view(['GET'])
def userstate(request):
    if request.user.is_authenticated:
        user = request.user
        data = {'user': user.username}
        return Response(data=data, status=status.HTTP_200_OK)
    else:
        data = {'user': ''}
        return Response(data=data, status=status.HTTP_200_OK)

@api_view(['GET', 'POST'])
def detail(request, user_id):
    user = User.objects.get(pk=user_id)
    user_profile = Profile.objects.get(user=user)
    if request.method == 'GET':
        serializer = ProfileSerializer(user_profile)
        return Response(data=serializer.data, status=status.HTTP_200_OK)
    
    elif request.method == 'POST':
        user.username = request.data.get('username', None)
        user.email = request.data.get('email', None)
        user_profile.address = request.data.get('address', None)
        user_profile.save()
        user.save()
        return Response(status=status.HTTP_200_OK)

# 구독 신청하기
@api_view(['POST'])
def create(request):
    if request.method == 'POST':
        user_id = request.data.get('user')
        user = User.objects.get(pk=user_id)
        profile = Profile.objects.get(user=user)
        
        check_subscription_request = Subscription_manager.objects.filter(profile=profile, approval=False)
        if check_subscription_request:
            message = {'message':'이미 구독 신청을 한 상태입니다.'}
            return Response(data=message, status=status.HTTP_200_OK)
        else:
            subscription_request = Subscription_manager.objects.create(
                profile=profile,
                request=request.data.get('request'),
                approval=False,
            )
            return Response(status=status.HTTP_200_OK)

@api_view(['GET','POST'])
def manager(request):
    if request.method == 'GET':
        subscriptions = Subscription_manager.objects.filter(approval=False)
        serializer = SubscriptionSerializer(subscriptions, many=True)
        return Response(data=serializer.data, status=status.HTTP_200_OK)

    elif request.method == 'POST':
        item = request.data.get('subscription')
        days = item['request']
        sub_id = item['id']
        prof_id = item['profile']
        profile = Profile.objects.get(pk=prof_id)

        # 1. Profile 변경 (처음 신청했을 때, 만료 이후 재신청했을 때, 연장신청했을 때 모두 여기에 해당한다.)
        if profile.subscription.strftime("%Y%m%d") > datetime.datetime.now().strftime("%Y%m%d"):
            # 연장할 때 
            profile.subscription = profile.subscription + datetime.timedelta(days=days)
            profile.save()
        else:
            # 신청, 또는 만료 이후 다시 신청
            profile.approval = True
            profile.subscription = datetime.datetime.now() + datetime.timedelta(days=days)
            profile.save()

        # 2. Subscription_manager 에서 해당 객체 승인 되었다고 변경
        subscription_instance = Subscription_manager.objects.get(pk=sub_id)
        subscription_instance.approval = True
        subscription_instance.save()

        return Response(status=status.HTTP_200_OK)