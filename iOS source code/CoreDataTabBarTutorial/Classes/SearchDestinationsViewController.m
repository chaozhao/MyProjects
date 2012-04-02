    //
//  SearchDestinationsViewController.m
//  CoreDataTabBarTutorial
//
//  Created by Kent Franks on 7/5/11.
//  Copyright 2011 TheAppCodeBlog. All rights reserved.
//

#import "SearchDestinationsViewController.h"


@implementation SearchDestinationsViewController

@synthesize destinationSearchBar;

- (void)viewDidLoad 
{
    [super viewDidLoad];
}

#pragma mark -
#pragma mark search bar methods
- (void) searchBarSearchButtonClicked:(UISearchBar *)theSearchBar
{
	
    [destinationSearchBar resignFirstResponder];
	
}

- (void) searchBarTextDidBeginEditing:(UISearchBar *)searchBar
{
    searchBar.showsCancelButton = YES;
}

- (void) searchBarTextDidEndEditing:(UISearchBar *)searchBar

{
    searchBar.showsCancelButton = NO;
}

- (void)searchBarCancelButtonClicked:(UISearchBar *)searchBar
{
    [searchBar resignFirstResponder];
}

- (void)didReceiveMemoryWarning 
{
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc. that aren't in use.
}

- (void)viewDidUnload 
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}


- (void)dealloc
{
    [destinationSearchBar release];
    [super dealloc];
}


@end
