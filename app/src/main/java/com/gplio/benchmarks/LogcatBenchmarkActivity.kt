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

class LogcatBenchmarkActivity : AppCompatActivity() {
    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    external fun bubbleSortInt(ns: IntArray): IntArray

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logcat_benchmark)
        setSupportActionBar(toolbar)


        startButton.setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG) .setAction("Action", null).show()
            Log.d(TAG, "STARTING!!!")

            val random = Random()
            val original = IntArray(9000) { random.nextInt(100) }

            val defaultSorted = original.copyOf()
            sort(defaultSorted)

            numberTextView.text = original.joinToString()
            sortedTextView.text = defaultSorted.joinToString()

            textViewLogs.text = "_"
            textViewNoLogs.text = "_"
            nativeTextView.text = "_"

            val handler = Handler(Looper.getMainLooper())
            Thread {
                var sum = 0
                val numbers = original.copyOf()
                val millis = measureTimeMillis {
                    val res = bubbleSortWithLogs(numbers)
                    sum = res.sum()
                }
                Log.d(TAG, "WITH_LOGS SUM: $sum")
                handler.post {
                    textViewLogs.text = "WITH_LOGS $millis MS"
                }
            }.start()

            Thread {
                var sum = 0
                val numbers = original.copyOf()
                val millis = measureTimeMillis {
                    val res = bubbleSortWithoutLogs(numbers)
                    sum = res.sum()
                }
                Log.d(TAG, "WITHOUT_LOGS SUM: $sum")
                handler.post {
                    textViewNoLogs.text = "WITHOUT_LOGS $millis MS"
                }
            }.start()

            Thread {
                val numbers = original.copyOf()
                var sum = 0
                val millis = measureTimeMillis {
                    val res = bubbleSortInt(numbers)
                    // Log.d(TAG, "NATIVE RESULT: ${res.joinToString()}")
                    sum = res.sum()
                }
                Log.d(TAG, "NATIVE SUM: $sum")
                handler.post {
                    nativeTextView.text = "NATIVE $millis MS "
                }
            }.start()
        }
    }

    private fun bubbleSortWithLogs(numbers: IntArray): IntArray {
        val tag = "bubbleSortWithLogs"
        Log.d(tag, "BEGIN: bubbleSort: ${numbers.joinToString()}")

        while (true) {
            var swapped = false
            for (i in 0 until numbers.size - 1) {
                if (numbers[i] > numbers[i + 1]) {
                    Log.d(tag, "START: bubbleSort: ${numbers[i]} ${numbers[i + 1]}")

                    val t = numbers[i + 1]
                    numbers[i + 1] = numbers[i]
                    numbers[i] = t
                    swapped = true

                    Log.d(tag, "END: bubbleSort: ${numbers[i]} ${numbers[i + 1]}")
                } else {
                    Log.d(tag, "END: bubbleSort: Continuing")
                }
            }
            Log.d(tag, "bubbleSort: $swapped")
            if (!swapped) {
                break
            }
        }
        return numbers
    }


    private fun bubbleSortWithoutLogs(numbers: IntArray): IntArray {
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
