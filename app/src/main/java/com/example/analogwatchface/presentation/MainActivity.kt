import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : WallpaperService() {

    override fun onCreateEngine(): Engine {
        return Engine()
    }

    inner class Engine : WallpaperService.Engine() {

        private lateinit var paint: Paint
        private lateinit var hourPaint: Paint
        private lateinit var minutePaint: Paint
        private lateinit var secondPaint: Paint
        private lateinit var centerPaint: Paint
        private val handler = Handler(Looper.getMainLooper())
        private var isVisible = true

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

        override fun onVisibilityChanged(visible: Boolean) {
            isVisible = visible
            if (visible) {
                drawFrame()
            } else {
                handler.removeCallbacks(drawRunnable)
            }
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            super.onSurfaceDestroyed(holder)
            isVisible = false
            handler.removeCallbacks(drawRunnable)
        }

        private val drawRunnable = Runnable { drawFrame() }

        private fun drawFrame() {
            val holder = surfaceHolder
            var canvas: Canvas? = null
            try {
                canvas = holder.lockCanvas()
                if (canvas != null) {
                    drawCanvas(canvas)
                }
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas)
                }
            }
            if (isVisible) {
                handler.postDelayed(drawRunnable, 1000) // Redraw every second
            }
        }

        private fun drawCanvas(canvas: Canvas) {
            val width = canvas.width
            val height = canvas.height
            val centerX = width / 2f
            val centerY = height / 2f
            val radius = width / 2f

            // Clear canvas
            canvas.drawColor(Color.BLACK)

            // Draw circle
            canvas.drawCircle(centerX, centerY, radius, paint)

            // Calculate the current time
            val currentTime = System.currentTimeMillis()
            val hours = (currentTime / 3600000 % 12).toFloat()
            val minutes = (currentTime / 60000 % 60).toFloat()
            val seconds = (currentTime / 1000 % 60).toFloat()

            // Draw hour hand
            val hourAngle = (PI / 6 * hours - PI / 2).toFloat()
            canvas.drawLine(
                centerX,
                centerY,
                centerX + cos(hourAngle) * radius * 0.5f,
                centerY + sin(hourAngle) * radius * 0.5f,
                hourPaint
            )

            // Draw minute hand
            val minuteAngle = (PI / 30 * minutes - PI / 2).toFloat()
            canvas.drawLine(
                centerX,
                centerY,
                centerX + cos(minuteAngle) * radius * 0.7f,
                centerY + sin(minuteAngle) * radius * 0.7f,
                minutePaint
            )

            // Draw second hand
            val secondAngle = (PI / 30 * seconds - PI / 2).toFloat()
            canvas.drawLine(
                centerX,
                centerY,
                centerX + cos(secondAngle) * radius * 0.9f,
                centerY + sin(secondAngle) * radius * 0.9f,
                secondPaint
            )

            // Draw center
            canvas.drawCircle(centerX, centerY, 10f, centerPaint)
        }
    }
}
