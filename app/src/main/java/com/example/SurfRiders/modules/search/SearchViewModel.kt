package com.example.SurfRiders.modules.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.SurfRiders.data.cocktail.Cocktail
import com.example.SurfRiders.data.cocktail.CocktailService

class SearchViewModel : ViewModel() {
    var cocktails: MutableLiveData<MutableList<Cocktail>> = MutableLiveData()

    fun refreshCocktails(query: String) {
        CocktailService.instance.searchCocktail(query) {
            cocktails.postValue(it)
        }
    }
    fun clearCocktails() {
        cocktails.postValue(mutableListOf())
    }
}
