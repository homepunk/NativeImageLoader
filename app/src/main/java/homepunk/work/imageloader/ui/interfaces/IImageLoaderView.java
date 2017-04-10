package homepunk.work.imageloader.ui.interfaces;

import homepunk.work.imageloader.model.Image;

public interface IImageLoaderView {
    void onResult(Image image);
    void onError(String e);
}