package com.theseuntaylor.aadproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.theseuntaylor.aadproject.R;
import com.theseuntaylor.aadproject.adapters.SkillIQAdapter;
import com.theseuntaylor.aadproject.model.SkillIQResponse;
import com.theseuntaylor.aadproject.retrofit.ApiClient;
import com.theseuntaylor.aadproject.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class SkillIqLeadersFragment extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
//    private List userInfo;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View skillIqView = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false);

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        Call<List<SkillIQResponse>> call = service.getSkillIQLeaders();
        call.enqueue(new Callback<List<SkillIQResponse>>() {
            @Override
            public void onResponse(Call<List<SkillIQResponse>> call, Response<List<SkillIQResponse>> response) {
                generateSkillIQLeadersList(skillIqView, response.body());
            }

            @Override
            public void onFailure(Call<List<SkillIQResponse>> call, Throwable t) {
                Toast.makeText(skillIqView.getContext(), R.string.failed_message, Toast.LENGTH_LONG).show();
            }
        });

        return skillIqView;
    }

    private void generateSkillIQLeadersList(View view, List<SkillIQResponse> skillIQResponseList){

        RecyclerView recyclerView = view.findViewById(R.id.skill_iq_recycler_view);
        mAdapter = new SkillIQAdapter(view.getContext(), skillIQResponseList);
        mLayoutManager = new LinearLayoutManager(view.getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

}
