package exam.homepunk.registration.interfaces;

import java.util.List;

import exam.homepunk.registration.model.Image;
import rx.Observable;

public interface IImageLoaderModel {
        Observable<List<Image>> loadImages();
}
