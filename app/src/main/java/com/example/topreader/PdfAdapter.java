package com.example.topreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class PdfAdapter extends RecyclerView.Adapter<PdfViewHolder> {

    private final Context context;
    private final List<File> fileList;
    private final OnPdfSelectedListener onPdfSelectedListener;

    public PdfAdapter(Context context, List<File> fileList, OnPdfSelectedListener listener) {
        this.context = context;
        this.fileList = fileList;
        this.onPdfSelectedListener = listener;
    }

    @NonNull
    @Override
    public PdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PdfViewHolder(LayoutInflater.from(context).inflate(
                R.layout.layout_holder,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull PdfViewHolder holder, int position) {
        String string = fileList.get(position).getName();
        string = string.substring(0, string.lastIndexOf('.'));

        holder.textView.setText(string);
        holder.textView.setSelected(true);

        holder.cardView.setOnClickListener(view ->
                onPdfSelectedListener.onPdfSelected(fileList.get(position)));
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }
}
