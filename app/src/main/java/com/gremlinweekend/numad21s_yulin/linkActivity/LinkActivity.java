package com.gremlinweekend.numad21s_yulin.linkActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gremlinweekend.numad21s_yulin.FirstFragment;
import com.gremlinweekend.numad21s_yulin.R;
import java.util.ArrayList;

public class LinkActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private FloatingActionButton addButton;
    private ArrayList<ILink> links = new ArrayList<>();

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);


        init(savedInstanceState);

        addButton = findViewById(R.id.button_add_link);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = 0;
                addItem(pos);
            }
        });

        //Specify what action a specific gesture performs, in this case swiping right or left deletes the entry
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(LinkActivity.this, "Delete an item", Toast.LENGTH_SHORT).show();
                int position = viewHolder.getLayoutPosition();
                links.remove(position);
                rviewAdapter.notifyItemRemoved(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    private void init(Bundle savedInstanceState) {
        initialItemData(savedInstanceState);
        createRecyclerView();
    }


    private void initialItemData(Bundle savedInstanceState) {

        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (links == null || links.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    Integer linkImage = savedInstanceState.getInt(KEY_OF_INSTANCE + i + "0");
                    String linkURL = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");
//                    String itemDesc = savedInstanceState.getString(KEY_OF_INSTANCE + i + "2");
                    boolean isModifying = savedInstanceState.getBoolean(KEY_OF_INSTANCE + i + "3");

                    // We need to make sure names such as "XXX(checked)" will not duplicate
                    // Use a tricky way to solve this problem, not the best though
                    if (isModifying) {
                        linkURL = linkURL.substring(0, linkURL.lastIndexOf("("));
                    }
                    LinkItem link = new LinkItem(linkImage, linkURL, isModifying);
                    links.add(link);
                }
            }
        }
        // The first time to opne this Activity
        else {
            LinkItem link1 = new LinkItem(R.drawable.pic_gmail_01, "https://www.gmail.com", false);
            LinkItem link2 = new LinkItem(R.drawable.pic_google_01, "https://www.google.com",  false);
            LinkItem link3 = new LinkItem(R.drawable.pic_youtube_01, "https://www.youtube.com",  false);
//            links.add(link1);
//            links.add(link2);
//            links.add(link3);
        }

    }

    private void createRecyclerView() {

        rLayoutManger = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        rviewAdapter = new RviewAdapter(links);

        ILink itemClickListener = new ILink() {
            @Override
            public void onLinkClick(int position) {
                //attributions bond to the item has been changed
                links.get(position).onLinkClick(position);
                rviewAdapter.notifyItemChanged(position);
            }

            @Override
            public String onclickNavigate(int position) {
                rviewAdapter.notifyItemChanged(position);
                String urlString = links.get(position).onclickNavigate(position);
                openBrowser(urlString);
                return urlString;
            }

            @Override
            public String setLinkURL(int position, String linkURL) {
                return links.get(position).setLinkURL(position, linkURL);
            }
        };
        rviewAdapter.setOnItemClickListener(itemClickListener);

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);


    }


    private void addItem(int position) {
        links.add(position, new LinkItem(R.drawable.empty, "https://", false));
        Toast.makeText(LinkActivity.this, "Add an item", Toast.LENGTH_SHORT).show();
        rviewAdapter.notifyItemInserted(position);
    }

    private void openBrowser(String urlString){
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("urlString", urlString);
        if (urlString.contains("https://")) {
            startActivity(intent);
        }else{
            String notifyUseHTTPS = "Please include https:// in url.";
        }
        System.out.println(urlString);
    }
}