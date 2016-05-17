package com.example.stevendrumm.savingfiles;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class UsoFicheros extends AppCompatActivity implements View.OnClickListener {
    private static final String INTERNAL_FILENAME = "pruebaI.txt";
    private static final String EXTERNAL_FILENAME = "pruebaE.txt";

    private EditText text;
    private Button butFileResources;
    private Button butSaveInternal;
    private Button butReadInternal;
    private Button butSaveSD;
    private Button butReadSD;
    private Button butSaveCache;
    private Button butReadCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uso_ficheros);

        text = (EditText) findViewById(R.id.text);
        butFileResources = (Button) findViewById(R.id.btn_app_storage);
        butSaveInternal = (Button) findViewById(R.id.btn_save_internal_storage);
        butReadInternal = (Button) findViewById(R.id.btn_read_internal_storage);
        butSaveSD = (Button) findViewById(R.id.btn_save_sd);
        butReadSD = (Button) findViewById(R.id.btn_read_sd);
        butSaveCache = (Button) findViewById(R.id.btn_save_cache);
        butReadCache = (Button) findViewById(R.id.btn_read_cache);

        butFileResources.setOnClickListener(this);
        butSaveInternal.setOnClickListener(this);
        butReadInternal.setOnClickListener(this);
        butSaveSD.setOnClickListener(this);
        butReadSD.setOnClickListener(this);
        butSaveCache.setOnClickListener(this);
        butReadCache.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_app_storage:
                InputStream is = getResources().openRawResource(R.raw.holamundo);
                BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
                try {
                    String texto = buffer.readLine();
                    Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(this, "Error leyendo fichero raw", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_read_internal_storage:
                File rFile = new File(getFilesDir(), INTERNAL_FILENAME);
                try{
                    BufferedReader bReader = new BufferedReader(new FileReader(rFile));
                    String textRead = bReader.readLine();
                    Toast.makeText(this, textRead, Toast.LENGTH_SHORT).show();
                } catch (IOException e){
                    Toast.makeText(this, "Error leyendo fichero de memoria interna", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_save_internal_storage:
                File wFile = new File(getFilesDir(), INTERNAL_FILENAME);
                try{
                    FileWriter out = new FileWriter(wFile);
                    out.write(text.getText().toString());
                    out.close();
                }catch (IOException e){
                    Toast.makeText(this, "Error escribiendo fichero en memoria interna", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_read_cache:
                File cRFile = new File(getCacheDir(), INTERNAL_FILENAME);
                try{
                    BufferedReader bReader = new BufferedReader(new FileReader(cRFile));
                    String textRead = bReader.readLine();
                    Toast.makeText(this, textRead, Toast.LENGTH_SHORT).show();
                } catch (IOException e){
                    Toast.makeText(this, "Error leyendo fichero de memoria cache", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_save_cache:
                File cWFile = new File(getCacheDir(), INTERNAL_FILENAME);
                try{
                    FileWriter out = new FileWriter(cWFile);
                    out.write(text.getText().toString());
                    out.close();
                }catch (IOException e){
                    Toast.makeText(this, "Error escribiendo fichero en cache", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_read_sd:
                if(isExternalStorageReadable()) {
                   //File rFileE = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_FILENAME);
                    File rFileE = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_FILENAME);
                    try {
                        BufferedReader bReader = new BufferedReader(new FileReader(rFileE));
                        String textRead = bReader.readLine();
                        Toast.makeText(this, textRead, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toast.makeText(this, "Error leyendo fichero de memoria externa", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.btn_save_sd:
                if(isExternalStorageWritable()) {
                 // File wFileE = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_FILENAME);
                    File wFileE = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_FILENAME);
                    try {
                        FileWriter out = new FileWriter(wFileE);
                        out.write(text.getText().toString());
                        out.close();
                    } catch (IOException e) {
                        Toast.makeText(this, "Error escribiendo fichero en memoria externa", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


}