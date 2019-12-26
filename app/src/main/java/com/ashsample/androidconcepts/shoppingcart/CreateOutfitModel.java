package com.ashsample.androidconcepts.shoppingcart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

//Singleton model for the entire app

public class CreateOutfitModel {

    private static CreateOutfitModel INSTANACE;
    private List<Product> productsForOutfit;
    MutableLiveData<List<Product>> productsForOutfitLiveData = new MutableLiveData<>();

    static {
        INSTANACE = new CreateOutfitModel();
    }

    public static CreateOutfitModel getInstance() {
        return INSTANACE;
    }

    public void addProduct(Product product){
        if(productsForOutfit == null){
            productsForOutfit = new ArrayList<>();
        }
        productsForOutfit.add(product);
        productsForOutfitLiveData.setValue(productsForOutfit);
    }
     public void removeProduct(Product p){
        if(productsForOutfit!= null && productsForOutfit.size()>0) {
            for (Product temp : productsForOutfit) {
                if (temp.getSku().equalsIgnoreCase(p.getSku())) {
                    productsForOutfit.remove(p);
                    productsForOutfitLiveData.setValue(productsForOutfit);
                    break;
                }
            }
        }
     }
    public LiveData<List<Product>> getAllProductsForOutFit(){
        return productsForOutfitLiveData;
    }








}
