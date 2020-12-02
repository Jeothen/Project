from django.shortcuts import render, redirect, get_object_or_404
from django.contrib.auth import get_user_model
from django.contrib.auth import login as auth_login
from django.contrib.auth import logout as auth_logout
from django.contrib.auth.forms import AuthenticationForm
from django.contrib.auth.decorators import login_required
from django.views.decorators.http import require_http_methods, require_POST
from .models import User
from .forms import UserCustomCreationForm

    
def index(request):
    users = User.objects.order_by('-pk')
    context = {'users':users}
    return render(request,'accounts/index.html',context)
     
# from django.db.models import Max
# def detail(request, user_pk):
#     User = get_user_model()
#     user = get_object_or_404(User, pk=user_pk)
#     followings = user.followings.annotate(max_value=Max('score__value'))
#     context = {'user_info': user,'followings':followings}
#     return render(request, 'accounts/detail.html', context)

from django.db.models import Prefetch, Count
from movies.models import Score


#** 자세하게 공부(prefetch_related, select_related)
def detail(request, user_pk):
    User = get_user_model()
    user = get_object_or_404(User.objects.annotate(
                            followers_count=Count('followers'), 
                                followings_count=Count('followings')
                                ), pk=user_pk)
    followings = user.followings.prefetch_related(
                                            Prefetch('score_set',
                                            queryset=Score.objects.select_related('movie').order_by('-value'),
                                            to_attr='score_set_value_order'
                                            ))
    context = {'user_info': user, 'followings': followings}
    return render(request, 'accounts/detail.html', context)


def follow(request, user_pk):
    User = get_user_model()
    target_user = get_object_or_404(User, pk=user_pk)
    if request.user in target_user.followers.all():
        target_user.followers.remove(request.user)
    else:
        target_user.followers.add(request.user)
    return redirect('accounts:detail', user_pk)
    
# Create your views here.
@require_http_methods(["GET", "POST"])
def signup(request):
    if request.user.is_authenticated: # 이미 로그인이 되어 있다면
        return redirect('movies:list')
    if request.method == 'POST': 
        user_form = UserCustomCreationForm(request.POST)
        if user_form.is_valid():
            user = user_form.save()
            auth_login(request, user)
            return redirect('movies:list')
    else:
        user_form = UserCustomCreationForm()
    context = {'form': user_form}
    return render(request, 'accounts/forms.html', context)

def login(request):
    if request.user.is_authenticated:
        return redirect('movies:list')
    if request.method == 'POST':
        login_form = AuthenticationForm(request, request.POST)
        if login_form.is_valid():
            auth_login(request, login_form.get_user())
            return redirect('movies:list')
    else:
        login_form = AuthenticationForm()
    context = {'form': login_form}
    return render(request, 'accounts/forms.html', context)

@login_required
def logout(request):
    auth_logout(request)
    return redirect('movies:list')
