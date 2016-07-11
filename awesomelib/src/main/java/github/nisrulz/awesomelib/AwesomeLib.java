package github.nisrulz.awesomelib;

import android.content.Context;
import android.widget.Toast;

public class AwesomeLib {
  private static AwesomeLib ourInstance = new AwesomeLib();

  public static AwesomeLib getInstance() {
    return ourInstance;
  }

  private AwesomeLib() {
  }

  public void makeMeAwesome(Context context, String data) {
    Toast.makeText(context, "Awesome " + data, Toast.LENGTH_LONG).show();
  }
}