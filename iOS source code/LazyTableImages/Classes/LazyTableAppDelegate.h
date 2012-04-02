/*
     File: LazyTableAppDelegate.h 
 Abstract: Application delegate for the LazyTableImages sample.
 It also downloads in the background the "Top Paid iPhone Apps" RSS feed using NSURLConnection.
  
  Version: 1.2 
  
 Copyright (C) 2010 Apple Inc. All Rights Reserved. 
  
 */

#import <UIKit/UIKit.h>
#import "AppRecord.h"
#import "ParseOperation.h"

@class RootViewController;

@interface LazyTableAppDelegate : NSObject <UIApplicationDelegate, ParseOperationDelegate>
{
    UIWindow				*window;
    UINavigationController	*navigationController;
	
    // this view controller hosts our table of top paid apps
    RootViewController      *rootViewController;
    
    // the list of apps shared with "RootViewController"
    NSMutableArray          *appRecords;
    
    // the queue to run our "ParseOperation"
    NSOperationQueue		*queue;
    
    // RSS feed network connection to the App Store
    NSURLConnection         *appListFeedConnection;
    NSMutableData           *appListData;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet UINavigationController *navigationController;
@property (nonatomic, retain) IBOutlet RootViewController *rootViewController;

@property (nonatomic, retain) NSMutableArray *appRecords;

@property (nonatomic, retain) NSOperationQueue *queue;

@property (nonatomic, retain) NSURLConnection *appListFeedConnection;
@property (nonatomic, retain) NSMutableData *appListData;

@end

