from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render
from django.views import View
#from django.contrib.auth.mixins import LoginRequiredMixin
from django.contrib.auth import authenticate, login
from django.contrib.auth.models import User
#from django.core.context_processors import csrf

class Login(View):
    def post(self, request):
        user = authenticate(username=request.POST['username'], password=request.POST['password'])
        if user is not None:
            #return HttpResponseRedirect('/mobile/start/')
            login(request, user)
            return HttpResponse(status=200)
        else:
            #return HttpResponseRedirect('/login/')
            return HttpResponse(status=403)

    def get(self, request):
        h = HttpResponse(status=200)
        return h


class Registration(View):
    def post(self, request):
        if request.POST['password'] != request.POST['again_pass']:
            return HttpResponse(status=403)
        user = User.objects.create_user(request.POST['username'], request.POST['mail'], request.POST['password'])
        user.save()
        return HttpResponseRedirect('/login/')
