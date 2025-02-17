package presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import wand.shared.generated.resources.Res
import wand.shared.generated.resources.ic_back

/**
 * @author yuming
 */

@Composable
fun ContentWithTitleBar(
    navigationDrawableResource: DrawableResource = Res.drawable.ic_back,
    titleText: String,
    bgColor: Color = MaterialTheme.colorScheme.background,
    menu: @Composable RowScope.() -> Unit = {},
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.safeDrawingPadding()) {
        TitleBar(
            navigationDrawableResource = navigationDrawableResource,
            titleText = titleText,
            bgColor = bgColor,
            menu = menu
        )
        content()
    }
}