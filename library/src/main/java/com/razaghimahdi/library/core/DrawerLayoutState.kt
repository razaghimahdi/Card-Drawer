/*
 * Copyright (C) 2023 razaghimahdi (Mahdi Razzaghi Ghaleh)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.razaghimahdi.library.core

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.SwipeableDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.util.*


sealed class DrawerLayoutValue {
    /**
     * The drawer layout is not visible.
     */
    object Closed : DrawerLayoutValue(){
        override fun toString(): String {
            return "Closed"
        }
    }

    /**
     * The drawer layout is visible.
     */
    object Open : DrawerLayoutValue(){
        override fun toString(): String {
            return "Open"
        }
    }

}

@Composable
fun rememberDrawerLayoutState(initialValue: DrawerLayoutValue): DrawerLayoutState {
    return remember {
        DrawerLayoutState(
            initialValue = initialValue,
            animationSpec = SwipeableDefaults.AnimationSpec,
            confirmStateChange = { true })
    }
}


class DrawerLayoutState(
    initialValue: DrawerLayoutValue,
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    confirmStateChange: (DrawerLayoutValue) -> Boolean = { true }
) {

    private var _drawerLayoutValue: MutableState<DrawerLayoutValue> = mutableStateOf(initialValue)
    internal val drawerLayoutValue get() = _drawerLayoutValue.value

    fun updateDrawerLayoutValue(value: DrawerLayoutValue) {
        _drawerLayoutValue.value = value
    }



}