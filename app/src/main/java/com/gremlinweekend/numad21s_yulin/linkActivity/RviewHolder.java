package com.gremlinweekend.numad21s_yulin.linkActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gremlinweekend.numad21s_yulin.R;

public class RviewHolder extends RecyclerView.ViewHolder {

    public ImageView hIcon;
    public TextView hURL;
    public Button goButton;

    public RviewHolder(@NonNull View linkView, final ILink link) {
        super(linkView);

        hIcon = linkView.findViewById(R.id.img_icon);
        hURL = linkView.findViewById(R.id.text_url);
        goButton = linkView.findViewById(R.id.button_go);

        linkView.setOnClickListener(new View.OnClickListener() {
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



        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (link != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        String newURL = hURL.getText().toString();
                        System.out.println(newURL);
                        link.setLinkURL(position, newURL);
                        link.onclickNavigate(position);
                    }
                }
            }
        });
    }


}
