from django import forms

from .models import Item,Blend,Delivery

class DeliveryForm(forms.ModelForm):
    class Meta:
        model = Delivery
        fields = ('year','month','weeks')