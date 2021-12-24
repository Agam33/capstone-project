package com.example.sportreservation.data.source.remote

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sportreservation.data.source.remote.response.*
import com.example.sportreservation.utils.DB_BOOKING
import com.example.sportreservation.utils.JsonHelper
import com.example.sportreservation.utils.mainThreadDelay
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class RemoteDataSourceImpl(
    private val jsonHelper: JsonHelper
) : RemoteDataSource {

    override fun getBadmintonPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>> {
        val result = MutableLiveData<ApiResponse<List<SportPlaceResponse>>>()
        mainThreadDelay {
            result.postValue(ApiResponse.success(jsonHelper.loadBadmintonPlace()))
        }
        return result
    }

    override fun getBasketPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>> {
        val result = MutableLiveData<ApiResponse<List<SportPlaceResponse>>>()
        mainThreadDelay {
            result.postValue(ApiResponse.success(jsonHelper.loadBasketPlace()))
        }
        return result
    }

    override fun getFutsalPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>> {
        val result = MutableLiveData<ApiResponse<List<SportPlaceResponse>>>()
        mainThreadDelay {
            result.postValue(ApiResponse.success(jsonHelper.loadFutsalPlace()))
        }
        return result
    }

    override fun getGolfPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>> {
        val result = MutableLiveData<ApiResponse<List<SportPlaceResponse>>>()
        result.postValue(ApiResponse.success(jsonHelper.loadGolfPlace()))
        return result
    }

    override fun getFootballPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>> {
        val result = MutableLiveData<ApiResponse<List<SportPlaceResponse>>>()
        result.postValue(ApiResponse.success(jsonHelper.loadFootballPlace()))
        return result
    }

    override fun getArticle(): LiveData<ApiResponse<List<ArticleResponse>>> {
        val result = MutableLiveData<ApiResponse<List<ArticleResponse>>>()
        mainThreadDelay {
            result.value = ApiResponse.success(jsonHelper.loadArticle())
        }
        return result
    }

    override fun getReferee(): LiveData<List<RefereeResponse>> {
        val result = MutableLiveData<List<RefereeResponse>>()
        result.postValue(jsonHelper.loadReferee())
        return result
    }

    override fun getEquipment(): LiveData<List<EquipmentResponse>> {
        val result = MutableLiveData<List<EquipmentResponse>>()
        result.postValue(jsonHelper.loadEquipment())
        return result
    }

    override fun getOrderList(): LiveData<ApiResponse<List<OrderResponse>>> {
        val user = Firebase.auth.currentUser
        val result = MutableLiveData<ApiResponse<List<OrderResponse>>>()
        if(user != null) {
            val dbRef = FirebaseDatabase.getInstance().getReference(DB_BOOKING)
            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val bookList = ArrayList<OrderResponse>()
                    for(data in snapshot.children) {
                        val item = data.getValue(OrderResponse::class.java)
                        if(item?.userId == user.uid) {
                            bookList.add(item)
                        }
                    }
                    result.postValue(ApiResponse.success(bookList))
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }
        return result
    }
}