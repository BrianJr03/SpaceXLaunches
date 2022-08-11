package jr.brian.rxjavaretrofit.model.data.spacex

data class Fairings(
    val recovered: Boolean,
    val recovery_attempt: Boolean,
    val reused: Boolean,
    val ship: String
)