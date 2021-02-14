from django.shortcuts import render
from django.http import HttpResponse
from django.contrib.auth.mixins import LoginRequiredMixin
from django.views import View


class Start(LoginRequiredMixin, View):
    def get(self, request):
        return HttpResponse(status=200)

'''
class some_view(View):
    def post(self, request):
        return HttpResponse(status=200)
'''
# Create your views here.
