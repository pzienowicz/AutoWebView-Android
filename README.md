##AutoWebView for TVs or other display devices

So you bought new enormous TV and connected Raspberry PI (or other stuff with Android) to it... Now you wish to display some website or information panel, don't you? Here is a ready-to-use tool for displaying every website you wish.

**Features**

1. Authentication,
* Defined auto refresh,
* Autostart after system rebooting,
* Ability to scroll page after loading,
* JS support.

**Usage flow**

1. Fork project,
* Set your data in config file,
* Build apk file,
* Run it on Android 4.0 or higher.

**Configuration**

Project is prepared in Android Studio.
All you have to do is to set your own parameters in build.gradle file.

```java
  buildConfigField "String", "URL", '"http://google.com"'         //url
  buildConfigField "boolean", "USE_AUTH", 'false'                 //enables authentication
  buildConfigField "String", "HOST", '"example.com"'              //only for auth
  buildConfigField "String", "USERNAME", '"username"'             //only for auth
  buildConfigField "String", "PASSWORD", '"password"'             //only for auth
  buildConfigField "boolean", "ENABLE_JS", 'true'                 //enables js in webview
  buildConfigField "boolean", "ALLOW_FILE_ACCESS", 'true'         //allows file access in webview
  buildConfigField "boolean", "ENABLE_DEBUG_MODE", 'true'         //only for KitKat and higher
  buildConfigField "long", "REFRESH_DELAY", '60000'               //first reload (in ms)
  buildConfigField "long", "REFRESH_PERIOD", '60000'              //next reloads (in ms)
  buildConfigField "int", "SCROLL_X", '200'                       //the amount of pixels to scroll by horizontally
  buildConfigField "int", "SCROLL_Y", '0'                         //the amount of pixels to scroll by vertically

  resValue "string", "app_name", "AutoWebView"                    //app name
```

Enjoy!



