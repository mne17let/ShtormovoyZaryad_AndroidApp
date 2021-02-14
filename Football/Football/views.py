from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render
from django.views import View
from django.contrib.auth import authenticate, login
from django.contrib.auth.models import User
from django.views.decorators.csrf import ensure_csrf_cookie, get_token, requires_csrf_token, csrf_exempt
from django.contrib.auth.views import LoginView
from django.views.generic import CreateView
from django.contrib.auth import login
from django.core import validators


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
        return h


class Registration(CreateView):
    def post(self, request):
        try:
            if request.POST['password'] != request.POST['again_pass']:
                return HttpResponse(content="passwords don\'t match", status=405)
        except:
            return HttpResponse('bad password', status=405)

        try:
            validators.validate_email(request.POST['mail'])
        except:
            return HttpResponse(content='bad email', status=405)

        try:
            validators.validate_slug(request.POST['password'])
        except:
            return HttpResponse(content='bad password', status=405)

        try:
            validators.validate_slug(request.POST['username'])
        except:
            return HttpResponse(content='bad login', status=405)

        try:
            if User.objects.filter(username=request.POST['username']).exists():
                return HttpResponse(content='user already exists', status=405)
        except:
            pass

        try:
            user = User.objects.create_user(request.POST['username'], request.POST['mail'], request.POST['password'])
            user.save()
            return HttpResponse(content='user is successfully created', status=200)
        except:
            HttpResponse('something went wrong', status=406)
