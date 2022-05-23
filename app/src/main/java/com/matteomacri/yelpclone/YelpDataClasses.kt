package com.matteomacri.yelpclone

import com.google.gson.annotations.SerializedName

data class YelpSearchResult(
    @SerializedName("total") val total: Int,
    @SerializedName("businesses") val restaurants: List<YelpRestaurants>
)

// If the name of SerializedName is exactly the same like json object's name
// you can don't write @SerializedName
data class YelpRestaurants(
    val name: String,
    val rating: Double,
    val price: String,
    @SerializedName("review_count") val numReviews: Int,
    @SerializedName("distance") val distanceInMeters: Double,
    @SerializedName("image_url") val imageUrl: String,
    val categories: List<YelpCatagory>,
    val location: YelpLocation
){
    // method converter distance in meters
    fun displayDistance(): String{
        val milesPerMeters = 0.000621371
        val distanceInMiles = "%.2f".format(distanceInMeters * milesPerMeters)
        return "$distanceInMiles mi"
    }

}

data class YelpCatagory(
    val title: String
)

data class YelpLocation(
    @SerializedName("address1") val address: String
)