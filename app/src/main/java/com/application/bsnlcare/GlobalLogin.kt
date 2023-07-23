package com.application.bsnlcare

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.application.bsnlcare.customer.CustomerLogin
import com.application.bsnlcare.admin.AdminLogin


class GlobalLogin : AppCompatActivity() {

    private lateinit var imagePager: ViewPager2
    private lateinit var imageList: List<Int>
    lateinit var btnExit: Button
    lateinit var shareRepo: Button
    lateinit var shareAppTxt: Button
    lateinit var customerLoginBtn: CardView
    lateinit var adminLoginBtn: CardView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_login)

        // customer login
        customerLoginBtn = findViewById(R.id.customer_login_button)
        customerLoginBtn.setOnClickListener(){
            val intent = Intent(this, CustomerLogin::class.java)
            startActivity(intent)
        }

        // admin login
        adminLoginBtn = findViewById(R.id.admin_login_button)
        adminLoginBtn.setOnClickListener(){
            val intent = Intent(this, AdminLogin::class.java)
            startActivity(intent)
        }


        // view Pager in card view
        imagePager = findViewById(R.id.imagePager)

        imageList = listOf(
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3,
            R.drawable.banner4
        )

        val adapter = ImagePagerAdapter(imageList)
        imagePager.adapter = adapter

        startImageSliderTimer()
    }

    private fun startImageSliderTimer() {
        val handler = Handler()
        val updateImageSliderTask = object : Runnable {
            override fun run() {
                val currentPage = imagePager.currentItem
                var nextPage = currentPage + 1
                if (nextPage >= imageList.size) {
                    nextPage = 0
                }
                imagePager.currentItem = nextPage
                handler.postDelayed(this, 4000)
            }
        }
        handler.postDelayed(updateImageSliderTask, 4000)
    }


    class ImagePagerAdapter(private val imageList: List<Int>) :
        RecyclerView.Adapter<ImagePagerAdapter.ImagePagerViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePagerViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image_pager, parent, false)
            return ImagePagerViewHolder(view)
        }

        override fun onBindViewHolder(holder: ImagePagerViewHolder, position: Int) {
            val imageRes = imageList[position]
            holder.bind(imageRes)
        }

        override fun getItemCount(): Int {
            return imageList.size
        }

        inner class ImagePagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.imageView)

            fun bind(imageRes: Int) {
                Glide.with(itemView.context)
                    .load(imageRes)
                    .into(imageView)
            }
        }
    }

}

