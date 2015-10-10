package com.hhl.recyclerview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.hhl.adapter.BaseAdapterHelper;
import com.hhl.adapter.QuickAdapter;
import com.hhl.recyclerview.demo.model.PostModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanHailong on 15/9/7.
 */
public class TimelineActivity extends AppCompatActivity {

    private QuickAdapter<PostModel> mQuickAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        getSupportActionBar().setTitle("Timeline");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



        mQuickAdapter = new QuickAdapter<PostModel>(this, R.layout.post_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, PostModel item) {
                helper.setText(R.id.tv_username, item.getUsername())
                        .setText(R.id.tv_location, item.getLocation())
                        .setText(R.id.tv_time, "9/7 10:35pm")
                        .setText(R.id.tv_post, item.getPost())
                        .setText(R.id.tv_reason, item.getReason())
                        .setText(R.id.btn_likes, item.getLikes() + " Likes")
                        .setText(R.id.btn_comments, item.getComments() + " Comments");
                ImageLoader.getInstance().displayImage(item.getAvatar(), helper
                  .getImageView(R
                    .id.iv_avatar));
            }
        };

        recyclerView.setAdapter(mQuickAdapter);

        initData();
    }


    /**
     * 初始化数据
     */
    private void initData() {
        List<PostModel> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            PostModel post = new PostModel();
            post.setUsername("Adrian Hsu");
            post.setLocation("near Neihu, Taipei");
            post.setPost("Deserve a kick on my ass now LOL");
            post.setReason("- Open app frequency exceeds");
            post.setAvatar("http://f.hiphotos.baidu" +
              ".com/image/pic/item/cdbf6c81800a19d8218dbbd436fa828ba71e4650.jpg");
            post.setLikes(i);
            post.setComments(i);

            list.add(post);
        }
        mQuickAdapter.addAll(list);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
