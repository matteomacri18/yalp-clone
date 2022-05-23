package com.matteomacri.yelpclone

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

// Interface that defines each endpoint
interface YelpService {

    // for this endpoint call this method which will pass term and location
    // as Query: Query(nameInTheAPI) ourName: type
    // and it will return a Call<YelpSearchResult>
    @GET("businesses/search")
    fun searchRestaurants(
        @Header("Authorization") autHeader: String,
        @Query("term") searchTerm: String,
        @Query("location") location: String
    ): Call<YelpSearchResult>


}