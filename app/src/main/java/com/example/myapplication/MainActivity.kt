package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val text=findViewById<TextView>(R.id.textView2)
        val client= OkHttpClient()
        val req= Request.Builder()
            .url("http://127.0.0.1:5000/flower")
            .get()
            .build()
        thread {
            try {
                val response = client.newCall(req).execute()
                val jsonString = response.body?.string() ?: ""

                val json = JSONObject(jsonString)
                val data = json.getJSONArray("data")

                val flowers = mutableListOf<String>()
                for (i in 0 until data.length()) {
                    flowers.add(data.getString(i))
                }

                // cập nhật giao diện
                runOnUiThread {
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, flowers)
                    listView.adapter = adapter
                }

            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("FlowerApp", "Lỗi: ${e.message}")
            }
        }
    }
}