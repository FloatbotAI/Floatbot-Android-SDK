To integrate floatbot sdk in android app first download floatbot aar file from [here](https://floatbot.ai/android-sdk/floatbotapp-3.2.aar)

You need to handle Runtime permission for

Sdk has only 1 fragment which can be placed in any activity

Your Activity must implement OnFloatbotInteractionListener interface and override its method onChatWindowClosed

~~~java
public class SampleActivity extends AppCompatActivity implements FloatbotFragment.OnFloatbotInteractionListener{
  ....
  //Called when user click on closebutton from chatwindow
  @Override
  public void onChatWindowClosed() {
    finish();
  }
} 
~~~

To initialize SDK, 
We need to pass below paramaters in fragment

~~~java

FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        flb =  new FloatbotFragment(this);
Bundle bundle = new Bundle();
bundle.putString("bot_id","BOT-ID");
bundle.putString("key","BOT-TOKEN");
~~~

To get your key and bot_id,

Login to https://floatbot.ai/console <h6>Note: Please change URL accordingly, if your setup is on-premise or on private cloud,
Example: **https://domain/console** 
</h6>

Once you are logged in please click on **Bot API Keys** under **Developers** Section

Select **Bot ID** and **Secret Key** against your Bot Name and Key Name **Android security token**
 
To hide header from chat window, simply pass showHeader value as false. By default value will be true

~~~java
bundle.putBoolean("showHeader",false);
~~~
To show list of sessions to user. You can call showSessions()  method of fragment class
~~~java
flb.showSessions();
~~~
To use login architecture in your app. Create a json string and pass encrypted data in bundle 
Encryption method - 'AES CBC 256'
~~~java
bundle.putString("data",AES-CBC-256 Encrypted Json String);
~~~

You can set attributes by encrypting them in json string (data bundle)
Or
To set attributes directly from SDK
~~~java
FloatbotAttributes attributes = new FloatbotAttributes();
try {
    attributes.addAttributes("name","user-name");
    bundle.putString("attributes",attributes.value());
} catch (Exception e) {

}
~~~

To register for push notification, you need to pass gcm/fcm token in bundle as below
~~~java
bundle.putString("push_token","GCM/FCM TOKEN");
~~~
You can also update fcm/gcm token later by calling below function
~~~java
FloatbotUtils.updatePushToken("GCM/FCM TOKEN"); 
~~~
For push notification, you will need to handle json string in onMessageReceived 

~~~java
@Override
public void onMessageReceived(RemoteMessage message) {
      Log.e("fcm-message",message.getData().toString());
     //Create notification	
}
~~~

You can also set your own loading screen/no connection (layout), which will be shown when chatbot is loading or when their is no internet access
~~~java
FloatbotUtils.setLoadingView(this,R.layout.connecting);
FloatbotUtils.setNoInternetConnectionView(this,R.layout.nointernet);
~~~

**If you have enabled mic in chatbot. You need to handle Mircophone permission in activity before loading fragment**
 
**Currently as of now attachment item is not supported in android floatbot sdk**

To Delete user/Clear/Logout from  Floatbot app
~~~java
FloatbotUtils.clearUserData(this);
~~~


From Sdk version 2.2 You can handle callback url in android app
Just implement onCallback() method from OnFloatbotInteractionListener interface in your main activity

~~~java
 @Override
    public void onCallback(Uri uri) {
        Log.e("callback",uri.toString());
        //finish();
    }
~~~

From SDK version 2.3 , you can pass one more parameter in bundle
It will create new session everytime when value is set to true (Default value is false)
~~~java
    bundle.putBoolean("create_new_session",true);
~~~

From SDK version 3.2 , you can set/change chatbot domain in SDK
It is used to point to that domain [use this settings for on-premise or private cloud setup or public cloud with different region]
(Default value is https://floatbot.ai)
~~~java
    bundle.putBoolean("domain","https://floatbot.ai");
~~~

Please let us know if you encounter any problems at **connect@floatbot.ai**