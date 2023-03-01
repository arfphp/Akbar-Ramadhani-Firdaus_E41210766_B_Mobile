package com.example.sql1;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class MainActivity extends AppCompatActivity {
    EditText editText;
    public int STORAGE_PERMISSION_CODE = 23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText2);
    }
    public void next(View view){
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }
    public  void savePublic(View view){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        String info = editText.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);//folder name
        File myFile = new File(folder, "Data1.txt");//file name
        writeData(myFile, info);
        editText.setText("");
    }
//public void savePublic(View view) {
//    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//        // Jika izin belum diberikan, maka permintaan izin kepada pengguna
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
//    } else {
//        // Jika izin sudah diberikan, maka melakukan aksi yang diinginkan
//        String info = editText.getText().toString();
//        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);//folder name
//        File myFile = new File(folder, "Data1.txt");//file name
//        writeData(myFile, info);
//        editText.setText("");
//    }
//}
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case STORAGE_PERMISSION_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // Izin diberikan, maka melakukan aksi yang diinginkan
//                    String info = editText.getText().toString();
//                    File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);//folder name
//                    File myFile = new File(folder, "Data1.txt");//file name
//                    writeData(myFile, info);
//                    editText.setText("");
//                } else {
//                    // Izin tidak diberikan, maka memberikan peringatan kepada pengguna
//                    Toast.makeText(this, "Izin akses penyimpanan dibutuhkan untuk melakukan aksi ini", Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }
//    }

    public void savePrivate(View view){
        String info = editText.getText().toString();
        File folder = getExternalFilesDir("Akbar");//folder name
        File myFile = new File(folder, "Data2.txt");//file name
        writeData(myFile, info);
        editText.setText("");
    }

    private void writeData(File myFile, String data){
        FileOutputStream fileOutputStream = null;
        try {
            System.out.println("TES");
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
            Toast.makeText(this, "Done"+myFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}