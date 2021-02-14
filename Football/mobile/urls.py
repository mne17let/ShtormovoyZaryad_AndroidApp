from django.urls import path
from django.conf.urls import include, url
from . import views
from django.views.decorators.csrf import csrf_exempt

urlpatterns = [
    path('start/', views.Start.as_view()),
    #path('autorization/', csrf_exempt(views.some_view.as_view())),
]
