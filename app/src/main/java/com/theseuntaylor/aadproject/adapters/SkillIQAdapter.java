package com.theseuntaylor.aadproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.theseuntaylor.aadproject.R;
import com.theseuntaylor.aadproject.model.SkillIQResponse;

import java.util.List;

public class SkillIQAdapter extends RecyclerView.Adapter<SkillIQAdapter.ViewHolder> {

    private final Context mContext;
    private final List<SkillIQResponse> mItemsList;

    public SkillIQAdapter(Context context, List itemsList){
        mContext = context;
        mItemsList = itemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listView = LayoutInflater.from(mContext).inflate(R.layout.list_model, parent, false);
        return new ViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String name = mItemsList.get(position).getName();
        String iqScore = String.valueOf(mItemsList.get(position).getScore());
        String country = mItemsList.get(position).getCountry();
        String thumbnailUrl = mItemsList.get(position).getBadgeUrl();

        String subtitle = iqScore + " Skill IQ Score, " + country;

        holder.iqLeadersNames.setText(name);
        holder.iqLeadersScores.setText(subtitle);

        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.downloader(new OkHttp3Downloader(mContext));
        builder.build().load(thumbnailUrl).placeholder(R.drawable.loading)
                .error(R.drawable.loading).into(holder.iqLeadersImages);
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iqLeadersImages;
        public TextView iqLeadersNames, iqLeadersScores;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iqLeadersImages = itemView.findViewById(R.id.leadersImage);
            iqLeadersNames = itemView.findViewById(R.id.leadersNames);
            iqLeadersScores = itemView.findViewById(R.id.scoreTextView);
        }

        public TextView getIqLeadersNames(){
            return iqLeadersNames;
        }

        public TextView getIqLeadersScores(){
            return iqLeadersScores;
        }

        public ImageView getIqLeadersImages(){
            return iqLeadersImages;
        }
    }
}
