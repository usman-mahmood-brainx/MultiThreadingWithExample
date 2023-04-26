package com.example.multithreadingwithexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import androidx.annotation.WorkerThread

class MainActivity : AppCompatActivity() {
    lateinit var btnStart: Button
    lateinit var btnStop: Button
    lateinit var mainhandler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mainhandler = Handler()
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnSotp)

        btnStart.setOnClickListener {
            // Method 1
//            val worker1 = WorkerThread1(10)
//            worker1.start()

            // Method 2
            val worker2 = WorkerThread2(10)
            Thread(worker2).start()
            // For running on same thread
            // worker2.run()

        }


    }

    // Method 1 by extending Thread Class
    class WorkerThread1(val seconds: Int) : Thread() {
        val i: Int = 1
        override fun run() {
            super.run()
            for (i in i..seconds) {

                println("StartThread: $i")
                Thread.sleep(1000)


            }
        }
    }

    // Method 2 by implementing Runnable
    inner class WorkerThread2(val seconds: Int) : Runnable {
        override fun run() {
            for (i in 1..seconds) {
                if (i == 5) {
                    /*  // Method 2
                    val threadHandler = Handler(Looper.getMainLooper())

                    threadHandler.post(Runnable {
                        btnStart.text = "50%"
                    })

                    // Method 3
                    btnStart.post(Runnable {
                        btnStart.text = "50%"
                    })
                     */
                    // Method 4
                    runOnUiThread(Runnable {
                        btnStart.text = "50%"
                    })
                    
                }
                println("StartThread: $i")
                Thread.sleep(1000)


            }
        }
    }


    // Method 2 by implementing Runnable
//    class WorkerThread2(val seconds:Int) : Runnable{
//        override fun run() {
//            for(i in 1..seconds ) {
//
//                println("StartThread: $i")
//                Thread.sleep(1000)
//
//
//            }
//        }
//    }
}