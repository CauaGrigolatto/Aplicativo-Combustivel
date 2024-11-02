package br.edu.ifsp.dmo1.flexfuelhelper

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var gasolineEditText: EditText
    private lateinit var ethanolEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findById()
        setClick()
    }

    override fun onClick(v: View?) {
        if (v == calculateButton) {
            calculate()
        }
    }

    private fun calculate() {
        val ethanolStr = ethanolEditText.text.toString()
        val gasolineStr = gasolineEditText.text.toString()

        if (ethanolStr.isBlank() || gasolineStr.isBlank()) {
            Toast.makeText(this, getString(R.string.input_fuel_message), Toast.LENGTH_SHORT).show()
            resultTextView.text = ""
        }
        else {
            val gasoline = getValueConverted(gasolineStr)
            val ethanol = getValueConverted(ethanolStr)
            val result = ethanol / gasoline

            if (result < 0.7) {
                resultTextView.text = getString(R.string.answer_ethanol)
                resultTextView.setTextColor(resources.getColor(R.color.ethanol, this.theme))
            }
            else {
                resultTextView.text = getString(R.string.answer_gas)
                resultTextView.setTextColor(resources.getColor(R.color.gasoline, this.theme))
            }
        }
    }

    private fun getValueConverted(input: String): Double {
        try {
            return input.toDouble()
        }
        catch(e: NumberFormatException) {
            Toast.makeText(this, getString(R.string.invalid_input_message), Toast.LENGTH_SHORT).show()
            return 0.0
        }
    }

    private fun setClick() {
        calculateButton.setOnClickListener(this)
    }

    private fun findById() {
        gasolineEditText = findViewById(R.id.edit_text_gasoline)
        ethanolEditText = findViewById(R.id.edit_text_ethanol)
        calculateButton = findViewById(R.id.calculate_btn)
        resultTextView = findViewById(R.id.text_view_response)
    }
}