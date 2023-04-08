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
import androidx.compose.animation.core.TweenSpec
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CancellationException



@Composable
fun rememberCardDrawerState(initialValue: CardDrawerValue): CardDrawerState {
    return remember {
        CardDrawerState(initialValue = initialValue)
    }
}


@OptIn(ExperimentalMaterialApi::class)
class CardDrawerState(
    initialValue: CardDrawerValue,
    animationSpec: TweenSpec<Float> = TweenSpec<Float>(durationMillis = 500),
    confirmStateChange: (CardDrawerValue) -> Boolean = { true }
) {

    internal val DrawerVelocityThreshold = 400.dp
    private var AnimationSpec = animationSpec


    internal val swipeableState = SwipeableState(
        initialValue = initialValue,
        animationSpec = AnimationSpec,
        confirmStateChange = confirmStateChange
    )


    /**
     * The current position (in pixels) of the drawer sheet.
     */
    @Suppress("EXPERIMENTAL_ANNOTATION_ON_WRONG_TARGET")
    @ExperimentalMaterialApi
    val offset: State<Float>
        get() = swipeableState.offset



    /**
     * The current value of the state.
     *
     * If no swipe or animation is in progress, this corresponds to the start the drawer
     * currently in. If a swipe or an animation is in progress, this corresponds the state drawer
     * was in before the swipe or animation started.
     */
    val currentValue: CardDrawerValue
        get() {
            return swipeableState.currentValue
        }


    /**
     * Whether the drawer is open, either in opened or expanded state.
     */
    val isOpen: Boolean
        get() = currentValue != CardDrawerValue.Closed

    /**
     * Whether the drawer is closed.
     */
    val isClosed: Boolean
        get() = currentValue == CardDrawerValue.Closed




    /**
     * Open the drawer with animation and suspend until it if fully opened or animation has been
     * cancelled. This method will throw [CancellationException] if the animation is
     * interrupted
     *
     * @return the reason the open animation ended
     */
    suspend fun open() = animateTo(CardDrawerValue.Open, AnimationSpec)

    /**
     * Close the drawer with animation and suspend until it if fully closed or animation has been
     * cancelled. This method will throw [CancellationException] if the animation is
     * interrupted
     *
     * @return the reason the close animation ended
     */
    suspend fun close() = animateTo(CardDrawerValue.Closed, AnimationSpec)

    /**
     * Set the state of the drawer with specific animation
     *
     * @param targetValue The new value to animate to.
     * @param anim The animation that will be used to animate to the new value.
     */
    @ExperimentalMaterialApi
    suspend fun animateTo(targetValue: CardDrawerValue, anim: AnimationSpec<Float>) {
        swipeableState.animateTo(targetValue, anim)
    }


}