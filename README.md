# Card Drawer

`Card Drawer` is a library that allows developers to add a custom cool Drawer to their Jetpack
Compose apps. The library provides a customizable drawer that lets users select items, with options
for updating the selected date and other settings.

[![](https://jitpack.io/v/razaghimahdi/Card-Drawer.svg)](https://jitpack.io/#razaghimahdi/Card-Drawer)

## Quickstart

Here's a quick example of how to use the library:

1. Add the JitPack repository to your project-level build.gradle or settings.gradle file:

```groovy
    allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the library dependency to your app-level build.gradle file:

```groovy
    dependencies {
    implementation 'com.github.razaghimahdi:Card-Drawer:1.0.0'
} 
```

3. Use the CardDrawer in your app:

```kotlin
val drawerState = rememberCardDrawerState(initialValue = CardDrawerValue.Closed)

CardDrawer(
    drawerState = drawerState,
    drawerContent = { DrawerContent(drawerState) }
) {
    MainScreen(drawerState)
}
```

4. Customize CardDrawer:

```Kotlin
CardDrawer(
    modifier = Modifier,
    gesturesEnabled = true,
    drawerBackgroundColor = MaterialTheme.colors.surface,
    drawerContentColor = contentColorFor(drawerBackgroundColor),
    contentCornerSize = 0.dp,
    contentBackgroundColor = MaterialTheme.colors.surface,
    drawerContent = { DrawerContent(drawerState) },
    drawerState = drawerState
) {
    MainScreen(drawerState)
}
```

## Example

For a more detailed example, check out
the [example app](https://github.com/razaghimahdi/CardDrawer/blob/master/app/src/main/java/com/razaghimahdi/fullnavigationdrawer/MainActivity.kt)
included in the repository.

## Parameter

CardDrawer has this Parameters:
| Parameter | Description |
| --- | --- |
| `drawerContent` | A Composable function that represents the content inside the drawer. |
| `modifier` | An optional Modifier that can be used to apply additional styling or layout information to the drawer. |
| `drawerState` | A CardDrawerState object that represents the state of the drawer (e.g. open, closed, partially open). |
| `gesturesEnabled` | A Boolean value that determines whether or not the drawer can be interacted with via gestures (e.g. swipe to open/close). |
| `drawerBackgroundColor` | A Color value that specifies the background color to be used for the drawer sheet. |
| `drawerContentColor` | A Color value that specifies the color of the content to be used inside the drawer sheet. Defaults to the contentColorFor function applied to the drawerBackgroundColor. |
| `contentCornerSize` | A Dp value that specifies the size of the shape of the content. |
| `contentBackgroundColor` | A Color value that specifies the background color to be used for the content outside of the drawer. |
| `content` | A Composable function that represents the content outside of the drawer. |


## Screenshots

https://user-images.githubusercontent.com/61207818/230716598-1f08fd4b-3fe4-4d4c-9b44-84174aae00a2.mp4


## Contributing

Contributions are welcome! If you find a bug or would like to create a new feature, please submit a
pull request.

## License

This library is licensed under the MIT License.
See [LICENSE.txt](https://github.com/razaghimahdi/CardDrawer/blob/master/LICENSE)

Developed by Mahdi Razzaghi Ghaleh
