package a1824jj.jp.ac.aiit.workmanagersampel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final public static String KEY_COUNT_VALUE = "KEY_COUNT_VALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Data data
                = new Data.Builder()
                .putInt(KEY_COUNT_VALUE, 1750)
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();

       final OneTimeWorkRequest oneTimeWorkRequest
               = new OneTimeWorkRequest
               .Builder(DemoWorker.class)
               .setConstraints(constraints)
               .setInputData(data)
               .build();

       final TextView textView = findViewById(R.id.textView);



       (findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance(MainActivity.this).enqueue(oneTimeWorkRequest);
            }
       });

       WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.getId())
               .observe(this, new Observer<WorkInfo>() {
                   @Override
                   public void onChanged(WorkInfo workInfo) {
                       if(workInfo != null){
                           textView.setText(workInfo.getState().name());

                           if(workInfo.getState().isFinished()){
                               Data data1 = workInfo.getOutputData();
                               String message = data1.getString(DemoWorker.KEY_WORKER);
                               Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                           }
                       }
                   }
               });
    }
}
