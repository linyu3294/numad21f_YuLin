package com.gremlinweekend.numad21s_yulin.linkActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gremlinweekend.numad21s_yulin.R;

import java.util.ArrayList;
import java.util.List;

public class RviewAdapter extends RecyclerView.Adapter<RviewHolder> {
    List<ILink> links = new ArrayList<ILink>();
    private ILink listener;

    public RviewAdapter(List<ILink> links) {
        this.links = links;
    }

    public void setOnItemClickListener(ILink listener) {
        this.listener = listener;
    }


    public RviewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_link, parent, false);
        return new RviewHolder(view, this.listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RviewHolder holder, int position) {
       LinkItem currentLink =  (LinkItem) links.get(position);

        holder.hIcon.setImageResource(currentLink.getLinkImage());
        holder.hURL.setText(currentLink.getLinkURL());
    }

    @Override
    public int getItemCount() {
        return this.links.size();
    }

}
