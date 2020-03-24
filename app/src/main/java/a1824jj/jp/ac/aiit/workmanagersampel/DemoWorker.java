package a1824jj.jp.ac.aiit.workmanagersampel;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class DemoWorker extends Worker {

    public DemoWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        for(int i = 0 ; i < 100000; i++){
            Log.w("MYTAG", "************ Count is " + i);
        }


        return Result.success();
    }
}
