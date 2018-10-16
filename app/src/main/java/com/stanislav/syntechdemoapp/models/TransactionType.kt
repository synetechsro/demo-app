package com.stanislav.syntechdemoapp.models

import android.graphics.drawable.Drawable
import com.stanislav.syntechdemoapp.R

enum class TransactionType(val type: String, val drawableId: Int) {
    KIDS("kids", R.drawable.kids_icon3x),
    GAS("gas", R.drawable.gas_icon3x),
    HOME("house", R.drawable.home_icon3x),
    BANK("bank", R.drawable.bank_icon3x)
}