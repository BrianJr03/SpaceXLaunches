package jr.brian.rxjavaretrofit.model.data.spacex

data class LaunchFailureDetails(
    val altitude: Int,
    val reason: String,
    val time: Int
)