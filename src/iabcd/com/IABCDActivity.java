package iabcd.com;



import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class IABCDActivity extends Activity 
{
    /** Called when the activity is first created. */
	private static final String TAG = IABCDActivity.class.getSimpleName();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(new Tela(this));
    }
}