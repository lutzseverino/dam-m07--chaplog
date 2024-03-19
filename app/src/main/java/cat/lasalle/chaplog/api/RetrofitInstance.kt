package cat.lasalle.chaplog.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://my-json-server.typicode.com/yourusername/yourrepo/"

    val bookLogService: BookLogService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookLogService::class.java)
    }
}