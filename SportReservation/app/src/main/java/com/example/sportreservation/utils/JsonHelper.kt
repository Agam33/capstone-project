package com.example.sportreservation.utils

import android.content.Context
import com.example.sportreservation.data.source.remote.response.ArticleResponse
import com.example.sportreservation.data.source.remote.response.EquipmentResponse
import com.example.sportreservation.data.source.remote.response.RefereeResponse
import com.example.sportreservation.data.source.remote.response.SportPlaceResponse
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadBasketPlace(): List<SportPlaceResponse> {
        val list = ArrayList<SportPlaceResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("Basket.json").toString())
            val listArray = responseObject.getJSONArray("basket")
            for(item in 0 until listArray.length()) {
                val place = listArray.getJSONObject(item)

                list.add(
                    SportPlaceResponse(
                        place.getInt("id"),
                        place.getString("nama"),
                        place.getString("alamat"),
                        place.getString("buka"),
                        place.getString("tutup"),
                        place.getString("harga"),
                        place.getString("noTlp"),
                        place.getString("fasilitas"),
                        place.getString("imgUrl")
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun loadBadmintonPlace(): List<SportPlaceResponse> {
        val list = ArrayList<SportPlaceResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("Badminton.json").toString())
            val listArray = responseObject.getJSONArray("badminton")
            for(item in 0 until listArray.length()) {
                val place = listArray.getJSONObject(item)

                list.add(
                    SportPlaceResponse(
                        place.getInt("id"),
                        place.getString("nama"),
                        place.getString("alamat"),
                        place.getString("buka"),
                        place.getString("tutup"),
                        place.getString("harga"),
                        place.getString("noTlp"),
                        place.getString("fasilitas"),
                        place.getString("imgUrl")
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun loadFutsalPlace(): List<SportPlaceResponse> {
        val list = ArrayList<SportPlaceResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("Futsal.json").toString())
            val listArray = responseObject.getJSONArray("futsal")
            for(item in 0 until listArray.length()) {
                val place = listArray.getJSONObject(item)

                list.add(
                    SportPlaceResponse(
                        place.getInt("id"),
                        place.getString("nama"),
                        place.getString("alamat"),
                        place.getString("buka"),
                        place.getString("tutup"),
                        place.getString("harga"),
                        place.getString("noTlp"),
                        place.getString("fasilitas"),
                        place.getString("imgUrl")
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun loadGolfPlace(): List<SportPlaceResponse> {
        val list = ArrayList<SportPlaceResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("Golf.json").toString())
            val listArray = responseObject.getJSONArray("golf")
            for (item in 0 until listArray.length()) {
                val place = listArray.getJSONObject(item)

                list.add(
                    SportPlaceResponse(
                        place.getInt("id"),
                        place.getString("nama"),
                        place.getString("alamat"),
                        place.getString("buka"),
                        place.getString("tutup"),
                        place.getString("harga"),
                        place.getString("noTlp"),
                        place.getString("fasilitas"),
                        place.getString("imgUrl")
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun loadArticle(): List<ArticleResponse> {
        val list = ArrayList<ArticleResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("Article.json").toString())
            val listArray = responseObject.getJSONArray("article")
            for(item in 0 until listArray.length()) {
                val article = listArray.getJSONObject(item)

                list.add(
                    ArticleResponse(
                        article.getInt("id"),
                        article.getString("title"),
                        article.getString("writer"),
                        article.getString("imgUrl"),
                        article.getString("content")
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun loadReferee(): List<RefereeResponse> {
        val list = ArrayList<RefereeResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("Referee.json").toString())
            val listArray = responseObject.getJSONArray("referee")
            for (item in 0 until listArray.length()) {
                val referee = listArray.getJSONObject(item)

                list.add(
                    RefereeResponse(
                        referee.getInt("id"),
                        referee.getString("name"),
                        referee.getString("price"),
                        referee.getString("imgUrl")
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun loadEquipment(): List<EquipmentResponse> {
        val list = ArrayList<EquipmentResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("Equipment.json").toString())
            val listArray = responseObject.getJSONArray("equipment")
            for (item in 0 until listArray.length()) {
                val referee = listArray.getJSONObject(item)

                list.add(
                    EquipmentResponse(
                        referee.getInt("id"),
                        referee.getString("name"),
                        referee.getString("price"),
                        referee.getString("imgUrl")
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }
}