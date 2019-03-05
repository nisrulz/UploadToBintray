package github.nisrulz.sample.uploadtobintray;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import github.nisrulz.awesomelib.AwesomeLib;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    AwesomeLib.getInstance().makeMeAwesome(this,"Nishant");
  }
}
