package com.example.topreader;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class PdfViewHolder extends RecyclerView.ViewHolder{

    public final CardView cardView;
    public final TextView textView;

    public PdfViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.holder_cardView);
        textView = itemView.findViewById(R.id.holder_textView);
    }
}
