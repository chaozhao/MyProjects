//
//  ShowDestinationsViewController.h
//  CoreDataTabBarTutorial
//
//  Created by Kent Franks on 7/5/11.
//  Copyright 2011 TheAppCodeBlog. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface ShowDestinationsViewController : UIViewController
{
	
    UITableView *destinationsTableView;
	
}

@property (nonatomic, retain) IBOutlet UITableView  *destinationsTableView;

@end
