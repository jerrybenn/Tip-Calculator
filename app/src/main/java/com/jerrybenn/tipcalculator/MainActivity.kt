package com.jerrybenn.tipcalculator

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var editTextNumber: EditText
    lateinit var tipPercentTextC: TextView
    lateinit var tipAmountTextC: TextView
    lateinit var totalTextC: TextView
    lateinit var seekBar: SeekBar

    lateinit var tipPercentTextA: TextView
    lateinit var tipAmountTextA: TextView
    lateinit var totalTextA: TextView

    lateinit var tipPercentTextB: TextView
    lateinit var tipAmountTextB: TextView
    lateinit var totalTextB: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber = findViewById(R.id.editTextNumber)
        tipPercentTextC = findViewById(R.id.tipPercentTextC)
        tipAmountTextC = findViewById(R.id.tipAmountTextC)
        totalTextC = findViewById(R.id.totalTextC)
        seekBar = findViewById(R.id.seekBar)
        tipPercentTextA = findViewById(R.id.tipPercentTextA)
        tipAmountTextA = findViewById(R.id.tipAmountTextA)
        totalTextA = findViewById(R.id.totalTextA)
        tipPercentTextB = findViewById(R.id.tipPercentTextB)
        tipAmountTextB = findViewById(R.id.tipAmountTextB)
        totalTextB = findViewById(R.id.totalTextB)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update the tip percent display
                tipPercentTextC.text = "$progress%"
                update()  // Recalculate the tip and total when seek bar changes
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        editTextNumber.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                update()
                Log.i("Jerry", "ENTER pressed")
                return@OnKeyListener true
            }
        false
        })
        update()
    }


    fun update() {
        val amountString = editTextNumber.text.toString()
        if (amountString.isEmpty()) return  // Exit if no amount is entered

        val amount = amountString.toDouble()
        val tipPercent = seekBar.progress.toDouble() / 100.0

        val tipAmountC = amount * tipPercent
        tipAmountTextC.text = String.format("%.2f", tipAmountC)
        val totalAmountC = amount + tipAmountC
        totalTextC.text = String.format("%.2f", totalAmountC)

        val tipPercentA = 0.15
        val tipAmountA = amount * tipPercentA
        tipAmountTextA.text = String.format("%.2f", tipAmountA)
        val totalAmountA = amount + tipAmountA
        totalTextA.text = String.format("%.2f", totalAmountA)


        val tipPercentB = 0.20
        val tipAmountB = amount * tipPercentB
        tipAmountTextB.text = String.format("%.2f", tipAmountB)
        val totalAmountB = amount + tipAmountB
        totalTextB.text = String.format("%.2f", totalAmountB)
    }


}