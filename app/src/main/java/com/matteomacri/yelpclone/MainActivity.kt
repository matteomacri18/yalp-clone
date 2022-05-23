package com.matteomacri.yelpclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainActivity"
private const val BASE_URL = "https://api.yelo.com/v3/"
private const val API_KEY = "6KZu1yL9CNnm4M0VzAUaP69WAMgoDKqpTIWzMJfDCNYXYU1s87HTmdm-ml1WZsuW2IGXZAFL5JDAi6t_BoQ1aomHtKOl_tyObwDk1Vz-aIlyo1V8BDXdKBjRVYGKYnYx"

class MainActivity : AppCompatActivity() {
    lateinit var rvRestaurants: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvRestaurants = findViewById(R.id.rvRestaurants)

        val restaurants = mutableListOf<YelpRestaurants>()
        val adapter = RestaurantsAdapter(this, restaurants)
        rvRestaurants.adapter = adapter
        rvRestaurants.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val yelpService = retrofit.create(YelpService::class.java)
        // searchRestaurants() is asynchronous method so we can not block the UI Thread:
        // therefore we call enqueue method
        yelpService.searchRestaurants("Bearer $API_KEY","Avocado Toast", "New York")
            .enqueue(object : Callback<YelpSearchResult> {
                override fun onResponse(call: Call<YelpSearchResult>, response: Response<YelpSearchResult>) {
                    Log.i(TAG, "onResponse $response")
                    val body = response.body()
                    if(body==null){
                        Log.w(TAG, "Did not receive valid response")
                    }
                    restaurants.addAll(body!!.restaurants)
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                    Log.i(TAG, "onFailure $t")
                }
            })

    }


}