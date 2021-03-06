package com.example.example.feature.main.set;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.example.R;
import com.example.example.feature.main.Main;

public class TravelTitleSet extends AppCompatActivity {

    EditText Edittext_title;
    Button btn_Next;
    String title;
    int mainPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_travel_title_set);
        Intent intent = getIntent();
        mainPosition = intent.getIntExtra("mainPosition", 0);

        Edittext_title = (EditText) findViewById(R.id.Edittext_title);

        btn_Next = (Button) findViewById(R.id.btn_Next);
        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = Edittext_title.getText().toString();
                if(title == null || title.length() == 0){
                    Edittext_title.setHintTextColor(getColor(R.color.coral_red));
                    //btn_Next.isClickable(false);
                }else{
                    Intent intent = new Intent(TravelTitleSet.this, TravelCalendarSet.class);
                    intent.putExtra("travelTitle", title);
                    intent.putExtra("mainPosition", mainPosition);
                    //Toast.makeText(getApplicationContext(), title, Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }

            }
        });

    }

    //Main Empty
    public void onButtonClick(View view1){
        Intent intent = new Intent(TravelTitleSet.this, Main.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
