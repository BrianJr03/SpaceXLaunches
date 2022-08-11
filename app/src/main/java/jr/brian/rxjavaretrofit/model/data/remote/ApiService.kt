package jr.brian.rxjavaretrofit.model.data.remote

import io.reactivex.*
import jr.brian.rxjavaretrofit.model.data.spacex.SpaceXLaunches
import retrofit2.http.GET

interface ApiService {
    @GET("v3/launches")
    fun getV3Launches(): Single<SpaceXLaunches>

    companion object {
        fun instance(): ApiService =
            RetrofitClient.retrofit.create(ApiService::class.java)
    }
}