package com.example.serie5exo8

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.converter.moshi.MoshiConverterFactory
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val text = findViewById<TextView>(R.id.text_view_result )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url = "http://mobile-courses-server.herokuapp.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(JsonPlaceHolderApi::class.java)
        val courseRequest = service.getPosts()

        courseRequest.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val allCourse = response.body()
                if (allCourse != null) {
                   text.text="HERE is ALL COURSES FROM HEROKU SERVER:"

                    for (c in allCourse)
                        text.text=" one course : ${c.id}: ${c.title}"

                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                error("KO")
            }
        })
}







}






