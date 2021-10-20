package com.example.simplepostrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var input: EditText
    lateinit var save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.et)
        save = findViewById(R.id.button)

        save.setOnClickListener {
            if(input.text.isNotEmpty()){
                input.text.clear()
                val apiInterface = APIClient.getClient()?.create(APIInterface::class.java)

                apiInterface!!.addName(input.text.toString()).enqueue(object : Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        Log.d("TAG", "$response")
                        Toast.makeText(this@MainActivity, "Saved", Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Try Again", Toast.LENGTH_LONG).show()
                    }

                })
            }else{
                Toast.makeText(this, "Enter a Name", Toast.LENGTH_LONG).show()
            }
        }
    }
}