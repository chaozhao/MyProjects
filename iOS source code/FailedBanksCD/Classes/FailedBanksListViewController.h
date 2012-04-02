//
//  FailedBanksListViewController.h
//  FailedBanksCD
//
//  Created by Ray Wenderlich on 4/12/10.
//  Copyright 2010 Ray Wenderlich. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface FailedBanksListViewController : UITableViewController <NSFetchedResultsControllerDelegate> {
    NSFetchedResultsController *_fetchedResultsController;
    NSManagedObjectContext *_context;    
}

@property (nonatomic, retain) NSFetchedResultsController *fetchedResultsController;
@property (nonatomic, retain) NSManagedObjectContext *context;

@end
