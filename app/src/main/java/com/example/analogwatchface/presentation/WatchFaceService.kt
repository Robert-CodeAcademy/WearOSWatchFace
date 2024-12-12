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

        override fun onDraw(holder: SurfaceHolder, canvas: Canvas, x: Int, y: Int, width: Int, height: Int) {
            super.onDraw(holder, canvas, x, y, width, height)

            val centerX = width / 2f
            val centerY = height / 2f
            val radius = width / 2f

            // Draw circle
            canvas.drawCircle(centerX, centerY, radius, paint)

            // Draw hour hand
            val hourAngle = (PI / 6 * ((System.currentTimeMillis() / 1000L) / 3600 % 12)).toFloat()
            canvas.drawLine(centerX, centerY, centerX + cos(hourAngle) * radius * 0.5f, centerY + sin(hourAngle) * radius * 0.5f, hourPaint)

            // Draw minute hand
            val minuteAngle = (PI / 30 * ((System.currentTimeMillis() / 1000L) / 60 % 60)).toFloat()
            canvas.drawLine(centerX, centerY, centerX + cos(minuteAngle) * radius * 0.7f, centerY + sin(minuteAngle) * radius * 0.7f, minutePaint)

            // Draw second hand
            val secondAngle = (PI / 30 * ((System.currentTimeMillis() / 1000L) % 60)).toFloat()
            canvas.drawLine(centerX, centerY, centerX + cos(secondAngle) * radius * 0.9f, centerY + sin(secondAngle) * radius * 0.9f, secondPaint)

            // Draw center
            canvas.drawCircle(centerX, centerY, 5f, centerPaint)

            // Update the watch face every second
            postInvalidateDelayed(1000)
        }
    }
}