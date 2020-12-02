from django.conf.urls import include,url
from django.urls import path
from . import views
from . import parse

from api.tmpviews import auth_views
from api.tmpviews import rating_views
from api.tmpviews import filter_views

app_name='api'
urlpatterns = [
    url(r'^Percent/get/',parse.Percent_parse), 
    url(r'^Flavor/get/',parse.Flavor_parse), 
    path('delivery/',views.list_delivery, name='list_delivery'),
    path('items/',views.list_items, name='list_item'),
    path('blend/',views.list_blend, name='list_blend'),

    path('detail/blend/<int:blend_pk>',views.detail_blend,name='detail_blend'),
    path('add/delivery/<int:item_pk>',views.add_delivery,name='add_delivery'),
    path('delete/delivery/<int:delivery_pk>',views.delete_delivery,name='delete_delivery'),

    path('thismonth/delivery/<str:year>/<str:month>',views.this_delivery,name="this_delivery"),
    path('add_rawdata/',views.add_raw_material,name='add_material'),
    path('get_item/',views.get_item, name='get_item'),
    path('delete_item/',views.delete_item, name='delete_item'),
    path('add_blend/',views.add_blend,name='addblend'),
    path('blend/delete/<int:blend_pk>/',views.delete_blend,name='deleteblend'),

    path('auth/signup/', auth_views.signup, name='sign_up'),
    path('auth/signin/', auth_views.signin, name='sign_in'),
    path('auth/logout/', auth_views.logout, name='logout'),
    path('auth/<int:user_id>/', auth_views.detail, name='user_detail'),

    path('auth/alluser/', auth_views.alluser, name='alluser'),
    path('auth/user/<int:user_pk>/delete', auth_views.user_delete, name='user_delete'),

    # 구독 관리
    path('subscription/create/', auth_views.create, name='subscription_create'),
    path('subscription/manager/', auth_views.manager, name="subscription_manager"),

    # 평점 관리
    path('rating/items/', rating_views.get_item, name='get_item'),
    path('rating/create/', rating_views.create, name='rating_create'),

    # 추천 관리
    path('filtering/', filter_views.filtering, name='filtering'),
]
