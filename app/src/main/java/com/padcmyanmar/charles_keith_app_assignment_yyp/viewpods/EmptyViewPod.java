package com.padcmyanmar.charles_keith_app_assignment_yyp.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.padcmyanmar.charles_keith_app_assignment_yyp.R;
import com.padcmyanmar.charles_keith_app_assignment_yyp.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmptyViewPod extends RelativeLayout {

    @BindView(R.id.iv_empty_img)
    ImageView ivEmptyImg;

    @BindView(R.id.tv_empty_text)
    TextView tvEmptyText;

    public EmptyViewPod(Context context) {
        super(context);
    }

    public EmptyViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);
    }

    public void setEmptyData(String emptyImageUrl, String emptyMsg) {
        GlideApp.with(getContext())
                .load(emptyImageUrl)
                .into(ivEmptyImg);

        tvEmptyText.setText(emptyMsg);
    }

    public void setEmptyData(int emptyImageResource, String emptyMsg) {
        ivEmptyImg.setImageResource(emptyImageResource);
        tvEmptyText.setText(emptyMsg);
    }
}
