package homepunk.work.imageloader.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.work.imageloader.R;
import homepunk.work.imageloader.adapter.ImageAdapter;
import homepunk.work.imageloader.model.Image;
import homepunk.work.imageloader.ui.interfaces.IImageLoaderView;

import static homepunk.work.imageloader.data.Constants.imageUrls;

public class ImageLoaderActivity extends AppCompatActivity implements IImageLoaderView {
    @Bind(R.id.recycleview)
    RecyclerView imageLoaderRecycleView;

    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpRecycleView();

    }

    @Override
    public void onResult(Image image) {

    }

    @Override
    public void onError(String e) {

    }

    private void setUpRecycleView() {
        imageAdapter = new ImageAdapter(imageUrls);
        imageLoaderRecycleView.setLayoutManager(new LinearLayoutManager(this));
        imageLoaderRecycleView.setAdapter(imageAdapter);
    }
}