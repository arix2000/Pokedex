package com.arix.pokedex.utils

class MultipleEventsCutter {
    private var lastEventTimeMs: Long = 0

    fun processEvent(delay: Long, event: () -> Unit) {
        val now = System.currentTimeMillis()
        if (now - lastEventTimeMs >= delay) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}