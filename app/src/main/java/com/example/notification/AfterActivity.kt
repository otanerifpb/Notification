package com.example.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AfterActivity : AppCompatActivity() {
    private lateinit var tvTextoAfter: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after)

        // Sincronizar Back-End com Front-End
        this.tvTextoAfter = findViewById(R.id.txTextoAfter)
    }
}