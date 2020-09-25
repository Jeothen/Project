from django.shortcuts import render,redirect,get_object_or_404
from .models import Movie, Score, Genre, Actor, Director
from django.core.paginator import Paginator
from django.http import JsonResponse, HttpResponseBadRequest
from django.contrib.auth.decorators import login_required
from .forms import ScoreForm
import json
import urllib.request


# 네브바 - 검색 로그인 홈 
# 메인 페이지 - 탑50 (다음영화처럼보이게), 추천영화
# 검색 - 검색 결과에서 디테일 넘어갈수있게 + 팔로우기능
# 디테일 - 태그

# Create your views here.
def list(request):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    movies = Movie.objects.order_by('-pk')
    paginator = Paginator(movies, 9)
    page = request.GET.get('page')
    contacts = paginator.get_page(page)
    context = {'movies':movies, 'contacts': contacts, 'genres':genres, 'ages':ages, 'years':years, 'key':'list'}
    return render(request, 'movies/list.html',context)
    
def actorlist(request):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    actors = Actor.objects.order_by('-pk')
    paginator = Paginator(actors, 9)
    page = request.GET.get('page')
    contacts = paginator.get_page(page)
    context = {'actors':actors, 'contacts': contacts, 'genres':genres, 'ages':ages, 'years':years, 'key':'actor'}
    return render(request, 'movies/list.html',context)
    
def directorlist(request):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    directors = Director.objects.order_by('-pk')
    paginator = Paginator(directors, 9)
    page = request.GET.get('page')
    contacts = paginator.get_page(page)
    context = {'directors':directors, 'contacts': contacts, 'genres':genres, 'ages':ages, 'years':years, 'key':'director'}
    return render(request, 'movies/list.html',context)

def detail(request,movie_pk):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    movie = Movie.objects.get(pk=movie_pk)
    # movie.watch += 1
    # movie.save()
    form = ScoreForm()
    context = {'movie':movie,'form':form, 'genres':genres,'ages':ages, 'years':years}
    return render(request,'movies/detail.html',context)
    
@login_required
def score_create(request, movie_pk):
    movie = get_object_or_404(Movie, pk=movie_pk)
    if request.method == 'POST':
        form = ScoreForm(request.POST)
        if form.is_valid():
            score = form.save(commit=False)
            score.user = request.user
            score.movie = movie
            score.save()
            return redirect(movie)
    return redirect(movie)

def score_delete(request,movie_pk,score_pk):
    if request.method == 'POST':
        score = Score.objects.get(pk=score_pk)
        score.delete()
    return redirect('movies:detail',movie_pk)

def search(request):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    itext = request.POST.get('itext')
    movies = Movie.objects.filter(title__contains=itext)
    if len(movies) == 0 : movieheight = 2.5
    else : 
        if len(movies) % 3 == 0: 
            movieheight = len(movies) // 3 *40 + 10
        else:
            movieheight = len(movies) // 3 * 40 + 50
    actors = Actor.objects.filter(name__contains=itext)
    directors = Director.objects.filter(name__contains=itext)
    context = {'movies':movies, 'actors':actors,'directors':directors, 'genres':genres,'ages':ages, 'years':years, 'itext':itext, 
    'movieheight':movieheight }
    return render(request, 'movies/search.html', context=context)
            

def like(request, movie_pk):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    movie = get_object_or_404(Movie, pk=movie_pk)
    user = request.user
    if user in movie.like_users.all():
        movie.like_users.remove(user)
    else:
        movie.like_users.add(user)
    return redirect('movies:detail',movie_pk)

def genremovie(request,genre_pk):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    genre1 = Genre.objects.get(pk=genre_pk)
    movies = Movie.objects.filter(genre=genre1).order_by('-pk')
    # 장르 pk를 땡겨오고 그거에 관련된 영화만 
    paginator = Paginator(movies, 9)
    page = request.GET.get('page')
    contacts = paginator.get_page(page)
    context = {'movies':movies, 'contacts': contacts, 'genres':genres, 'ages':ages, 'years':years}
    return render(request, 'movies/list.html',context)

def yearmovie(request, year):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    if year == 2009:
        movies = Movie.objects.filter(dates__startswith=200).order_by('-dates')
    else:
        movies = Movie.objects.filter(dates__startswith=year).order_by('-dates')
    paginator = Paginator(movies, 9)
    page = request.GET.get('page')
    contacts = paginator.get_page(page)
    context = {'movies':movies, 'contacts': contacts, 'genres':genres, 'ages':ages, 'years':years}
    return render(request,'movies/list.html',context)
    

def agemovie(request, age):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    movies = Movie.objects.filter(age=age).order_by('-pk')
    paginator = Paginator(movies, 9)
    page = request.GET.get('page')
    contacts = paginator.get_page(page)
    context = {'movies':movies, 'contacts': contacts, 'genres':genres, 'ages':ages, 'years':years}
    return render(request, 'movies/list.html', context)
    
def manywatching(request):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    movies = Movie.objects.all().order_by('-audience')
    paginator = Paginator(movies, 9)
    page = request.GET.get('page')
    contacts = paginator.get_page(page)
    context = {'movies':movies, 'contacts': contacts, 'genres':genres, 'ages':ages, 'years':years }
    return render(request,'movies/list.html',context)
    
def notwatching(request):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    movies = Movie.objects.all().order_by('-audience')
    lists = []
    for movie in movies:
        if not movie in request.user.like_posts.all():
            lists.append(movie)
            if len(lists) == 3 : break
    paginator = Paginator(lists, 9)
    page = request.GET.get('page')
    contacts = paginator.get_page(page)
    context = {'movies':movies, 'contacts': contacts, 'genres':genres, 'ages':ages, 'years':years ,'lists':lists}
    return render(request,'movies/list.html',context)
    
def genrerecommend(request):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    movies = Movie.objects.all().order_by('-audience')
    lists = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
    result = []
    for movie in movies :
        if movie in request.user.like_posts.all():
            for genre in movie.genre.all():
                lists[genre.id-1] += 1
    a = Genre.objects.filter(id=lists.index(max(lists))+1)
    for amovie in a[0].genremovies.all().order_by('-audience'):
        if not amovie in request.user.like_posts.all():
            result.append(amovie)
            if len(result)==3:
                break
    paginator = Paginator(result, 9)
    page = request.GET.get('page')
    contacts = paginator.get_page(page)
    context = {'movies':movies, 'contacts': contacts, 'genres':genres, 'ages':ages, 'years':years ,'lists':lists}
    return render(request,'movies/list.html',context)
    
def actorrecommend(request):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    movies = Movie.objects.all().order_by('-audience')
    result = []
    for movie in movies :
        for actor in movie.actors.all():
            if actor in request.user.like_actors.all():
                print(actor)
                result.append(movie)
                break
        if len(result) > 2 : break
    paginator = Paginator(result, 9)
    page = request.GET.get('page')
    contacts = paginator.get_page(page)
    context = {'movies':movies, 'contacts': contacts, 'genres':genres, 'ages':ages, 'years':years ,'result':result}
    return render(request,'movies/list.html',context)

def directorrecommend(request):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    movies = Movie.objects.all().order_by('-audience')
    result = []
    for movie in movies :
        for director in movie.directors.all():
            if director in request.user.like_directors.all():
                result.append(movie)
                break
        if len(result) > 2 : break
    paginator = Paginator(result, 9)
    page = request.GET.get('page')
    contacts = paginator.get_page(page)
    context = {'movies':movies, 'contacts': contacts, 'genres':genres, 'ages':ages, 'years':years ,'result':result}
    return render(request,'movies/list.html',context)



# 관심 영화 뿐 아니라 관심 배우 누르는 기능 추가해서 관심 배우가 출연한 영화 추천 알고리즘 
def actordetail(request,actor_pk):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    actor = Actor.objects.get(pk=actor_pk)
    context = {'actor':actor,'genres':genres, 'ages':ages, 'years':years}
    return render(request,'movies/actordetail.html',context)
    print(actor)


def directordetail(request,director_pk):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    
    director = Director.objects.get(pk=director_pk)
    context = {'director':director,'genres':genres, 'ages':ages, 'years':years}
    return render(request,'movies/directordetail.html',context)

@ login_required
def likeactor(request, actor_pk):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    actor = get_object_or_404(Actor, pk=actor_pk)
    user = request.user
    if user in actor.likeactor_users.all():
        actor.likeactor_users.remove(user)
    else:
        actor.likeactor_users.add(user)
    return redirect('movies:actordetail',actor_pk)

@ login_required
def likedirector(request, director_pk):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    director = get_object_or_404(Director, pk=director_pk)
    user = request.user
    if user in director.likedirector_users.all():
        director.likedirector_users.remove(user)
    else:
        director.likedirector_users.add(user)
    return redirect('movies:directordetail',director_pk)

@ login_required
def mypage(request):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    years = [2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009]
    likemovies = request.user.like_posts.all()
    likeactors = request.user.like_actors.all()
    likedirectors = request.user.like_directors.all()
    if len(likemovies) == 0 : movieheight = 2.5
    else : 
        if len(likemovies) % 3 == 0: 
            movieheight = len(likemovies) // 3 *40 + 10
        else:
            movieheight = len(likemovies) // 3 * 40 + 50
    context = {'genres':genres, 'ages':ages, 'years':years, 'likemovies':likemovies, 'likeactors':likeactors,'likedirectors':likedirectors,
                'movieheight':movieheight}
    return render(request,'movies/mypage.html',context)

# {'title': '<b>어벤져스</b>: 엔드게임', 
# 'link': 'https://movie.naver.com/movie/bi/mi/basic.nhn?code=136900', 
# 'image': 'https://ssl.pstatic.net/imgmovie/mdi/mit110/1369/136900_P57_104126.jpg', 
# 'subtitle': 'Avengers: Endgame', 'pubDate': '2019', 
# 'director': '안소니 루소|조 루소|', 
# 'actor': '로버트 다우니 주니어|크리스 에반스|크리스 헴스워스|마크 러팔로|스칼렛 요한슨|제레미 레너|돈 치들|폴 러드|브리 라슨|카렌 길런|브래들리 쿠퍼|조슈 브롤린|', 
# 'userRating': '9.41'}