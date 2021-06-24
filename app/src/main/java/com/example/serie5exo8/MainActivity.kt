package com.example.serie5exo8


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


import retrofit2.converter.moshi.MoshiConverterFactory
class MainActivity : AppCompatActivity() {
    /*  override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
     }*/
    var textViewResult: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  textViewResult = findViewById<TextView>(R.id.text_view_result)
        val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/todos")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val jsonPlaceHolderApi = retrofit.create(
                JsonPlaceHolderApi::class.java
        )
        val call = jsonPlaceHolderApi.posts
        call!!.enqueue(object : Callback<List<Post?>?> {
            override fun onResponse(call: Call<List<Post?>?>, response: Response<List<Post?>?>) {
                if (!response.isSuccessful) {
                    textViewResult.setText("Code: " + response.code())
                    return
                }
                val posts = response.body()!!
                for (post in posts) {
                    var content = ""
                    content += """
                      ID: ${post!!.id}
 
                      """.trimIndent()
                    content += """
                      User ID: ${post.userId}
 
                      """.trimIndent()
                    content += """
                      Title: ${post.title}
 
                      """.trimIndent()
                    content += """
                      Text: ${post.text}
 
 
                      """.trimIndent()
                    textViewResult.append(content)
                }
            }

            override fun onFailure(call: Call<List<Post?>?>, t: Throwable) {
                textViewResult.setText(t.message)
            }
        })
    }




}






