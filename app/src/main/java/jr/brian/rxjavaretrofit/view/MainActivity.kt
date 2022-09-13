package jr.brian.rxjavaretrofit.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import jr.brian.rxjavaretrofit.R
import jr.brian.rxjavaretrofit.databinding.ActivityMainBinding
import jr.brian.rxjavaretrofit.model.data.remote.ApiService
import jr.brian.rxjavaretrofit.model.data.Repo
import jr.brian.rxjavaretrofit.model.data.spacex.SpaceXLaunches
import jr.brian.rxjavaretrofit.viewmoodel.ViewModel
import jr.brian.rxjavaretrofit.viewmoodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    private lateinit var spaceXAdapter: SpaceXAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        AppCenter.start(
            // TODO - Store appSecret outside of code
            application,
            "a14e81ac-1855-4aec-bb3c-41d4228e6c2b",
            Analytics::class.java,
            Crashes::class.java
        )
        fetchLatestNews()
        binding.fetchBtn.setOnClickListener {
            fetchLatestNews()
        }
    }

    private fun fetchLatestNews() {
        viewModelFactory = ViewModelFactory(Repo(ApiService.instance()))
        viewModel = ViewModelProvider(this, viewModelFactory)[ViewModel::class.java].apply {
            getV3Launches()
            spaceXLiveData.observe(this@MainActivity) {
                Log.i("TAG", it.toString())
                setAdapter(it)
                binding.fetchBtn.text = getString(R.string.refresh)
            }
        }
    }

    private fun setAdapter(launches: SpaceXLaunches) {
        spaceXAdapter = SpaceXAdapter(this, launches)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL, false
            )
            recyclerView.adapter = spaceXAdapter
        }
    }
}