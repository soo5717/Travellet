package com.example.example.feature.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.example.R;

public class MainItemView extends LinearLayout {
    ImageView image;
    TextView dDay, title, period;

    public MainItemView(Context context) {
        super(context);
        init(context);
    }

    public MainItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_main_item, this, true);

        image = (ImageView) findViewById(R.id.seoulImage);
        dDay = (TextView) findViewById(R.id.dDay);
        title = (TextView) findViewById(R.id.title);
        period = (TextView) findViewById(R.id.period);
    }

    public void setImage(int image){
        this.image.setImageResource(image);
    }

    public void setdDay(String dDay){
        this.dDay.setText(dDay);
    }

    public void setTitle(String title){
        this.title.setText(title);
    }

    public void setPeriod(String period){
        this.period.setText(period);
    }


}
