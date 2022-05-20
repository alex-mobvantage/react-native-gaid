# react-native-gaid

Gets the Google Advertising ID for [React Native](https://github.com/facebook/react-native)

## Install

```shell
npm install --save react-native-gaid
```

## Automatically link

#### With React Native 0.27+

```shell
react-native link react-native-gaid
```

#### With older versions of React Native

You need [`rnpm`](https://github.com/rnpm/rnpm) (`npm install -g rnpm`)

```shell
rnpm link react-native-gaid
```

## Manually link

- in `android/app/build.gradle`:

```diff
dependencies {
    ...
    implementation "com.facebook.react:react-native:+"  // From node_modules
+   implementation project(':react-native-gaid')
}
```

- in `android/settings.gradle`:

```diff
...
include ':app'
+ include ':react-native-gaid'
+ project(':react-native-gaid').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-gaid/android')
```

#### With React Native 0.29+

- in `MainApplication.java`:

```diff
+ import com.mobvantage.react.gaid.RNGaidPackage;

  public class MainApplication extends Application implements ReactApplication {
    //......

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
+         new RNGaidPackage(),
          new MainReactPackage()
      );
    }

    ......
  }
```

#### With older versions of React Native:

- in `MainActivity.java`:

```diff
+ import com.mobvantage.react.gaid.RNGaidPackage;

  public class MainActivity extends ReactActivity {
    ......

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
+       new RNGaidPackage(),
        new MainReactPackage()
      );
    }
  }
```

## Example

```js
import GAID from 'react-native-gaid';

GAID.getAdvertisingInfo().then(info => {
  console.log('Google Advertising ID', info.advertisingId);
  console.log('Is Limit Ad Tracking Enabled', info.isLimitAdTrackingEnabled);
})
.catch(err => {
  console.log('Something went wrong', err);
});

```