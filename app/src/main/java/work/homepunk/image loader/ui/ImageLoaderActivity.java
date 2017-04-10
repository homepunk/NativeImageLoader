package exam.homepunk.registration.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import exam.homepunk.registration.R;
import exam.homepunk.registration.adapter.ImageLoaderAdapter;
import exam.homepunk.registration.data.Constants;
import exam.homepunk.registration.model.Image;
import exam.homepunk.registration.ui.interfaces.IImageLoaderView;

public class ImageLoaderActivity extends AppCompatActivity implements IImageLoaderView {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycleview)
    RecyclerView imageLoaderRecycleView;

    private ImageLoaderAdapter imageLoaderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setUpRecycleView();

    }

    @Override
    public void onResult(Image image) {

    }

    @Override
    public void onError(String e) {

    }

    private void setUpRecycleView() {
        imageLoaderAdapter = new ImageLoaderAdapter(Constants.imageUrls);
        imageLoaderRecycleView.setLayoutManager(new LinearLayoutManager(this));
        imageLoaderRecycleView.setAdapter(imageLoaderAdapter);
    }
}
