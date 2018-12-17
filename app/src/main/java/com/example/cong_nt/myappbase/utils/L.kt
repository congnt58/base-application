package com.example.cong_nt.myappbase.utils

import android.util.Log

/**
 * Created by anhtu on 2/13/2017.
 */

object L {
    private var className: String? = null
    private var methodName: String? = null
    private var lineNumber: Int = 0

    private fun getMethodName(sElement: Array<StackTraceElement>) {
        className = sElement[1].fileName
        methodName = sElement[1].methodName
        lineNumber = sElement[1].lineNumber
    }

    private fun createLog(log: String): String {

        return " line " +
                lineNumber + " - [" +
                methodName +
                "] " +
                log
    }

    fun i(message: String) {
        getMethodName(Throwable().stackTrace)
        Log.i(className, createLog(message))
    }

    fun e(message: String) {
        getMethodName(Throwable().stackTrace)
        Log.e(className, createLog(message))
    }
}
