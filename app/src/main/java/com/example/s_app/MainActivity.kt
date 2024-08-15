package com.example.s_app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.sqrt

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
        val a = fetchAValue()
        val b = fetchBValue()
        val c = complexFinancialComputation(a, b)
        val d = financialOperation(c)
        val e = finalFinancialComputation(d)

        val result = complexDivision(e)
        Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show()


    }

    private fun fetchAValue(): Double {
        return 10.0
    }

    private fun fetchBValue(): Double {
        return 5.0
    }

    private fun complexFinancialComputation(a: Double, b: Double): Double {
        return sqrt(a * a - 4 * b)
    }

    private fun financialOperation(c: Double): Int {
        return (c / 2 - 7).toInt()
    }

    private fun finalFinancialComputation(d: Int): Int {
        return 100 / ((d + 1) - (d + 1))
    }

    private fun complexDivision(e: Int): Int {
        return 50 / e
    }
}
