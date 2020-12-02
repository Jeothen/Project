from django.shortcuts import render,redirect,get_object_or_404
from .models import Movie, Score, Genre
from django.core.paginator import Paginator

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
    ages = ['전체관람가','12세관람가','15세관람가','청소년관람불가']
    movies = Movie.objects.order_by('-pk')
    paginator = Paginator(movies, 9)
    page = request.GET.get('page')
    contacts = paginator.get_page(page)
    context = {'movies':movies, 'contacts': contacts, 'genres':genres, 'ages':ages}
    return render(request, 'movies/list.html',context)

def detail(request,movie_pk):
    genres = Genre.objects.all()
    ages = ['전체관람가','12세관람가','15세관람가','청소년관람불가']
    movie = Movie.objects.get(pk=movie_pk)
    form = ScoreForm()
    context = {'movie':movie,'form':form, 'genres':genres,'ages':ages}
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
    ages = ['전체관람가','12세관람가','15세관람가','청소년관람불가']
    if request.method == 'GET':
        client_id = 'Qvz4DajtC_EQIslZpC2y'
        client_secret = 'EqTf2tY_EH'

        q = request.GET.get('q')
        encText = urllib.parse.quote("{}".format(q))
        url = "https://openapi.naver.com/v1/search/movie?query=" + encText  # json 결과
        movie_api_request = urllib.request.Request(url)
        movie_api_request.add_header("X-Naver-Client-Id", client_id)
        movie_api_request.add_header("X-Naver-Client-Secret", client_secret)
        response = urllib.request.urlopen(movie_api_request)
        rescode = response.getcode()
        if (rescode == 200):
            response_body = response.read()
            result = json.loads(response_body.decode('utf-8'))
            items = result.get('items')
            
            # print(result)  # request를 예쁘게 출력해볼 수 있다.

            context = {
                'items': items,
                'genres':genres,
                'ages':ages
            }
            return render(request, 'movies/search.html', context=context)
            

def searching(request):
    genres = Genre.objects.all()
    ages = ['전체관람가','12세관람가','15세관람가','청소년관람불가']
    if request.method == 'GET':
        client_id = 'Qvz4DajtC_EQIslZpC2y'
        client_secret = 'EqTf2tY_EH'

        q = request.GET.get('q')
        encText = urllib.parse.quote("{}".format(q))
        url = "https://openapi.naver.com/v1/search/movie?query=" + encText  # json 결과
        movie_api_request = urllib.request.Request(url)
        movie_api_request.add_header("X-Naver-Client-Id", client_id)
        movie_api_request.add_header("X-Naver-Client-Secret", client_secret)
        response = urllib.request.urlopen(movie_api_request)
        rescode = response.getcode()
        if (rescode == 200):
            response_body = response.read()
            result = json.loads(response_body.decode('utf-8'))
            items = result.get('items')
            
            # print(result)  # request를 예쁘게 출력해볼 수 있다.

            context = {
                'items': items,
                'genres':genres,
                'ages':ages
            }
            return render(request, 'movies/searching.html', context=context)
            


def like(request, post_pk):
    genres = Genre.objects.all()
    ages = ['전체관람가','12세관람가','15세관람가','청소년관람불가']
    post = get_object_or_404(Post, pk=post_pk)
    user = request.user
    # user가 지금 해당 게시글에 좋아요를 한적이 있는지?
    # if user in post.like_users.all():
    #     post.like_users.remove(user)
    # else:
    #     post.like_users.add(user)
    if post.like_users.filter(pk=user.id).exists():
        post.like_users.remove(user)
        is_like = False
    else:
        post.like_users.add(user)
        is_like = True
    return JsonResponse({'is_like': is_like, 'count': post.like_users.count(), 'genres':genres, 'ages':ages})


def genremovie(request,genre_pk):
    genres = Genre.objects.all()
    ages = ['전체관람가','12세관람가','15세관람가','청소년관람불가']
    genre1 = Genre.objects.get(pk=genre_pk)
    movies = Movie.objects.filter(genre=genre1).order_by('-pk')
    # 장르 pk를 땡겨오고 그거에 관련된 영화만 
    paginator = Paginator(movies, 9)
    page = request.GET.get('page')
    contacts = paginator.get_page(page)
    genres = Genre.objects.all()
    return render(request, 'movies/list.html',context)

def yearmovie(request, date):
    genres = Genre.objects.all()
    ages = ['전체 관람가','12세 관람가','15세 관람가','청소년 관람불가']
    movies = Movie.objects.all()
    context = {'date':date}
    return render(request,'movies/yearmovie.html',context)
    

def agemovie(request, age):
    print(age)
    genres = Genre.objects.all()
    ages = ['전체관람가','12세관람가','15세관람가','청소년관람불가']
    movies = Movie.objects.filter(age=age).order_by('-pk')
    paginator = Paginator(movies, 9)
    page = request.GET.get('page')
    contacts = paginator.get_page(page)
    context = {'movies':movies, 'contacts': contacts, 'genres':genres, 'ages':ages}
    return render(request, 'movies/list.html', context)
    
# {'title': '<b>어벤져스</b>: 엔드게임', 
# 'link': 'https://movie.naver.com/movie/bi/mi/basic.nhn?code=136900', 
# 'image': 'https://ssl.pstatic.net/imgmovie/mdi/mit110/1369/136900_P57_104126.jpg', 
# 'subtitle': 'Avengers: Endgame', 'pubDate': '2019', 
# 'director': '안소니 루소|조 루소|', 
# 'actor': '로버트 다우니 주니어|크리스 에반스|크리스 헴스워스|마크 러팔로|스칼렛 요한슨|제레미 레너|돈 치들|폴 러드|브리 라슨|카렌 길런|브래들리 쿠퍼|조슈 브롤린|', 
# 'userRating': '9.41'}