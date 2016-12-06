package com.mobvantage.react.gaid;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;

public class RNGaidModule extends ReactContextBaseJavaModule {

  public RNGaidModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "RNGaid";
  }

  @ReactMethod
  public void getAdvertisingInfo(Promise result){
    ReactApplicationContext context = getReactApplicationContext();
    context.runOnNativeModulesQueueThread(new GetGAIDThread(context, result));
  }

  class GetGAIDThread implements Runnable {
    ReactApplicationContext context;
    Promise result;
    
    public GetGAIDThread(ReactApplicationContext context, Promise result) {
      this.context = context;
      this.result = result;
    }
    
    @Override
    public void run() {
      try {
        Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
        
        WritableMap map = Arguments.createMap();
        map.putString("advertisingId", adInfo.getId());
        map.putBoolean("isLimitAdTrackingEnabled", adInfo.isLimitAdTrackingEnabled());
        result.resolve(map);
      } catch (IOException e) {
        // Unrecoverable error connecting to Google Play services (e.g.,
        // the old version of the service doesn't support getting AdvertisingId).
        result.reject(e);
      } catch (GooglePlayServicesNotAvailableException e) {
        // Google Play services is not available entirely.
        result.reject(e);
      } catch (GooglePlayServicesRepairableException e) {
        result.reject(e);
      } catch (NullPointerException e) {
        result.reject(e);
      }
    }
  }
}