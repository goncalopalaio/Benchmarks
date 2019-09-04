package com.gplio.benchmarks

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_if_else.*

class IfElseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_if_else)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    companion object {
        /*
            // Generated with Android Studio's 'Kotlin Bytecode' feature
            // access flags 0x12
            private final ifReturn(Z)Ljava/lang/String;
            // annotable parameter count: 1 (visible)
            // annotable parameter count: 1 (invisible)
            L0
            LINENUMBER 27 L0
            ILOAD 1
            IFEQ L1
            L2
            LINENUMBER 28 L2
            LDC "TRUE"
            ARETURN
            L1
            LINENUMBER 30 L1
            LDC "FALSE"
            ARETURN
            L3
            LOCALVARIABLE this Lcom/gplio/benchmarks/IfElseActivity$Companion; L0 L3 0
            LOCALVARIABLE value Z L0 L3 1
            MAXSTACK = 1
            MAXLOCALS = 2
         */
        private fun ifReturn(value: Boolean): String {
            if (value) {
                return "TRUE"
            }
            return "FALSE"
        }

        /*
            // Generated with Android Studio's 'Kotlin Bytecode' feature
            // access flags 0x12
            private final ifElse(Z)Ljava/lang/String;
            // annotable parameter count: 1 (visible)
            // annotable parameter count: 1 (invisible)
            L0
            LINENUMBER 54 L0
            ILOAD 1
            IFEQ L1
            L2
            LINENUMBER 55 L2
            LDC "TRUE"
            ARETURN
            L1
            LINENUMBER 57 L1
            LDC "FALSE"
            ARETURN
            L3
            L4
            LOCALVARIABLE this Lcom/gplio/benchmarks/IfElseActivity$Companion; L0 L4 0
            LOCALVARIABLE value Z L0 L4 1
            MAXSTACK = 1
            MAXLOCALS = 2
         */
        private fun ifElse(value: Boolean): String {
            if (value) {
                return "TRUE"
            } else {
                return "FALSE"
            }
        }

        /*
        // access flags 0x12
        // Generated with Android Studio's 'Kotlin Bytecode' feature
        private final ifElseLifted(Z)Ljava/lang/String;
        // annotable parameter count: 1 (visible)
        // annotable parameter count: 1 (invisible)
        L0
        LINENUMBER 86 L0
        ILOAD 1
        IFEQ L1
        L2
        LINENUMBER 87 L2
        LDC "TRUE"
        L3
        GOTO L4
        L1
        LINENUMBER 89 L1
        LDC "FALSE"
        L5
        LINENUMBER 86 L5
        L4
        ARETURN
        L6
        LOCALVARIABLE this Lcom/gplio/benchmarks/IfElseActivity$Companion; L0 L6 0
        LOCALVARIABLE value Z L0 L6 1
        MAXSTACK = 1
        MAXLOCALS = 2
         */
        private fun ifElseLifted(value: Boolean): String {
            return if (value) {
                "TRUE"
            } else {
                "FALSE"
            }
        }
    }
}
