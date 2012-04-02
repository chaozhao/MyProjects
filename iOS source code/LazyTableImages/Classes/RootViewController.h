/*
     File: RootViewController.h 
 Abstract: Controller for the main table view of the LazyTable sample.
    This table view controller works off the AppDelege's data model.
    produce a three-stage lazy load:
    1. No data (i.e. an empty table)
    2. Text-only data from the model's RSS feed
    3. Images loaded over the network asynchronously
 
    This process allows for asynchronous loading of the table to keep the UI responsive.
    Stage 3 is managed by the AppRecord corresponding to each row/cell.
 
    Images are scaled to the desired height.
    If rapid scrolling is in progress, downloads do not begin until scrolling has ended.
  
  Version: 1.2 
    
 Copyright (C) 2010 Apple Inc. All Rights Reserved. 
  
 */

#import <UIKit/UIKit.h>
#import "IconDownloader.h"

@interface RootViewController : UITableViewController <UIScrollViewDelegate, IconDownloaderDelegate>
{
	NSArray *entries;   // the main data model for our UITableView
    NSMutableDictionary *imageDownloadsInProgress;  // the set of IconDownloader objects for each app
}

@property (nonatomic, retain) NSArray *entries;
@property (nonatomic, retain) NSMutableDictionary *imageDownloadsInProgress;

- (void)appImageDidLoad:(NSIndexPath *)indexPath;

@end