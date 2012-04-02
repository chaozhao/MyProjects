//
//  RottenTomatoesJSONAppDelegate.h
//  RottenTomatoesJSON
//
//  Created by Sylvain Marcotte on 11-07-05.
//  Copyright 2011 14 Oranges. All rights reserved.
//

#import <UIKit/UIKit.h>

@class RottenTomatoesJSONViewController;

@interface RottenTomatoesJSONAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    RottenTomatoesJSONViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet RottenTomatoesJSONViewController *viewController;

@end

