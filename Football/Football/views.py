from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render
from django.views import View
from django.contrib.auth import authenticate, login
from django.contrib.auth.models import User
#from django.middleware.csrf import CsrfViewMiddleware
from django.views.decorators.csrf import ensure_csrf_cookie, get_token, requires_csrf_token, csrf_exempt
from django.contrib.auth.views import LoginView
from django.views.generic import CreateView
#from django.template.context_processors import csrf
from django.contrib.auth import login


class Login(LoginView):

    def post(self, request):
        user = authenticate(username=request.POST['username'], password=request.POST['password'])
        if user is not None:
            login(request, user)
            return HttpResponse(status=200)
        else:
            return HttpResponse(status=405)

    def get(self, request):
        h = HttpResponse(status=200)
        csrf_token = get_token(request)
        #h.set_cookie('csrfmiddlewaretoken', value=csrf_token)
        #request.setRequestHeader("X-CSRFToken", csrftoken);
        #h["X-CSRFToken"] =  csrf_token;
        return h


class Registration(CreateView):
    def post(self, request):
        if request.POST['password'] != request.POST['again_pass']:
            return HttpResponse(status=403)
        user = User.objects.create_user(request.POST['username'], request.POST['mail'], request.POST['password'])
        user.save()
        return HttpResponseRedirect('/login/')
