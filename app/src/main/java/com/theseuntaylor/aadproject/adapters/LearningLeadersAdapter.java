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
import com.theseuntaylor.aadproject.model.LearningLeadersResponse;

import java.util.List;

public class LearningLeadersAdapter extends RecyclerView.Adapter<LearningLeadersAdapter.ViewHolder>{

    private final Context mContext;
    private List<LearningLeadersResponse> mList;


    public LearningLeadersAdapter(Context context, List<LearningLeadersResponse> learningLeadersList) {
        this.mContext = context;
        this.mList = learningLeadersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listView = LayoutInflater.from(mContext).inflate(R.layout.list_model, parent, false);
        return new LearningLeadersAdapter.ViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = mList.get(position).getName();
        String hours = String.valueOf(mList.get(position).getHours());
        String country = mList.get(position).getCountry();
        String badgeUrl = mList.get(position).getBadgeUrl();

        String subtitle = hours + " learning hours, " + country;

        holder.learningLeadersNames.setText(name);
        holder.learningLeadersScores.setText(subtitle);

        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.downloader(new OkHttp3Downloader(mContext));
        builder.build().load(badgeUrl).placeholder(R.drawable.loading)
                .error(R.drawable.loading).into(holder.learningLeadersImages);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView learningLeadersImages;
        public TextView learningLeadersNames, learningLeadersScores;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            learningLeadersImages = itemView.findViewById(R.id.leadersImage);
            learningLeadersNames = itemView.findViewById(R.id.leadersNames);
            learningLeadersScores = itemView.findViewById(R.id.scoreTextView);
        }

        public ImageView getLearningLeadersImages() {
            return learningLeadersImages;
        }

        public TextView getLearningLeadersNames() {
            return learningLeadersNames;
        }

        public TextView getLearningLeadersScores() {
            return learningLeadersScores;
        }
    }
}
