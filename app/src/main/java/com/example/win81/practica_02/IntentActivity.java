package com.example.win81.practica_02;

import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class IntentActivity extends AppCompatActivity implements View.OnClickListener {
    @Override public void onClick(View v){
        Intent i = new Intent(Intent.ACTION_VIEW);
        switch (v.getId()){
            
            case R.id.buttonCallPhone:             //content://contacts/people/1
                 i.setData(Uri.parse("tel:5512345678"));
            startActivity(i);
            break;
            case R.id.buttonMaps:
                i.setData(Uri.parse("geo:19.6922729,98.84569220?z17"));
                startActivity(i);
            break;
            case  R.id.buttonAlert:
                Calendar date = Calendar.getInstance();
                int h = date.get(Calendar.HOUR_OF_DAY);
                int m = date.get(Calendar.MINUTE) + 2;
                i = new Intent(AlarmClock.ACTION_SET_ALARM).putExtra(AlarmClock.EXTRA_MESSAGE,"Mensaje Alarma").putExtra(AlarmClock.EXTRA_HOUR, h)
                        .putExtra(AlarmClock.EXTRA_MINUTES, m);
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"No se puede asignar la alarma", Toast.LENGTH_SHORT).show();
                }
            break;
            case R.id.buttonSms:
                i = new Intent(Intent.ACTION_VIEW);
                i.putExtra("sms_body", "Mensaje");
                i.putExtra("address", new String("5529536117"));
                i.setType("vnd.android-dir/mms-sms");             startActivity(i);
             break;
             case R.id.buttonCamera:
                    i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if(i.resolveActivity(getPackageManager()) != null){
                        startActivity(i);
                    }
                    break;
            case R.id.buttonMail:
                i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT,"Ejemplo Email");
                i.putExtra(Intent.EXTRA_TEXT,"Estes es un correo de pruea");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"omendoza@arcelia.net","megomar@gmail.com"});
                try{
                    startActivity(
                            Intent.createChooser(i, "Quien puede enviar un Email?"));
                }catch (android.content.ActivityNotFoundException e){
                    e.printStackTrace();
                }
            break;
        } }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        findViewById(R.id.buttonAlert).setOnClickListener(this);
        findViewById(R.id.buttonCallPhone).setOnClickListener(this);
        findViewById(R.id.buttonCamera).setOnClickListener(this);
        findViewById(R.id.buttonMail).setOnClickListener(this);
        findViewById(R.id.buttonMaps).setOnClickListener(this);
        findViewById(R.id.buttonPhone).setOnClickListener(this);
        findViewById(R.id.buttonSms).setOnClickListener(this);
    }
    public void btnWeb(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String urlWeb = "http://www.unam.mx";
        intent.setData(Uri.parse(urlWeb));
        startActivity(intent); }
}
