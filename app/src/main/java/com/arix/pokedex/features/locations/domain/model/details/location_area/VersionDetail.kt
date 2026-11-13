import com.arix.pokedex.features.locations.domain.model.details.location_details.NamedApiResource
import com.google.gson.annotations.SerializedName

data class VersionDetail(
    val version: NamedApiResource,
    @SerializedName("max_chance") val maxChance: Int,
    @SerializedName("encounter_details") val encounterDetails: List<EncounterDetail>
)