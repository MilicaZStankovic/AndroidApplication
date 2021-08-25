package com.example.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WhatsAppActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whatsapps);
        Button button = findViewById(R.id.dugme);
         final EditText edit1 = findViewById(R.id.edit_mobile);
         final EditText edit2 = findViewById(R.id.edit_message);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String mobile = edit1.getText().toString();
                String message = edit2.getText().toString();
                boolean installed = appInstallOrNot("com.whatsapp");

                if(installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + mobile +"text"+message));
                    startActivity(intent);
                }else{
                    Toast.makeText(WhatsAppActivity.this,"Whats app not installed",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean appInstallOrNot(String url) {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try{
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            app_installed = true;

        }catch(PackageManager.NameNotFoundException e){
            app_installed = false;
        }return app_installed;
    }

}
