package jr.brian.rxjavaretrofit.model.data.spacex

data class SecondStage(
    val block: Int,
    val payloads: List<Payload>
)