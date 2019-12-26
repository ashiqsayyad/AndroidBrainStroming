package com.ashsample.androidconcepts.shoppingcart;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class CreateOutfitViewModel extends AndroidViewModel {
    private CreateOutfitModel model;
    private LiveData<List<Product>> productsForOutfitLiveData;

    public CreateOutfitViewModel(@NonNull Application application) {
        super(application);
        model = CreateOutfitModel.getInstance();
        productsForOutfitLiveData = model.getAllProductsForOutFit();

    }
    public LiveData<List<Product>> getAllProductsForOutFit(){
        return productsForOutfitLiveData;
    }

}
