//
//  SearchDestinationsViewController.h
//  CoreDataTabBarTutorial
//
//  Created by Kent Franks on 7/5/11.
//  Copyright 2011 TheAppCodeBlog. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface SearchDestinationsViewController : UIViewController
{
	
    UISearchBar *destinationSearchBar;
	
}

@property (nonatomic, retain) IBOutlet UISearchBar *destinationSearchBar;

@end
