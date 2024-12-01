import android.service.wallpaper.WallpaperService

class MainActivity : WallpaperService() {

    override fun onCreateEngine(): Engine {
        return Engine()
    }

    inner class Engine : WallpaperService.Engine() {

    }
}