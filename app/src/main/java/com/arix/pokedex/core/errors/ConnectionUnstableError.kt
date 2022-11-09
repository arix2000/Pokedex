package com.arix.pokedex.core.errors

import androidx.annotation.StringRes
import com.arix.pokedex.R

class ConnectionUnstableError(
    message: String?,
    @StringRes val stringId: Int = R.string.connection_unstable_error_message
) : Error(message)

