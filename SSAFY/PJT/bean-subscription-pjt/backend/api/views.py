from django.shortcuts import render,redirect, get_object_or_404
from django_filters.rest_framework import DjangoFilterBackend
from rest_framework import status, generics
from rest_framework.filters import OrderingFilter
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view, action
from django.contrib import messages
from django.contrib.auth import login as auth_login
from django.contrib.auth import logout as auth_logout
from django.contrib.auth.decorators import login_required
from rest_framework.pagination import PageNumberPagination
from rest_framework.permissions import IsAdminUser, IsAuthenticated
from rest_framework.decorators import api_view, permission_classes

from django.db.models import Avg
from datetime import datetime
from django.http import JsonResponse,HttpResponseBadRequest
import time
from django.db.models import Max
from .models import Delivery,Item, Percent, Blend
from .serializers import DeliverySerializer, ItemSerializer, BlendSerializer
import random

from .forms import DeliveryForm

@api_view(['GET'])
def list_delivery(request) :
    print('배달리스트')
    # deliverys = Delivery.objects.all()
    # print(deliverys)
    queryset = Delivery.objects.all()
    serializer = DeliverySerializer(queryset,many=True)    
    # serializer = DeliverySerializer(deliverys)
    return Response(data=serializer.data,status=status.HTTP_200_OK)

@api_view(['GET'])
def list_items(request) :
    print('아이템 리스트 조회')
    queryset = Item.objects.all()
    serializer = ItemSerializer(queryset,many=True)
    print(serializer.data)
    return Response(data=serializer.data,status=status.HTTP_200_OK)

@api_view(['GET'])
def list_blend(request) :
    print('blned조회')
    serializer={'items':[],'percents':[]}
    queryset = Blend.objects.all()
    serializer['list'] = BlendSerializer(queryset,many=True).data
    for i in queryset :
        queryset_2=i.items.all()
        serializer['items'].append(ItemSerializer(queryset_2,many=True).data)
        serializer['percents'].append(i.percents.split(','))
    return Response(data=serializer,status=status.HTTP_200_OK)

@api_view(['GET'])
def this_delivery(request,year,month) :
    print('이번달 배송상품')
    print(year,month)
    serializer={'delivery':[],'blend':[],'items':[],'percents':[]}
    delivery=Delivery.objects.filter(year=int(year), month=int(month))
    print(delivery[0].in_item)
    serializer['delivery'] = DeliverySerializer(delivery[0]).data
    serializer['blend'] = BlendSerializer(delivery[0].in_item).data
    serializer['percents'] = delivery[0].in_item.percents.split(',')
    print(delivery[0].in_item.items.all())
    serializer['items'] = ItemSerializer(delivery[0].in_item.items.all(),many=True).data
    return Response(data=serializer,status=status.HTTP_200_OK)
@api_view(['GET'])
def detail_blend(request,blend_pk) :
    
    return Response(status=status.HTTP_200_OK)

@api_view(['POST'])
def add_delivery(request,item_pk) :
    #GET 방식은 http header에 보내고 POST는 http body에 벨류를 보낸다.
    if request.method == "POST":
        print(request.data)
        print(request.POST,'post')
        delivery_form = DeliveryForm(request.data)
        if delivery_form.is_valid() :
            delivery = delivery_form.save(commit=False)
            blend=Blend.objects.get(pk=item_pk)
            delivery.in_item = blend
            delivery.save()
            print('저장')
    else :
        delivery_form = DeliveryForm()
    print(delivery_form)
    return Response(data={'ok'},status=status.HTTP_200_OK)

@api_view(['GET'])
def delete_delivery(request,delivery_pk) :
    delivery = Delivery.objects.get(pk=delivery_pk)
    delivery.delete()
    return Response(data={'ok'},status=status.HTTP_200_OK)

# item 등록
@api_view(['POST'])
def add_raw_material(request):
    database = Item.objects.all()

    print(request.data["content"])

    database = Item(name=request.data["name"],content=request.data["content"],image=request.data["image"],
                    country = request.data["country"]).save()
    print(2)
    result = {}
    return Response(data=result, status=status.HTTP_200_OK)

# item 보여주는 페이지
@api_view(['GET'])
def get_item(request):
    queryset = Item.objects.all()
    serializer = ItemSerializer(queryset,many=True)
    print(serializer)
    return Response(data=serializer.data, status=status.HTTP_200_OK)

#item 삭제 페이지
@api_view(['POST'])
def delete_item(request):
    tmp = Item.objects.get(pk=request.data['pk'])
    tmp.delete()
    result = {}
    return Response(data=result, status=status.HTTP_200_OK)

# blend 등록
@api_view(['POST'])
def add_blend(request):
    # array가 빈 배열이면 return
    length = len(request.data['array'])
    lists = request.data['array']
    tmpnum = 0
    for i in lists :
        tmpnum += i[1]
    if tmpnum == 100 :
        # 중복 체크 (item이 전부 일치하면 return)
        ans = Blend.objects.create(content=request.data['content'])
        methods = ''
        string = ''
        for i in lists :
            ans.items.add(Item.objects.get(name=i[0]))
            string += str(i[1]) +','
            methods += i[0] + ' + '
        ans.percents = string[:-1]
        ans.method = methods[:-2]
        ans.save()
        return Response(data=True, status=status.HTTP_200_OK)
    else :
        return Response(data=False, status=status.HTTP_200_OK)

# 블렌드 삭제
@api_view(['POST'])
def delete_blend(request, blend_pk):
    if request.method == "POST":
        query = Blend.objects.get(pk=blend_pk)
        query.delete()
        return Response(data=True, status=status.HTTP_200_OK)