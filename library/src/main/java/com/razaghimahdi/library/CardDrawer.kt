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
package com.razaghimahdi.library

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.dismiss
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.razaghimahdi.library.core.*
import com.razaghimahdi.library.core.toDp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


/**
 * @param drawerContent composable that represents content inside the drawer
 * @param modifier optional modifier for the drawer
 * @param drawerState state of the drawer
 * @param gesturesEnabled whether or not drawer can be interacted by gestures
 * @param drawerBackgroundColor background color to be used for the drawer sheet
 * @param drawerContentColor color of the content to use inside the drawer sheet. Defaults to
 * @param contentCornerSize size of shape of the content
 * @param contentBackgroundColor background color to be used for the content
 * @param content content of the rest of the UI
 *
 * @throws IllegalStateException when parent has [Float.POSITIVE_INFINITY] width
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardDrawer(
    drawerContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    drawerState: CardDrawerState =
        rememberCardDrawerState(CardDrawerValue.Closed),
    gesturesEnabled: Boolean = true,
    drawerBackgroundColor: Color = MaterialTheme.colors.surface,
    drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
    contentCornerSize: Dp = 0.dp,
    contentBackgroundColor: Color = MaterialTheme.colors.surface,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp.toPx()


    val endContentXPosition = screenWidth / 1.4f



    BoxWithConstraints(modifier.background(drawerBackgroundColor)) {
        val modalDrawerConstraints = constraints
        // TODO : think about Infinite max bounds case
        if (!modalDrawerConstraints.hasBoundedWidth) {
            throw IllegalStateException("Drawer shouldn't have infinite width")
        }


        val minValue = -modalDrawerConstraints.maxWidth.toFloat()
        val maxValue = 0f
        val anchors =
            mapOf(minValue to CardDrawerValue.Closed, maxValue to CardDrawerValue.Open)
        val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
        Box(
            Modifier.swipeable(
                state = drawerState.swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal,
                enabled = gesturesEnabled,
                reverseDirection = isRtl,
                velocityThreshold = drawerState.DrawerVelocityThreshold,
                resistance = null
            )
        ) {

            Surface(
                Modifier
                    .fillMaxHeight()
                    .offset {
                        // IntOffset(drawerState.offset.value.roundToInt(), 0)

                        val x = (drawerState.offset.value.roundToInt())
                        if (x <= (screenWidth / 1.4f).roundToInt()) {
                            IntOffset(x, 0)
                        } else {
                            IntOffset((screenWidth / 1.4f).roundToInt(), 0)
                        }


                    }
                     .width(endContentXPosition.toDp())
                    .widthIn(0.dp, endContentXPosition.toDp())
                    .semantics {
                        paneTitle = "FullDrawerLayout"
                        if (drawerState.isOpen) {
                            dismiss {
                                if (
                                    drawerState.currentValue == CardDrawerValue.Closed
                                ) {
                                    scope.launch { drawerState.close() }
                                }; true
                            }
                        }
                    },
                color = drawerBackgroundColor,
                contentColor = drawerContentColor
            ) {
                Column(content = drawerContent)
            }



            Surface(
                Modifier
                    .fillMaxSize()
                    .offset {
                        val x = (drawerState.offset.value.roundToInt() + screenWidth.roundToInt())
                        if (x <= (screenWidth / 1.4f).roundToInt()) {
                            IntOffset(x, 0)
                        } else {
                            IntOffset((screenWidth / 1.4f).roundToInt(), 0)
                        }
                    }
                    .graphicsLayer {
                         rotationY = (-((25 * (drawerState.offset.value / screenWidth)) + 25))
                         cameraDistance = 8 * density
                    },
                color = contentBackgroundColor,
                shape = RoundedCornerShape((((contentCornerSize.value * (drawerState.offset.value / screenWidth)) + contentCornerSize.value).roundToInt().dp))
            ) {
                content()
            }

        }
    }
}