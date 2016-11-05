package com.example.apu.safechat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by apuchakroborti on 10/27/2016.
 */

public class SetImageActivity extends Activity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        iv=(ImageView)findViewById(R.id.ivSetImage);
        //ImageView imageView = new ImageView(this);
       // Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.du);
        //iv.setImageBitmap(bImage);

        iv.setImageResource(R.mipmap.ic_launcher);

    }
}
