package com.example.topreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnPdfSelectedListener {

    public static final String PATH = "path";
    private static final int PERMISSION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();
    }

    private void requestPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            showPdf();
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST);
        }
    }

    private ArrayList<File> getPdfFiles(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        if (files != null) {
            for (File parsed_file : files) {

                if (parsed_file.isDirectory() && !parsed_file.isHidden()) {
                    arrayList.addAll(getPdfFiles(parsed_file));
                } else {
                    if (parsed_file.getName().endsWith(".pdf")) {
                        arrayList.add(parsed_file);
                    }
                }
            }
        }

        return arrayList;
    }

    private void showPdf() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<File> fileList = new ArrayList<>(getPdfFiles(Environment.getExternalStorageDirectory()));
        PdfAdapter pdfAdapter = new PdfAdapter(this, fileList, this);

        recyclerView.setAdapter(pdfAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST) {
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                showPdf();
            } else {
                Toast.makeText(MainActivity.this,
                        "Нет доступа к файлам",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPdfSelected(File file) {
        Intent intent = new Intent(this, DocumentActivity.class);
        intent.putExtra(PATH, file.getAbsolutePath());
        startActivity(intent);
    }
}