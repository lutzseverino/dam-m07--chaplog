package cat.lasalle.chaplog.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://my-json-server.typicode.com/lutzseverino/dam-m07--chaplog/"

    val bookLogService: BookLogService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookLogService::class.java)
    }
}