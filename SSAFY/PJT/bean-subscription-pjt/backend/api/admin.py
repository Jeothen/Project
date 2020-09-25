from django.contrib import admin
from .models import Delivery, Item, Percent, Blend,Profile, Subscription_manager,Rating

admin.site.register(Delivery)
admin.site.register(Item)
admin.site.register(Percent)
admin.site.register(Blend)
admin.site.register(Profile)
admin.site.register(Subscription_manager)
admin.site.register(Rating)

