package com.example.SurfRiders.modules.feed


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.SurfRiders.data.review.Review
import com.example.SurfRiders.data.review.ReviewModel
import com.example.SurfRiders.data.user.User
import com.example.SurfRiders.data.user.UserModel
import org.json.JSONObject


class CocktailsFeedViewModel() : ViewModel() {

    val reviews: LiveData<MutableList<Review>> = ReviewModel.instance.getAllReviews()
    val users: LiveData<MutableList<User>> = UserModel.instance.getAllUsers()
    val reviewsListLoadingState: MutableLiveData<ReviewModel.LoadingState> =
        ReviewModel.instance.reviewsListLoadingState

    fun reloadData() {
        UserModel.instance.refreshAllUsers()
        ReviewModel.instance.refreshAllReviews()
    }

    fun JSONObject.toMap(): Map<String, Any> {
        val keys = keys()
        val map = mutableMapOf<String, Any>()
        while (keys.hasNext()) {
            val key = keys.next()
            val value = this.get(key)
            map[key] = value
        }
        return map
    }
}