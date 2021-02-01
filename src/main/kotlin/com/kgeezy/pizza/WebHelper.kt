package com.kgeezy.pizza

import com.google.gson.Gson
import com.google.gson.JsonObject
import java.net.HttpURLConnection
import java.net.URL

class WebHelper {
    fun requestOrder(callback: (response: String) -> Unit) {
        val url = URL("https://kavbc79w44.execute-api.us-east-2.amazonaws.com/Prod/test-order/") //<- this wont work

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"
            println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")
            var string = ""
            inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                    string += line
                }
            }

            callback(
                Gson()
                    .fromJson(string, JsonObject::class.java)
                    .get("body")
                    .toString()
                    .replace("\\", "")
                    .replace("\"", "")
            )

        }
    }
}