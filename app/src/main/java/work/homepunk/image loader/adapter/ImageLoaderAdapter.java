package exam.homepunk.registration.adapter;

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
import exam.homepunk.registration.R;
import exam.homepunk.registration.data.ImageLoaderTaskAsync;
import timber.log.Timber;

public class ImageLoaderAdapter extends RecyclerView.Adapter<ImageLoaderAdapter.ImageLoaderHolder> {
    private String[] imageUrls;

    public ImageLoaderAdapter(String[] imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public ImageLoaderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_image, parent, false);

        return new ImageLoaderHolder(root);
    }

    @Override
    public void onBindViewHolder(final ImageLoaderHolder holder, final int position) {
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

    public class ImageLoaderHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_image)
        ImageView imageView;

        @Bind(R.id.item_image_id)
        TextView imageId;

        @Bind(R.id.item_progress)
        ProgressBar progressBar;

        public ImageLoaderHolder(View root) {
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
