//
//  TimeTableController.h
//  LapTimer
//
//  Created by Alex Eckermann on 2/05/10.
//  Created for MobTuts
//

#import <UIKit/UIKit.h>
#import "Event.h"

@interface TimeTableController : UITableViewController {

	NSManagedObjectContext *managedObjectContext;
	NSMutableArray *eventArray;
	
}

@property (nonatomic, retain) NSManagedObjectContext *managedObjectContext;
@property (nonatomic, retain) NSMutableArray *eventArray;

- (void)fetchRecords;
- (void)addTime:(id)sender;

@end
