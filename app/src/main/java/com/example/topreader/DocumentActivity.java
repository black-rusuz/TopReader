package com.example.topreader;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class DocumentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);

        PDFView pdfView = findViewById(R.id.pdfView);
        String path = getIntent().getStringExtra(MainActivity.PATH);

        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        pdfView.fromUri(uri).load();
    }
}