import random
import math
from .models import Movie
import numpy as np
import pandas as pd
from django.utils.text import slugify

from rest_framework.response import Response
from rest_framework.decorators import api_view


df = pd.read_csv("./answer.csv")

id = df['title'].values
f1 = df["Action"].values
f2 = df["Adventure"].values
f3 = df["Children's"].values
f4 = df["Comedy"].values
f5 = df["Crime"].values
f6 = df["Documentary"].values
f7 = df["Drama"].values
f8 = df["Fantasy"].values
f9 = df["Film-Noir"].values
f10 = df["Horror"].values
f11 = df["Musical"].values
f12 = df["Mystery"].values
f13 = df["Romance"].values
f14 = df["Sci-Fi"].values
f15 = df["Thriller"].values
f16 = df["War"].values
f17 = df["Western"].values


original_data = np.array(list(zip(id, f1, f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17)))

data = np.array(list(zip( f1, f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17)))

def init_centroids(k, data):
    c = []
    s = np.random.randint(low=1, high=len(data), size=k)
    while (len(s) != len(set(s))):
        s = np.random.randint(low=1, high=len(data), size=k)
    for i in s:
        c.append(data[i])
    return c

def euc_dist(a, b):
    sum = 0
    for i, j in zip(a, b):
        a = (i - j) * (i - j)
        sum = sum + a
    return math.sqrt(sum)

def cal_dist(centroids, data):
    c_dist = []
    for i in centroids:
        temp = []
        for j in data:
            temp.append(euc_dist(i, j))
        c_dist.append(temp)
    return c_dist

def perf_clustering(k, dist_table):
    clusters = []
    for i in range(k):
        clusters.append([])
    for i in range(len(dist_table[0])):
        d = []
        for j in range(len(dist_table)):
            d.append(dist_table[j][i])
        clusters[d.index(min(d))].append(i)
    return clusters

def update_centroids(centroids, cluster_table, data):
    for i in range(len(centroids)):
        if (len(cluster_table[i]) > 0):
            temp = []
            for j in cluster_table[i]:
                temp.append(list(data[j]))
            sum = [0] * len(centroids[i])
            for l in temp:
                sum = [(a + b) for a, b in zip(sum, l)]
            centroids[i] = [p / len(temp) for p in sum]

    return centroids


def check_n_stop(dist_mem, cluster_mem):
    c1 = all(x == dist_mem[0] for x in dist_mem)
    c2 = all(y == cluster_mem[0] for y in cluster_mem)

    if c1:
        print("Stopping... Distance table has not changed from few iterations")
    elif c2:
        print("Stopping... Cluster table has not changed from few iterations")
    return c1 or c2

    
def kMeans(movie_name, k, data, max_iterations):
    dist_mem = []
    cluster_mem = []
    centroids = init_centroids(k, data)
    distance_table = cal_dist(centroids, data)
    cluster_table = perf_clustering(k, distance_table)
    newCentroids = update_centroids(centroids, cluster_table, data)

    dist_mem.append(distance_table)
    cluster_mem.append(cluster_table)

    for i in range(max_iterations):
        distance_table = cal_dist(newCentroids, data)
        cluster_table = perf_clustering(k, distance_table)
        newCentroids = update_centroids(newCentroids, cluster_table, data)

        dist_mem.append(distance_table)
        cluster_mem.append(cluster_table)
        if len(dist_mem) > 10:
            dist_mem.pop(0)
            cluster_mem.pop(0)
            if check_n_stop(dist_mem, cluster_mem):
                print("Stopped at iteration #", i)
                break

    answer = -1
    # for i in range(len(newCentroids)):
    for i in range(4) :
        print("Centroid #", i, ": ", newCentroids[i])
        print("Members of the cluster: ")
        print(len(original_data[cluster_table[i]]))
        for j in range(len(cluster_table[i])):
            if movie_name == str(original_data[cluster_table[i][j]])[2:len(movie_name)+2] :
                answer = i
                break
        if answer != -1 :
            break
    print(answer)
    lists = []
    for i in range(5) :
        # print(original_data[cluster_table[answer][int(random.random() * 13124571) % len(original_data[cluster_table[answer]])]][0])
        lists.append(original_data[cluster_table[answer][int(random.random() * 13124571) % len(original_data[cluster_table[answer]])]][0])
    return lists

@api_view(['GET'])
def solve(request,slug):
    movie = Movie.objects.get(slug=slug)

    print(movie.title)
    lists = kMeans(movie.title, 4, data, 100)

    list_slug = []
    for i in lists:
        slug = slugify(i)
        list_slug.append(slug)

    dataz = { 'movies': [] }
    dataz['movies'] = list_slug
    return Response(data=dataz)