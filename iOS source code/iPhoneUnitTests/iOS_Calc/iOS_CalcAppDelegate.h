
#import <UIKit/UIKit.h>

@class CalcViewController;

@interface CalcAppDelegate : NSObject <UIApplicationDelegate> {
   UIWindow           *window;
   CalcViewController *calcViewController;
}

@property (nonatomic, retain) IBOutlet UIWindow  *window;
@property (nonatomic, retain) CalcViewController *calcViewController;

@end

