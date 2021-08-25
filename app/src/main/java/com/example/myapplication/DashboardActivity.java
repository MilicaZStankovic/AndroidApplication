package com.example.myapplication;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


public class DashboardActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dashboard);
        drawerLayout = findViewById(R.id.drawer);
    }
        public void ClickMenu (View v){
            openDrawer(drawerLayout);
        }
    private void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogOut(View v) {

        logout(this);
    }
    private  static void logout( final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Logout");
        builder.setMessage("are you sure you want to logot?");
        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.show();
    }
    public  static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickMap(View v){

       redirectActivity(this,CurrentLocation.class);
    }

    public void ClickTermin(View v){
        redirectActivity(this,ListActivity.class);
    }

    public void ClickTermin2(View v){

        redirectActivity(this, Termin.class);
    }
    public void ClickRezultat(View v){
        redirectActivity(this,UploadPictureActivity.class);
    }

    public void ClickYouTube(View v){

        redirectActivity(this,SecondActivity.class);
    }

    public void ClickwhatApp(View v){

        redirectActivity(this, WhatsAppActivity.class);
    }
    public void ClickOcena(View v){

        redirectActivity(this,OcenaActivity.class);
    }
    public void ClickPosition(View v){

        redirectActivity(this, AccelometerActivity.class);
    }



    public static void redirectActivity(Activity activity, Class aclass){
        Intent intent = new Intent(activity,aclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }
    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }


}



