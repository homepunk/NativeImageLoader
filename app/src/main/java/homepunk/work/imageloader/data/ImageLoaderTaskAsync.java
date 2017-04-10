package homepunk.work.imageloader.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import timber.log.Timber;

public class ImageLoaderTaskAsync extends AsyncTask<String, Void, Bitmap> {
    private IResultListner resultListner;

    public ImageLoaderTaskAsync(IResultListner listner) {
        this.resultListner = listner;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Timber.i("doInBackground() ");
        Bitmap bitmap = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

            if (bitmap != null) {
                return bitmap;
            }
        } catch (Exception e) {
            resultListner.onError(e.getLocalizedMessage());
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);

        if (result == null) {
            return;
        }

        resultListner.onResult(result);
    }

    public interface IResultListner {
        void onResult(Bitmap result);

        void onError(String error);
    }

}
