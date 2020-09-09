package com.theseuntaylor.aadproject.fragments;

import android.os.Bundle;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.theseuntaylor.aadproject.R;
import com.theseuntaylor.aadproject.adapters.LearningLeadersAdapter;
import com.theseuntaylor.aadproject.model.LearningLeadersResponse;
import com.theseuntaylor.aadproject.retrofit.ApiClient;
import com.theseuntaylor.aadproject.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class LearningLeadersFragment extends Fragment {

    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View learningLeadersView = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        ApiInterface client = ApiClient.getClient().create(ApiInterface.class);

        Call<List<LearningLeadersResponse>> call = client.getLearningLeaders();
        call.enqueue(new Callback<List<LearningLeadersResponse>>() {
            @Override
            public void onResponse(Call<List<LearningLeadersResponse>> call, Response<List<LearningLeadersResponse>> response) {
                generateLearningLeadersList(learningLeadersView, response.body());
            }

            @Override
            public void onFailure(Call<List<LearningLeadersResponse>> call, Throwable t) {
                Toast.makeText(learningLeadersView.getContext(), R.string.failed_message, Toast.LENGTH_LONG).show();
            }
        });

        return learningLeadersView;
    }

    private void generateLearningLeadersList(View view, List<LearningLeadersResponse> learningLeadersList){

        recyclerView = view.findViewById(R.id.leading_leaders_recycler_view);
        mAdapter = new LearningLeadersAdapter(view.getContext(), learningLeadersList);
        layoutManager = new LinearLayoutManager(view.getContext());

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

}
