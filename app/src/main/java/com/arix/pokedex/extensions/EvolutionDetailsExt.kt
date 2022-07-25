package com.arix.pokedex.extensions

import androidx.compose.runtime.Composable
import com.arix.pokedex.features.poke_list.domain.model.details.TypeX
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.EvolutionDetail
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.Item

@Composable
fun List<EvolutionDetail>.ScanEvolutionDetails(
    evolveByLevel: @Composable (Int) -> Unit,
    evolveByItem: @Composable (Item) -> Unit,
    evolveByHoldingItem: @Composable (Item) -> Unit,
    evolveByHappiness: @Composable (Int) -> Unit,
    evolveByTimeOfDay: @Composable (String) -> Unit,
    evolveByTrade: @Composable () -> Unit,
    evolveByKnownMove: @Composable (String) -> Unit,
    evolveByKnownMoveType: @Composable (TypeX) -> Unit
) {
    val isAlreadyDoneList = mutableListOf<Boolean>()
    repeat(8) {
        isAlreadyDoneList.add(false)
    }
    val reorderedList = getReorderedEvolutionDetail(this)
    reorderedList.forEach {
        if (it.min_level != null && !isAlreadyDoneList[0]) {
            evolveByLevel(it.min_level)
            isAlreadyDoneList[0] = true
        }
        if (it.item != null && !isAlreadyDoneList[1]) {
            evolveByItem(it.item)
            isAlreadyDoneList[1] = true
        }
        if (it.min_happiness != null && !isAlreadyDoneList[2]) {
            evolveByHappiness(it.min_happiness)
            isAlreadyDoneList[2] = true
        }
        if (!it.time_of_day.isNullOrBlank() && !isAlreadyDoneList[3]) {
            evolveByTimeOfDay(it.time_of_day)
            isAlreadyDoneList[3] = true
        }
        if (it.trigger.name == "trade" && !isAlreadyDoneList[4]) {
            evolveByTrade()
            isAlreadyDoneList[4] = true
        }
        if (it.known_move != null && !isAlreadyDoneList[5]) {
            evolveByKnownMove(it.known_move.name)
            isAlreadyDoneList[5] = true
        }
        if (it.known_move_type != null && !isAlreadyDoneList[6]) {
            evolveByKnownMoveType(it.known_move_type)
            isAlreadyDoneList[6] = true
        }
        if (it.held_item != null && !isAlreadyDoneList[7]) {
            evolveByHoldingItem(it.held_item)
            isAlreadyDoneList[7] = true
        }
    }
}

private fun getReorderedEvolutionDetail(evolutionDetails: List<EvolutionDetail>): List<EvolutionDetail> {
    mutableListOf<EvolutionDetail>().run {
        //TODO fix/test order
        addAll(evolutionDetails.filter { it.min_level != null })
        addAll(evolutionDetails.filter { it.min_happiness != null })
        addAll(evolutionDetails.filter { it.known_move_type != null })
        addAll(evolutionDetails.filter { it.known_move != null })
        addAll(evolutionDetails.filter { it.item != null })
        addAll(evolutionDetails.filter { it.held_item != null })
        addAll(evolutionDetails.filter { it.time_of_day != null })
        addAll(evolutionDetails.filter { it.trigger.name == "trade" })
        return this.asReversed()
    }
}