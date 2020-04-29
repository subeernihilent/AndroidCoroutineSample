package com.example.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val RESULT_1 = "Result #1"
    private val RESULT_2 = "Result #2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{

            //IO, Main, Defaul
            CoroutineScope(IO).launch { fakeApiRequest() }
        }
    }

    private suspend fun fakeApiRequest(){
        val result1 = getResult1FormApi()
        println{"subeer: $result1"}
        setTextOnMainThread(result1)

        val result2 = getResult2FormApi()
        setTextOnMainThread(result2)
    }

    private suspend fun setTextOnMainThread(input: String){
        withContext(Main) {
            setNewText(input)
        }
    }

    private fun setNewText(input: String){
        text.text = text.text.toString() + "\n${input}"
    }

    private suspend fun getResult1FormApi(): String{
        logThread("getResult1FormApi")
        delay(1000) // it will delay the single coroutine, not the entire thread.
        return RESULT_1
    }

    private suspend fun getResult2FormApi(): String{
        logThread("getResult1FormApi")
        delay(1000) // it will delay the single coroutine, not the entire thread.
        return RESULT_2
    }


    private fun logThread(methodName : String){
        println("subeer : ${methodName}: ${Thread.currentThread().name}")
    }

}
