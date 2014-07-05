package rejasupotaro.sample.tools;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.TextView;

public class PaletteManager {

    private LruCache<String, Palette> cache = new LruCache<String, Palette>(100);

    public interface Callback {
        void onPaletteReady(Palette palette);
    }

    public void getPalette(final String key, Bitmap bitmap, final Callback callback) {
        Palette palette = cache.get(key);
        if (palette != null)
            callback.onPaletteReady(palette);
        else
            Palette.generateAsync(bitmap, 24, new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    cache.put(key, palette);
                    callback.onPaletteReady(palette);
                }
            });
    }

    private static int setColorAlpha(int color, int alpha) {
        return (alpha << 24) | (color & 0x00ffffff);
    }

    public void updatePalette(PaletteManager paletteManager, ImageView imageView, final TextView textView) {
        String key = imageView.toString();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        paletteManager.getPalette(key, bitmap, new PaletteManager.Callback() {
            @Override
            public void onPaletteReady(Palette palette) {
                int bgColor = palette.getDarkVibrantColor().getRgb();
                textView.setBackgroundColor(setColorAlpha(bgColor, 192));
                textView.setTextColor(palette.getLightMutedColor().getRgb());
            }
        });
    }

}
