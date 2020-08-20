package com.example.assignmentweek3

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.assignmentweek3.adapter.NewsAdapter
import com.example.assignmentweek3.model.News
import com.example.assignmentweek3.model.ResponseServer
import com.example.assignmentweek3.network.ApiConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = resources.getString(R.string.title)

        getNewsList()
    }

    private fun getNewsList() {

        if (isConnect()) {
            ApiConfig.getRetrofit().getNews().enqueue(object : Callback<ResponseServer>{

                override fun onFailure(call: Call<ResponseServer>, t: Throwable) {
                    Toast.makeText(this@MainActivity,  "Gagal load data", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                }

                override fun onResponse(call: Call<ResponseServer>, response: Response<ResponseServer>) {
                    if (response.isSuccessful) {
                        progressBar.visibility = View.GONE
                        val status = response.body()?.status

                        if (status == "ok") {
                            val article = response.body()?.articles
                            showData(article)
                        }
                    }
                }

            })
        } else {
            progressBar.visibility = View.GONE
            Toast.makeText(this, "Device harus menggunakan internet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showData(data : ArrayList<News>?) {
        recycler_view.adapter = NewsAdapter(data)
    }

    private fun isConnect() : Boolean {
        val connect : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val valid = connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
        return valid
    }
}