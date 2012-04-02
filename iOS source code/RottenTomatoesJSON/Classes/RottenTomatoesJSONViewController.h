//
//  RottenTomatoesJSONViewController.h
//  RottenTomatoesJSON
//
//  Created by Sylvain Marcotte on 11-07-05.
//  Copyright 2011 14 Oranges. All rights reserved.
//

#import <UIKit/UIKit.h>

extern NSString* const RT_API_KEY;

@interface RottenTomatoesJSONViewController : UIViewController 
{
	IBOutlet UITextField *searchField;
	IBOutlet UITextField *yearField;
	IBOutlet UILabel *criticsScoreLabel;
	IBOutlet UILabel *audienceScoreLabel;
	IBOutlet UITextView *resultTextView;
	IBOutlet UIButton *searchButton;
	
	NSString *movieReviewsURL;
}

-(IBAction)onSearch:(id)sender;
-(IBAction)dismissKeyboard:(id)sender;

@end

