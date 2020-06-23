package com.example.simpleplayer;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {

    private ArrayList<Songs> songs;
    public Context context;
    private static final int FOOTER_VIEW = 1;

    public SongsAdapter(ArrayList<Songs> songs) {
        this.songs=songs;
    }

   // we can create footer and normal view holder to define what to do in each case but
   // in this case we will make return type Recycle.ViewHolder Instead of ViewHolder
    // but i didn't need that in this case as i want only change appearance and doesn't behave different
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == FOOTER_VIEW) {
            View footerViewHolder = inflater.inflate(R.layout.load_more_bar, parent, false);
            return new ViewHolder(footerViewHolder);
        }
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.recycler_list_view_row, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        // Get the data model based on position
        Songs song = songs.get(position);

        //Set image
        ImageView songThumbnail=holder.songThumbnails;
        Picasso.get().load(song.getSongThumbnail().getUrl()).placeholder(R.drawable.ic_broken_image_black_24dp).into(songThumbnail);

        // Set item views based on your views and data model
        TextView songTitle = holder.songTitle;
        songTitle.setText(song.getTitle());

        ImageButton download = holder.downloadButton;
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Download Song at position"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView     songTitle;
        public ImageButton  downloadButton;
        public ImageView    songThumbnails;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            songThumbnails=itemView.findViewById(R.id.song_thumbnail);
            songTitle =  itemView.findViewById(R.id.song_title);
            downloadButton = itemView.findViewById(R.id.song_download);
        }
    }
}
