import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder

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

        override fun onDraw(holder: SurfaceHolder) {
            val canvas = holder.lockCanvas()
            canvas.drawColor(Color.BLACK)

            // Draw the watch face
            val centerX = canvas.width / 2f
            val centerY = canvas.height / 2f
            val radius = Math.min(centerX, centerY) - 50f

            // Draw the circle
            canvas.drawCircle(centerX, centerY, radius, paint)

            // Draw the hour hand
            val hourAngle = System.currentTimeMillis() % 43200000 / 432000f * 30f
            canvas.drawLine(centerX, centerY, centerX + radius * 0.5f * Math.sin(hourAngle * Math.PI / 180f), centerY - radius * 0.5f * Math.cos(hourAngle * Math.PI / 180f), hourPaint)

            // Draw the minute hand
            val minuteAngle = System.currentTimeMillis() % 3600000 / 60000f * 6f
            canvas.drawLine(centerX, centerY, centerX + radius * 0.8f * Math.sin(minuteAngle * Math.PI / 180f), centerY - radius * 0.8f * Math.cos(minuteAngle * Math.PI / 180f), minutePaint)

            // Draw the second hand
            val secondAngle = System.currentTimeMillis() % 60000 / 1000f * 6f
            canvas.drawLine(centerX, centerY, centerX + radius * 0.9f * Math.sin(secondAngle * Math.PI / 180f), centerY - radius * 0.9f * Math.cos(secondAngle * Math.PI / 180f), secondPaint)

            // Draw the center dot
            canvas.drawCircle(centerX, centerY, 10f, centerPaint)

            holder.unlockCanvasAndPost(canvas)
        }
    }
}