package jr.brian.rxjavaretrofit.viewmoodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import jr.brian.rxjavaretrofit.model.data.spacex.Repo
import io.reactivex.android.schedulers.AndroidSchedulers
import jr.brian.rxjavaretrofit.model.data.spacex.SpaceXLaunches

class ViewModel(private val repo: Repo) : ViewModel() {
    val spaceXLiveData = MutableLiveData<SpaceXLaunches>()
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getV3Launches() {
        compositeDisposable.addAll(
            repo.getV3Launches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { spaceXLiveData.postValue(it) },
                    { Log.i("TAG", it.message ?: "error") })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}