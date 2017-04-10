package exam.homepunk.registration.ui.interfaces;

import exam.homepunk.registration.model.Image;

public interface IImageLoaderView {
    void onResult(Image image);
    void onError(String e);
}
