package glass.yasan.magic

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runDesktopComposeUiTest
import io.github.takahirom.roborazzi.captureRoboImage
import sergio.sastre.composable.preview.scanner.jvm.JvmAnnotationScanner
import kotlin.test.Test

/**
 * Automatically discovers and screenshots all [glass.yasan.magic.util.PreviewWithTest] annotated previews.
 */
class PreviewScreenshotTests {

    companion object {
        private val previews by lazy {
            JvmAnnotationScanner("glass.yasan.magic.util.PreviewWithTest")
                .scanPackageTrees("glass.yasan.magic")
                .getPreviews()
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun screenshotTests() {
        previews.forEach { preview ->
            runDesktopComposeUiTest {
                setContent {
                    preview()
                }
                onRoot().captureRoboImage(
                    filePath = "src/jvmTest/snapshots/${preview.methodName}.png",
                )
            }
        }
    }
}
