package com.gplio.benchmarks

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_logcat_benchmark.*
import kotlinx.android.synthetic.main.content_logcat_benchmark.*
import java.util.*
import java.util.Arrays.sort
import kotlin.system.measureTimeMillis

private const val TAG = "LogcatBenchmark"
private const val updates = 10000
class LogcatBenchmarkActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logcat_benchmark)
        setSupportActionBar(toolbar)


        startButton.setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG) .setAction("Action", null).show()

            val random = Random()
            val numbers = IntArray(1000) { random.nextInt(100) }

            val defaultSorted = numbers.copyOf()
            sort(defaultSorted)

            numberTextView.text = numbers.joinToString()
            sortedTextView.text = defaultSorted.joinToString()

            textViewLogs.text = "_"
            textViewNoLogs.text = "_"

            val handler = Handler(Looper.getMainLooper())
            Thread {
                val millis = measureTimeMillis {
                    bubbleSortWithLogs(numbers)
                }

                handler.post {
                    textViewLogs.text = "FINISHED_WITH_LOGS $millis"
                }
            }.start()

            Thread {
                val millis = measureTimeMillis {
                    bubbleSortWithoutLogs(numbers)
                }

                handler.post {
                    textViewNoLogs.text = "FINISHED_WITHOUT_LOGS $millis"
                }
            }.start()
        }
    }

    private fun bubbleSortWithLogs(ns: IntArray): IntArray {
        val numbers = ns.copyOf()

        Log.d(TAG, "BEGIN: bubbleSort: ${numbers.joinToString()}")

        while (true) {
            var swapped = false
            for (i in 0 until numbers.size - 1) {
                if (numbers[i] > numbers[i + 1]) {
                    Log.d(TAG, "START: bubbleSort: ${numbers[i]} ${numbers[i + 1]}")

                    val t = numbers[i + 1]
                    numbers[i + 1] = numbers[i]
                    numbers[i] = t
                    swapped = true

                    Log.d(TAG, "END: bubbleSort: ${numbers[i]} ${numbers[i + 1]}")
                } else {
                    Log.d(TAG, "END: bubbleSort: Continuing")
                }
            }
            Log.d(TAG, "bubbleSort: $swapped")
            if (!swapped) {
                break
            }
        }
        return numbers
    }


    private fun bubbleSortWithoutLogs(ns: IntArray): IntArray {
        val numbers = ns.copyOf()
        while (true) {
            var swapped = false
            for (i in 0 until numbers.size - 1) {
                if (numbers[i] > numbers[i + 1]) {
                    val t = numbers[i + 1]
                    numbers[i + 1] = numbers[i]
                    numbers[i] = t
                    swapped = true
                }
            }
            if (!swapped) {
                break
            }
        }
        return numbers
    }


}
