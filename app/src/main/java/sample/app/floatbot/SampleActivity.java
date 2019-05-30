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
//      bundle.putString("push_token",<FCM TOKEN>);
//      bundle.putString("data",<AES-CBC-256 Encrypted Json String>);

        flb.setArguments(bundle);
        transaction.replace(R.id.addView,flb);
        transaction.commit();
//        flb.showSessions();
//      FloatbotUtils.setLoadingView(this,R.layout.connecting);
//      FloatbotUtils.setNoInternetConnectionView(this,R.layout.nointernet);
//      FloatbotUtils.clearUserData(this);
//      FloatbotUtils.updatePushToken("<FCM TOKEN>");
    }
}
