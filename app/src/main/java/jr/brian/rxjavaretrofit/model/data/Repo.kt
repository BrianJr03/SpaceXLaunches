package jr.brian.rxjavaretrofit.model.data

import jr.brian.rxjavaretrofit.model.data.remote.ApiService

class Repo(private val apiService: ApiService) {
    fun getV3Launches() = apiService.getV3Launches()
}