package com.example.assignmentweek3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.detail)

        loadData()
    }

    fun loadData() {
        val title = intent.getStringExtra("title")
        val published = intent.getStringExtra("published")
        val imageDetail = intent.getStringExtra("img")
        val description = intent.getStringExtra("desc")
        val author = intent.getStringExtra("author")

        tvTitleDetail.text = title
        tvPublishedAt.text = published
        tvDescDetail.text = description
        tvAuthorDetail.text = author

        Glide.with(this)
            .load(imageDetail)
            .into(imgNewsDetail)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }
}