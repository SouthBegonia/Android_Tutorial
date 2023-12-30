package com.example.androidtutorial.Utils;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

public class GoogleUtils {
    public interface GooglePlayFlowListener {
        void OnCompleteListener();

        void OnErrorListener();
    }

    /**
     * 拉起 GooglePlay应用内评价
     *
     * @param context  context
     * @param listener listener
     */
    public static void ShowGooglePlayReview(@NonNull Activity context, GooglePlayFlowListener listener) {
        ReviewManager manager = ReviewManagerFactory.create(context);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ReviewInfo reviewInfo = task.getResult();
                Task<Void> flow = manager.launchReviewFlow(context, reviewInfo);
                flow.addOnCompleteListener(task1 -> {
                    if (listener != null) {
                        listener.OnCompleteListener();
                    }
                });
            } else {
                // There was some problem, log or handle the error code.
                //@ReviewErrorCode int reviewErrorCode = ((TaskException) task.getException()).getErrorCode();
                if (listener != null) {
                    listener.OnErrorListener();
                }
            }
        });
    }
}
