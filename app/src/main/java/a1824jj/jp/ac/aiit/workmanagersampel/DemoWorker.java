package a1824jj.jp.ac.aiit.workmanagersampel;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class DemoWorker extends Worker {

    final public static String KEY_WORKER = "KEY_WORKER";

    public DemoWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Data data = getInputData();
        int countLimit = data.getInt(MainActivity.KEY_COUNT_VALUE, 0);

        for(int i = 0 ; i < countLimit; i++){
            Log.w("MYTAG", "************ Count is " + i);
        }

        Data dataToSend
                = new Data.Builder()
                .putString(KEY_WORKER, "Task Done Successly!")
                .build();


        return Result.success(dataToSend);
    }
}
