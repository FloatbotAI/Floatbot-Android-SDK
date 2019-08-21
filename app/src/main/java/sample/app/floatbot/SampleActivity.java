package sample.app.floatbot;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import app.floatbot.FloatbotFragment;
import app.floatbot.FloatbotUtils;

public class SampleActivity extends AppCompatActivity implements FloatbotFragment.OnFloatbotInteractionListener{

    FloatbotFragment flb;
    FrameLayout addView;

    @Override
    public void onChatWindowClosed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        addView = (FrameLayout) findViewById(R.id.addView);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        flb =  new FloatbotFragment(this);

        Bundle bundle = new Bundle();
        bundle.putString("bot_id","5cc00545e6293668180a5d12");
        bundle.putString("key","U9vuk4FWthYO8ygE25nRqApbL1TSHx");
        bundle.putBoolean("showHeader",true);
        //bundle.putString("push_token",<FCM TOKEN>);
        //bundle.putString("data",<AES-CBC-256 Encrypted Json String>);

        flb.setArguments(bundle);
        transaction.replace(R.id.addView,flb);
        transaction.commit();

        //To show list of session to users in chatbot uncomment below function
        //flb.showSessions();

        /*Get floatbot user id from SDK*/
        //FloatbotUtils.getFloatbotUserID(this);

        /*Set Customized view for loading screen*/
        //FloatbotUtils.setLoadingView(this,R.layout.connecting);

        /* Set customized view for no internet connection*/
        //FloatbotUtils.setNoInternetConnectionView(this,R.layout.nointernet);

        /* To clear floatbot user data from app */
        //FloatbotUtils.clearUserData(this);

        /* To update token in floatbot, call below function with new push token */
        //FloatbotUtils.updatePushToken("<FCM TOKEN>");

    }
}
