from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from api.models import Profile, User, Delivery, Blend, Rating


@api_view(['POST'])
def filtering(request):
    if request.method == 'POST':
        gender = request.data.get('gender')
        age = request.data.get('age')
        start = 2019-int(age)+1
        users = list(Profile.objects.filter(gender=gender, years__lte=start, years__gt=start-10))
        
        ratings = {}
        cnt = 0
        for user in users:
            for r in list(Rating.objects.filter(user=user)):
                # print(r.number)
                for name in r.blend.items.all():
                    if name.name in ratings:
                        ratings[name.name] += r.number
                    else:
                        ratings[name.name] = r.number
                cnt += 1

        result = []
        for name, score in ratings.items():
            score /=cnt
            result.append((score, name))
        result.sort(reverse=True)
        data = result[:10]
        print(data)
    return Response(data=data, status=status.HTTP_200_OK)

