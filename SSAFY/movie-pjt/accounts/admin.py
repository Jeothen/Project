from django.contrib import admin
from .models import User
# Register your models here.
class UserAdmin(admin.ModelAdmin):
    list_display = ['id','email','last_name','first_name']
    
admin.site.register(User, UserAdmin)