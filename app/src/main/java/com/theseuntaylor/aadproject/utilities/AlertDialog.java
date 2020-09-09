package com.theseuntaylor.aadproject.utilities;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import com.theseuntaylor.aadproject.R;
import com.theseuntaylor.aadproject.retrofit.SubmitEntryClient;
import com.theseuntaylor.aadproject.retrofit.SubmitEntryInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlertDialog extends DialogFragment {

    private String TAG = "Submit Project Activity";

    public Dialog CreateDialog(final Activity activity, String message, String title, final String firstName, final String lastName, final String email, final String githubLink) {

        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(activity);
        alertDialog.setMessage(message).setCancelable(false).setTitle(title)//"SUBMIT PROJECT!""Are you sure?"
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Network call
                        MakeNetworkCall(activity, firstName, lastName, email, githubLink);

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return  alertDialog.create();
    }

    private void MakeNetworkCall(final Activity activity, String firstName, String lastName, String email, String githubLink) {
        SubmitEntryInterface service = SubmitEntryClient.getClient().create(SubmitEntryInterface.class);
        Call<Void> call = service.submitEntry(firstName, lastName, email, githubLink);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                try {

                    Log.i(TAG, response.toString());
                    Log.i("Response Code", String.valueOf(response.code()));

                    if (response.code() == 200){
                        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(activity);

                        alertDialog.setMessage("Your submission has been completed, thank you")
                                .setCancelable(false).setTitle("Successful Submission")//"SUBMIT PROJECT!""Are you sure?"
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                        alertDialog.create().show();
                    }
                } catch (Exception exception) {

                    androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(activity);
                    alertDialog.setMessage("Your submission could not be completed, please try again later")
                            .setCancelable(false).setTitle("Error with Submission")//"SUBMIT PROJECT!""Are you sure?"
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alertDialog.create().show();

                    exception.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(activity);
                alertDialog.setMessage("Your submission attempt failed, please check for internet and try again!")
                        .setCancelable(false).setTitle("Failed Submission")//"SUBMIT PROJECT!""Are you sure?"
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                alertDialog.create().show();
            }
        });
    }

}
