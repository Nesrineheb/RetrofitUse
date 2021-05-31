package com.example.serie5exo8

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textViewResult = findViewById<TextView>(R.id.text_view_result)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val jsonPlaceHolderApi: JsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val call:Call<MutableList<Post?>?>? = jsonPlaceHolderApi.getPosts()
        call.enqueue(object : Callback<List<Post?>?> {
            override fun onResponse(call: Call<List<Post?>?>?, response: Response<List<Post?>?>) {
                if (!response.isSuccessful) {

                    textViewResult.setText("Code: " + response.code())
                    return
                }
                val posts: List<Post> = response.body() as List<Post>
                for (post in posts) {
                    var content = ""
                    content += """
                        ID: ${post.getId().toString()}
                        
                        """.trimIndent()
                    content += """
                        User ID: ${post.getUserId().toString()}
                        
                        """.trimIndent()
                    content += """
                        Title: ${post.getTitle().toString()}
                        
                        """.trimIndent()
                    content += """
                        Text: ${post.getText().toString()}
                        
                        
                        """.trimIndent()
                    textViewResult.append(content)
                }
            }

            override fun onFailure(call: Call<List<Post?>?>?, t: Throwable) {
                textViewResult.setText(t.message)
            }
        })
    }


}







}






