package com.example.sportreservation.utils

import android.content.Context
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

}