import pandas as pd
import numpy as np

from rest_framework import status, generics, filters
from rest_framework.decorators import api_view, permission_classes
from rest_framework.response import Response
from rest_framework.permissions import AllowAny, IsAdminUser, IsAuthenticated
# req1-4

movies_df = pd.read_csv('./movies.csv')
ratings_df = pd.read_csv('./ratings.csv')

R_df = ratings_df.pivot(index = 'userId', columns ='movieId', values = 'rating').fillna(0)

R=R_df.as_matrix()
user_ratings_mean = np.mean(R, axis = 1)
R_demeaned = R - user_ratings_mean.reshape(-1, 1)

from scipy.sparse.linalg import svds
U, sigma, Vt = svds(R_demeaned, k = 50)

sigma = np.diag(sigma)
all_user_predicted_ratings = np.dot(np.dot(U, sigma), Vt) + user_ratings_mean.reshape(-1, 1)

# 영화 추천
preds_df = pd.DataFrame(all_user_predicted_ratings, columns = R_df.columns)

# req 1-6

def recommend_movies(predictions_df, userID, movies_df, original_ratings_df, num_recommendations=5):
    
    # Get and sort the user's predictions
    user_row_number = userID - 1 # UserID starts at 1, not 0
    sorted_user_predictions = preds_df.iloc[user_row_number].sort_values(ascending=False) # UserID starts at 1
    
    # Get the user's data and merge in the movie information.
    user_data = original_ratings_df[original_ratings_df.userId == (userID)]
    user_full = (user_data.merge(movies_df, how = 'left', left_on = 'movieId', right_on = 'movieId').
                     sort_values(['rating'], ascending=False)
                 )

    print ('User {0} has already rated {1} movies.'.format(userID, user_full.shape[0]))
    print ('Recommending highest {0} predicted ratings movies not already rated.'.format(num_recommendations))
    
    # Recommend the highest predicted rating movies that the user hasn't seen yet.
    recommendations = (movies_df[movies_df['movieId'].isin(user_full['movieId'])].
         merge(pd.DataFrame(sorted_user_predictions).reset_index(), how = 'left',
               left_on = 'movieId',
               right_on = 'movieId').
         rename(columns = {user_row_number: 'Predictions'}).
         sort_values('Predictions', ascending = False).
                       iloc[:num_recommendations, :-1]
                      )

    return user_full, recommendations

# already_rated, predictions = recommend_movies(preds_df, 1, movies_df, ratings_df,12)
# @permission_classes((IsAuthenticated, ))
@api_view(['GET'])
@permission_classes((AllowAny, ))
def mf(request, pk):
    print(request.data)
    if request.method == 'GET':
        already_rated, predictions = recommend_movies(preds_df, pk, movies_df, ratings_df,12)
        print(predictions)
        return Response(data=predictions)
