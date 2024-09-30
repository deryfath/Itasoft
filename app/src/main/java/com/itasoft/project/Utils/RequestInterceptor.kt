package com.itasoft.project.Utils

import com.itasofttest.project.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()

        //get the token
        try {
            newRequest.addHeader("Accept", "application/json")
            newRequest.addHeader("API_KEY", BuildConfig.API_KEY)
            newRequest.addHeader("API_ID", BuildConfig.API_ID)


        } catch (ex: Throwable) {
            print(ex)
        }

        return chain.proceed(newRequest.build())
    }
}