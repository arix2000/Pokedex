import com.arix.pokedex.features.locations.domain.model.details.location_details.NamedApiResource
import com.google.gson.annotations.SerializedName

data class EncounterDetail(
    val method: NamedApiResource,
    val chance: Int,
    @SerializedName("min_level") val minLevel: Int,
    @SerializedName("max_level") val maxLevel: Int,
    @SerializedName("condition_values") val conditionValues: List<NamedApiResource>
)