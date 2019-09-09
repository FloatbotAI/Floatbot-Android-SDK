To integrate floatbot sdk in android app first download floatbot aar file from [here](https://floatbot.ai/android-sdk/floatbotapp-1.3.aar)

Sdk has only 1 fragment which can be placed any activity

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
 
 BOT-ID : Your floatbot bot id
 BOT-TOKEN : You will get bot token from Floatbot Dashboard -> Settings -> Android Tab
 
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




Please let us know if you encounter any problems at **support@floatbot.ai**
