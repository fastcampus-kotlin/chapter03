package com.example.chapter3

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.chapter3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // lateinit 지연 초기화
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val outputTextView = binding.outputTextView
        val outputUnitTextView = binding.outputUnitTextView
        val inputEditText = binding.inputEditText
        val inputUnitTextView = binding.inputUnitTextView
        val swapImageButton = binding.swapImagebutton

        var inputNumber: Int = 0
        var cmToM = true

        inputEditText.addTextChangedListener { text ->
            inputNumber = if(text.isNullOrEmpty()){
                0
            } else {
                text.toString().toInt()
            }
            // cm미터를 m로 변환 시켜보기
            if(cmToM){
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                outputTextView.text = inputNumber.times(100).toString()
            }
        }

        swapImageButton.setOnClickListener {
            cmToM = cmToM.not()
            if(cmToM){
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.times(100).toString()
            }
        }
    }
}