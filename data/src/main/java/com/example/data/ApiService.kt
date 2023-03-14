package com.example.data

import com.example.data.data.remote.GetEmployeesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val baseUrl = "https://stoplight.io/mocks/kode-education/trainee-test/25143926/"
    }

    @GET("users")
    suspend fun getUsers(): Response<GetEmployeesResponse>
}