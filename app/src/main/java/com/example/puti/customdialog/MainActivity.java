package com.example.puti.customdialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_dialog = (Button) findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View customDialogView = li.inflate(R.layout.custom_dialoag,null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setView(customDialogView);
                final EditText edt_mail = (EditText) customDialogView.findViewById(R.id.edt_mail);
                final EditText edt_phone = (EditText) customDialogView.findViewById(R.id.edt_phone);

                alertDialogBuilder.setCancelable(false).setPositiveButton("PHONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String phoneno = edt_phone.getText().toString();
                        if(!TextUtils.isEmpty(phoneno)){
                            String dial = "tel:" + phoneno;
                            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Enter a phone number",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("SEND", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("sendmail: ", "");
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL,edt_mail.getText().toString());
                        try {
                            startActivity(Intent.createChooser(emailIntent,"Send mail ...."));
                            finish();
                        }
                        catch (android.content.ActivityNotFoundException ex)
                        {
                            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                AlertDialog alerDialog = alertDialogBuilder.create();
                alerDialog.show();
            }
        });
    }
}
