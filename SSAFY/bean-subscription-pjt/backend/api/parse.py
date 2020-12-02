from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view

from django.contrib.auth.models import User
from django.http import JsonResponse,HttpResponseBadRequest
from .models import Delivery, Percent, Item, Blend
import requests
import csv

@api_view(['POST'])
def Percent_parse(request) :
    if request.method == 'POST':
        ratings = request.data.get('ratings', None)
        for word in ratings :
            a1=Blend(content=word['content'],method=word['method'],percents=word['percents'])
            print(word['method'])
            print(word['percents'])
            a1.save()
            a=word['items'].split(',')
            for num in a :
                item=Item.objects.get(pk=int(num))
                a1.items.add(item)
        # print('감')
    return Response(data="{ok}",status=status.HTTP_200_OK)

@api_view(['POST'])
def Flavor_parse(request) :
    if request.method == 'POST':
        ratings = request.data.get('ratings', None)
        for word in ratings :
            a1=Item(name=word['name'],content=word['content'],image=word['image'],country=word['country'])
            # print(word['name'])
            # print(word['percents'])
            a1.save()
            # print(word)
            # print(word['items'])
        print('감')
    return Response(data="{ok}",status=status.HTTP_200_OK)