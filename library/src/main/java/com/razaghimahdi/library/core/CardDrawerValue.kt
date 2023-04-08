package com.razaghimahdi.library.core


sealed class CardDrawerValue {
    /**
     * The drawer layout is not visible.
     */
    object Closed : CardDrawerValue(){
        override fun toString(): String {
            return "Closed"
        }
    }

    /**
     * The drawer layout is visible.
     */
    object Open : CardDrawerValue(){
        override fun toString(): String {
            return "Open"
        }
    }

}