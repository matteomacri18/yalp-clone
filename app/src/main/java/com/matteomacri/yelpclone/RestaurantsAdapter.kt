package com.matteomacri.yelpclone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class RestaurantsAdapter(val context: Context, val restaurants: List<YelpRestaurants>) :
    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.itemView.apply {
            val tvName: TextView = findViewById(R.id.tvName)
            val ratingBar: RatingBar = findViewById(R.id.ratingBar2)
            val tvNumReviews: TextView = findViewById(R.id.tvNumberViews)
            val tvAddress: TextView = findViewById(R.id.tvAddress)
            val tvCategory: TextView = findViewById(R.id.tvCategory)
            val tvDistance: TextView = findViewById(R.id.tvDistance)
            val tvPrice: TextView = findViewById(R.id.tvPrice)
            val imageView: ImageView = findViewById(R.id.imageView)

            tvName.text = restaurant.name
            ratingBar.rating = restaurant.rating.toFloat()
            tvNumReviews.text = "${restaurant.numReviews} Reviews"
            tvAddress.text = restaurant.location.address
            tvCategory.text = restaurant.categories[0].title
            tvDistance.text = restaurant.displayDistance()
            tvPrice.text = restaurant.price

            Glide.with(context).load(restaurant.imageUrl).into(imageView)
        }
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}
