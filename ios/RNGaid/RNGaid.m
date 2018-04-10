//
//  RNGaid.m
//  RNGaid
//
//  Created by Marius Reimer on 12/18/17.
//  Copyright © 2017 Marius Reimer. All rights reserved.
//

#import "RNGaid.h"

@implementation RNGaid

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(getAdvertisingInfo: (RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    @try {
        NSUUID *adId = [[ASIdentifierManager sharedManager] advertisingIdentifier];
        NSString *str = [adId UUIDString];
        NSDictionary *ret = @{
                              @"advertisingId": str,
                              @"isLimitAdTrackingEnabled": @([[ASIdentifierManager sharedManager] isAdvertisingTrackingEnabled])
                              };
        resolve(ret);
    } @catch (NSException *exception) {
        reject(@"", @"", nil);
    }
}
@end
