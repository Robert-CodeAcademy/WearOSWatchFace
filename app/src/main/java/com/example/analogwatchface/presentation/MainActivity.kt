import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.wear.watchface.WatchFaceService
import androidx.wear.watchface.WatchFaceService.Engine
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : WatchFaceService() {

    private lateinit var paint: Paint
    private lateinit var hourPaint: Paint
    private lateinit var minutePaint: Paint
    private lateinit var secondPaint: Paint
    private lateinit var centerPaint: Paint

    override fun onCreateEngine(): Engine {
        return Engine()
    }

    inner class Engine : WatchFaceService.Engine() {

        override fun onCreate(holder: androidx.wear.watchface.WatchFaceService.Engine.SurfaceHolder) {
            super.onCreate(holder)

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

        override fun onDraw(canvas: Canvas, bounds: Rect) {
            super.onDraw(canvas, bounds)

            val centerX = bounds.centerX().toFloat()
            val centerY = bounds.centerY().toFloat()
            val radius = bounds.width() / 2f

            // Draw circle
            canvas.drawCircle(centerX, centerY, radius, paint)

            // Draw hour hand
            val hourAngle = PI / 6 * (System.currentTimeMillis() / 1000 / 3600 % 12).toFloat()
            canvas.drawLine(centerX, centerY, centerX + cos(hourAngle) * radius * 0.5f, centerY + sin(hourAngle) * radius * 0.5f, hourPaint)

            // Draw minute hand
            val minuteAngle = PI / 30 * (System.currentTimeMillis() / 1000 / 60 % 60).toFloat()
            canvas.drawLine(centerX, centerY, centerX + cos(minuteAngle) * radius * 0.7f, centerY + sin(minuteAngle) * radius * 0.7f, minutePaint)

            // Draw second hand
            val secondAngle = PI / 30 * (System.currentTimeMillis() / 1000 % 60).toFloat()
            canvas.drawLine(centerX, centerY, centerX + cos(secondAngle) * radius * 0.9f, centerY + sin(secondAngle) * radius * 0.9f, secondPaint)

            // Draw center
            canvas.drawCircle(centerX, centerY, 5f, centerPaint)
        }
    }
}