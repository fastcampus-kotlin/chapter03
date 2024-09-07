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
    var inputNumber: Int = 0
    var cmToM = true

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

        inputEditText.addTextChangedListener { text ->
            inputNumber = if(text.isNullOrEmpty())0 else text.toString().toInt()
            // cm미터를 m로 변환 시켜보기
            changeUnit(cmToM, 0)
        }

        swapImageButton.setOnClickListener {
            cmToM = cmToM.not()
            changeUnit(cmToM, 1)
        }
    }

    private fun changeUnit(cmToM: Boolean, flag: Int){
        if(flag == 1) {
            binding.inputUnitTextView.text = if (cmToM) "cm" else "m"
            binding.outputUnitTextView.text = if (cmToM) "m" else "cm"
        }

        if(cmToM){
            binding.outputTextView.text = inputNumber.times(0.01).toString()
        } else {
            binding.outputTextView.text = inputNumber.times(100).toString()
        }
    }

    
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.inputUnitTextView.text = if(cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if(cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}