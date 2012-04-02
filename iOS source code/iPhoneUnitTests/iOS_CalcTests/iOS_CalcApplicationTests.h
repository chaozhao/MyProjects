

#import <SenTestingKit/SenTestingKit.h>

#import <UIKit/UIKit.h>

// Test-subject headers.
#import "iOS_CalcAppDelegate.h"
#import "iOS_CalcViewController.h"


@interface CalcApplicationTests : SenTestCase 
{
@private
   CalcAppDelegate    *app_delegate;
   CalcViewController *calc_view_controller;
   UIView             *calc_view;
    
}

@end
