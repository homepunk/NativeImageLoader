package homepunk.work.imageloader.model;

import android.graphics.Bitmap;

public class Image {
    private Bitmap bitmap;

    public Image() {
    }

    public Image(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}