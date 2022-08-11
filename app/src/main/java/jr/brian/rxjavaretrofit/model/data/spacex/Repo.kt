package jr.brian.rxjavaretrofit.model.data.spacex

import jr.brian.rxjavaretrofit.model.data.remote.ApiService

class Repo(private val apiService: ApiService) {
    fun getRemoteData() = apiService.getRandomSingleAnimal()

    fun getV3Launches() = apiService.getV3Launches()
}