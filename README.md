# Android_Tutorial
Android测试程序

## 通用相关

### 获取包名
```java {.line-numbers}
    /**
     * 获取 包名
     *
     * @param activity Activity
     * @return packageName
     */
    public static String GetPackageName(Activity activity) {
        try {
            return Objects.requireNonNull(activity.getClass().getPackage()).getName();
        } catch (Exception e) {
            LogHelper.LogError(String.format("GetPackageName    failed ===> error = %s", e));
            return "";
        }
    }
```

### 跳转到应用商店
```java {.line-numbers}
    /**
     * 跳转到 目标应用商店中 目标App页面
     *
     * @param activity      activity
     * @param applicationID 目标App的ApplicationID标识
     * @param storePkg      应用商店Pkg标识
     */
    public static void JumpToAppStore(Activity activity, String applicationID, String storePkg) {
        try {
            if (TextUtils.isEmpty(applicationID))
                throw new Exception("applicationID is empty.");
            if (TextUtils.isEmpty(storePkg))
                throw new Exception("storePkg is empty.");

            Uri uri = Uri.parse("market://details?id=" + applicationID);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage(storePkg);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            activity.startActivity(intent);

        } catch (Exception e) {
            LogHelper.LogError(String.format("JumpToAppStore    failed ===> applicationID = %s, storePkg = %s, error = %s", applicationID, storePkg, e));
        }
    }
```


## Google相关

### 应用内评价
```java {.line-numbers}
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
```