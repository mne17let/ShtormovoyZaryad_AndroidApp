from django.shortcuts import render
from django.http import HttpResponse
from django.contrib.auth.mixins import LoginRequiredMixin
from django.views import View


class Start(View):
    def get(self, request):
        if request.user.is_authenticated:
            return HttpResponse(content="Yes", status=200)
        else:
            return HttpResponse(status=405)
'''
class some_view(View):
    def post(self, request):
        return HttpResponse(status=200)
'''
# Create your views here.
