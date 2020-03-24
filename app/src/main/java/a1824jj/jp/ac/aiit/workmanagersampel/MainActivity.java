package a1824jj.jp.ac.aiit.workmanagersampel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final OneTimeWorkRequest oneTimeWorkRequest
               = new OneTimeWorkRequest.Builder(DemoWorker.class).build();

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
                       }
                   }
               });
    }
}
