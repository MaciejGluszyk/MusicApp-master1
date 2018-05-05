package com.example.stud.musicapp.topsongs;

/**
 * Created by W57020 on 2018-04-26.
 */

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stud.musicapp.api.TrandingSingle;
import com.example.stud.musicapp.api.TrendingList;

import java.util.List;


public class TopSongsAdapter extends RecyclerView.Adapter<TopSongsAdapter.TopSongsViewHolder>
{
    private List<TrandingSingle> trending;
    public TopSongsAdapter(List<TrandingSingle> trending) {
        this.trending = trending;
    }





    @Override
    public TopSongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_top_songs, parent,  false);



        return null;
    }

    @Override
    public void onBindViewHolder(TopSongsViewHolder holder, int position) {
        final TrendingSingle trendingSingle = trending.get(position);

        holder.tvPlace.setText(String.valueOf(trendingSingle.intChartPlace));
        holder.tvTrack.setText(trendingSingle.strTrack);
        holder.tvArtist.setText(trendingSingle.strArtist);
        holder.tvAlbum.setText(trendingSingle.strAlbum);

        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SongDetailsActivity.class);
                Intent.putExtra(SongDetailsActivity.TRACK, trendingSingle.strTrack);
                Intent.putExtra (SongDetailsActivity.ARTIST, trendingSingle.strArtist);
                Intent.putExtra (SongDetailsActivity.TRACK_ID, trendingSingle.idTrack);


                view.getContext().startActivity(intent);

            }
        }){


        }

    }

    @Override
    public int getItemCount() {



        return this.trending != null ? this.trending.size() : 0;
    }

    public class TopSongsViewHolder extends RecyclerView.ViewHolder


    {
        TextView tvPlace;
        TextView tvTrack;
        TextView tvArtist;
        TextView tvAlbum;

        public TopSongsViewHolder(View itemView) {
            super(itemView);
            llContainers = itemView.FindViewById(R.id.llContainers);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvTrack = itemView.findViewById(R.id.tvTrack);
            tvArtist = itemView.findViewById(R.id.tvArtisst);
            tvAlbum = itemView.findViewById(R.id.tvAlbum);
        }
    }



}
