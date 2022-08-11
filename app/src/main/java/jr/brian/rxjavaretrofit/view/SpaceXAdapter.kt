package jr.brian.rxjavaretrofit.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jr.brian.rxjavaretrofit.R
import jr.brian.rxjavaretrofit.databinding.SpaceXItemBinding
import jr.brian.rxjavaretrofit.model.data.spacex.SpaceXItem

class SpaceXAdapter(private val context: Context, private val launches: ArrayList<SpaceXItem>) :
    RecyclerView.Adapter<SpaceXAdapter.SpaceXViewHolder>() {

    lateinit var binding: SpaceXItemBinding

    override fun getItemCount() = launches.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpaceXViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = SpaceXItemBinding.inflate(layoutInflater, parent, false)
        return SpaceXViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SpaceXViewHolder, position: Int) {
        holder.apply {
            val launch = launches[position]
            bind(launch)
        }
    }

    inner class SpaceXViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(launch: SpaceXItem) {
            val missionName = v.findViewById<TextView>(R.id.missionName)
            val rocketName = v.findViewById<TextView>(R.id.rocketName)
            val launchSiteName = v.findViewById<TextView>(R.id.launchSiteName)
            val dateOfLaunch = v.findViewById<TextView>(R.id.launchDate)
            val img = v.findViewById<AppCompatImageView>(R.id.launchPatchImage)
            missionName.text = launch.mission_name
            rocketName.text = launch.rocket.rocket_name
            launchSiteName.text = launch.launch_site.site_name
            dateOfLaunch.text = launch.launch_date_utc
            missionName.text = launch.mission_name
            Glide.with(context)
                .load(launch.links.mission_patch)
                .placeholder(R.drawable.ic_baseline_info_24)
                .error(R.drawable.ic_baseline_info_24)
                .fallback(R.drawable.ic_baseline_info_24)
                .into(img)
        }
    }
}