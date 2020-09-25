from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from api.models import Profile, User, Delivery, Blend, Rating
import datetime


@api_view(['POST'])
def get_item(request):
    if request.method == 'POST':
        today = request.data.get('today')
        user = request.data.get('user')
        print(today,user)
        print(int(today[4:6]))
        profile = Profile.objects.get(user=User.objects.get(pk=user))
        flag = [False, False]
        items = [False, False]
        method = [False,False]
        delivery = Delivery.objects.filter(year=today[:4], month=int(today[4:6])-1)
        if delivery and not Rating.objects.filter(blend=delivery[0].in_item, user=profile):
            flag[0] = True
            items[0] = delivery[0].in_item.id
            method[0] = delivery[0].in_item.method
        delivery = Delivery.objects.filter(year=today[:4], month=int(today[4:6]))
        print(delivery)
        if delivery and not Rating.objects.filter(blend=delivery[0].in_item, user=profile):
            flag[1] = True
            items[1] = delivery[0].in_item.id
            method[1] = delivery[0].in_item.method
        result = [flag, items, method]
        return Response(data=result, status=status.HTTP_200_OK)

@api_view(['POST'])
def create(request):
    if request.method == 'POST':
        profile = Profile.objects.get(user=User.objects.get(pk=request.data.get('user')))
        # items는 blend 모델의 id
        items = request.data.get('items')
        # ratings은 [평점, 댓글]
        rating = request.data.get('rating')
        rate = Rating.objects.create(user=profile, blend=Blend.objects.get(pk=items), number=rating[0], comment=rating[1])
        return Response(status=status.HTTP_200_OK)