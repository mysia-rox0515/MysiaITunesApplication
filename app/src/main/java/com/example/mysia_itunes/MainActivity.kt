package com.example.mysia_itunes

import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mysia_itunes.databinding.ActivityMainBinding
import com.example.mysia_itunes.view.ClassicFragment
import com.example.mysia_itunes.view.PopFragment
import com.example.mysia_itunes.view.RockFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var textView: TextView
    var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefreshLayout = findViewById(R.id.swipe)
        swipeRefreshLayout.setOnRefreshListener {
            number++
            textView.text = " Total number = $number"
            Handler().postDelayed(Runnable {
                swipeRefreshLayout.isRefreshing = false
            }, 4000)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, RockFragment())
            .commit()
        binding.rockLinear.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, RockFragment())
                .commit()
        }
        binding.classicLinear.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, ClassicFragment())
                .commit()
        }
        binding.popLinear.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, PopFragment())
                .commit()
        }
    }
    fun onRefresh(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,RockFragment())
            .commit()
        binding.swipe.setRefreshing(false)
    }

}
