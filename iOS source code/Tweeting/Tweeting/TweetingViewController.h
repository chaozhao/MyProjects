/*
 File: TweetingViewController.h
 Abstract: This view controller allows the user to send a tweet by using the built-in composition sheet or by using a custom post request.
 The user can additionally get the current Twitter public timeline.
 Version: 1.0
 Copyright (C) 2011 Apple Inc. All Rights Reserved.
 
 */

#import <UIKit/UIKit.h>
#import <Twitter/Twitter.h>
#import <Accounts/Accounts.h>

@interface TweetingViewController : UIViewController

@property (strong, nonatomic) IBOutlet UIButton *easyTweetButton;
@property (strong, nonatomic) IBOutlet UIButton *customTweetButton;
@property (strong, nonatomic) IBOutlet UITextView *outputTextView;

- (IBAction)sendEasyTweet:(id)sender;
- (IBAction)sendCustomTweet:(id)sender;
- (IBAction)getPublicTimeline:(id)sender;
- (void)displayText:(NSString *)text;
- (void)canTweetStatus;

@end
