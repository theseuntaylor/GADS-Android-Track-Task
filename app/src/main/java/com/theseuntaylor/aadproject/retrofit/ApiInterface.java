package com.theseuntaylor.aadproject.retrofit;

import com.theseuntaylor.aadproject.model.LearningLeadersResponse;
import com.theseuntaylor.aadproject.model.SkillIQResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface ApiInterface {

    @GET("api/skilliq")
    Call<List<SkillIQResponse>> getSkillIQLeaders();

    @GET("api/hours")
    Call<List<LearningLeadersResponse>> getLearningLeaders();

}
