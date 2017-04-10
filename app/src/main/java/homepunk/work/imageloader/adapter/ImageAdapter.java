package homepunk.work.imageloader.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.work.imageloader.R;
import homepunk.work.imageloader.data.ImageLoaderTaskAsync;
import timber.log.Timber;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {
    private String[] imageUrls;

    public ImageAdapter(String[] imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_image, parent, false);

        return new ImageHolder(root);
    }

    @Override
    public void onBindViewHolder(final ImageHolder holder, int position) {
        String path = imageUrls[position];
        Timber.i("onBindViewHolder " + position);
        holder.showProgress();
        holder.setId(position);

        new ImageLoaderTaskAsync(new ImageLoaderTaskAsync.IResultListner() {
            @Override
            public void onResult(Bitmap result) {
                if (result != null) {
                    holder.hideProgress();
                    holder.setImageBitmap(result);
                }
            }

            @Override
            public void onError(String error) {

            }
        }).execute(path);

    }

    @Override
    public int getItemCount() {
        return imageUrls.length;
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_image)
        ImageView imageView;

        @Bind(R.id.item_image_id)
        TextView imageId;

        @Bind(R.id.item_progress)
        ProgressBar progressBar;

        public ImageHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        private void hideProgress(){
            progressBar.setVisibility(View.GONE);
        }

        private void showProgress(){
            progressBar.setVisibility(View.VISIBLE);
        }

        private void setImageBitmap(Bitmap bitmap) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }

        private void setId(int id) {
            imageId.setText(String.valueOf(id));
        }
    }
}