package com.example.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randomNumber = Random.nextInt(0,100)

        val etNumber = findViewById<EditText>(R.id.etNumber)
        val btnCheck = findViewById<Button>(R.id.btnCheck)
        val tvFinalMessage = findViewById<TextView>(R.id.tvFinalMessage)

        fun numFound() = "Congratulations, you found the number ${randomNumber}".also { tvFinalMessage.text = it }

        fun smallerNum() = "Try a smaller one".also { tvFinalMessage.text = it }

        fun biggerNum() = "Try a bigger one".also { tvFinalMessage.text = it }

        btnCheck.setOnClickListener {
            val number = etNumber.text.toString().toInt()

            when {
                number == randomNumber -> numFound()
                number > randomNumber -> smallerNum()
                number < randomNumber -> biggerNum()
            }
        }
    }
}