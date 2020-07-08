package com.navibees.navigatorapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

public class SliderAdapter extends PagerAdapter {
    private List<String> colorName;
    private Context context;
    private Integer[] images;

    public SliderAdapter(Context context2, Integer[] images2) {
        this.context = context2;
        this.images = images2;
    }

    public int getCount() {
        return this.images.length;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgSlide = new ImageView(this.context);
        Glide.with(this.context).load(this.images[position]).apply((BaseRequestOptions<?>) RequestOptions.centerCropTransform()).into(imgSlide);
        ((ViewPager) container).addView(imgSlide, 0);
        return imgSlide;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
