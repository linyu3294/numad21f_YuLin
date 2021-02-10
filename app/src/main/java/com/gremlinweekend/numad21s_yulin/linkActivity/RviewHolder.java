package com.gremlinweekend.numad21s_yulin.linkActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gremlinweekend.numad21s_yulin.R;

public class RviewHolder extends RecyclerView.ViewHolder {

    public ImageView hIcon;
    public TextView hURL;
    public Button sButton;

    public RviewHolder(@NonNull View itemView, final ILink link) {
        super(itemView);

        hIcon = itemView.findViewById(R.id.img_icon);
        hURL = itemView.findViewById(R.id.text_url);
        sButton = itemView.findViewById(R.id.button_save);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (link != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        link.onLinkClick(position);
                    }
                }
            }
        });

        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (link != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        link.onlinkSaveClick(position);
                    }
                }
            }
        });
    }


}
