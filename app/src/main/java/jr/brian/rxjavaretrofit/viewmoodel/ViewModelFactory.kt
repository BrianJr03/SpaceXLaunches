package jr.brian.rxjavaretrofit.viewmoodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jr.brian.rxjavaretrofit.model.data.spacex.Repo
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: Repo) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ViewModel::class.java)) {
            ViewModel(repository) as T
        } else {
            throw IllegalArgumentException("No View Model was found")
        }
    }
}