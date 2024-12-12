import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class WatchFaceService : WallpaperService() {

    override fun onCreateEngine(): Engine {
        return Engine()
    }

    inner class Engine : WallpaperService.Engine() {

        private lateinit var paint: Paint
        private lateinit var hourPaint: Paint
        private lateinit var minutePaint: Paint
        private lateinit var secondPaint: Paint
        private lateinit var centerPaint: Paint

        override fun onCreate(surfaceHolder: SurfaceHolder) {
            super.onCreate(surfaceHolder)

            paint = Paint().apply {
                color = Color.WHITE
                style = Paint.Style.STROKE
                strokeWidth = 5f
            }

            hourPaint = Paint().apply {
                color = Color.WHITE
                style = Paint.Style.STROKE
                strokeWidth = 10f
            }

            minutePaint = Paint().apply {
                color = Color.WHITE
                style = Paint.Style.STROKE
                strokeWidth = 5f
            }

            secondPaint = Paint().apply {
                color = Color.RED
                style = Paint.Style.STROKE
                strokeWidth = 2f
            }

            centerPaint = Paint().apply {
                color = Color.WHITE
                style = Paint.Style.FILL
                strokeWidth = 5f
            }
        }

        override fun onSurfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            super.onSurfaceChanged(holder, format, width, height)
        }

        override fun onSurfaceCreated(holder: SurfaceHolder) {
            super.onSurfaceCreated(holder)
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            super.onSurfaceDestroyed(holder)
        }

        override fun onVisibilityChanged(changed: Boolean) {
            super.onVisibilityChanged(changed)
        }

        override fun onOffsetsChanged(xOffset: Float, yOffset: Float, xOffsetStep: Float, yOffsetStep: Float, xPixelOffset: Int, yPixelOffset: Int) {
            super.onOffsetsChanged(xOffset, yOffset, xOffsetStep, yOffsetStep, xPixelOffset, yPixelOffset)
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {
            return super.onTouchEvent(event)
        }

        override fun onCommand(action: String, x: Int, y: Int, z: Int, extras: Bundle, resultRequestCode: Int): Bundle? {
            return super.onCommand(action, x, y, z, extras, resultRequestCode)
        }

        override fun onAttachedToWindow() {
            super.onAttachedToWindow()
        }

        override fun onDetachedFromWindow() {
            super.onDetachedFromWindow()
        }

        override fun onApplyWindowInsets(insets: WindowInsetsCompat): WindowInsetsCompat {
            return super.onApplyWindowInsets(insets)
        }
    }
}