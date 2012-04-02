//
//  LapTimerAppDelegate.h
//  LapTimer
//
//  Created by Alex Eckermann on 2/05/10.
//  Created for MobTuts
//

@interface LapTimerAppDelegate : NSObject <UIApplicationDelegate> {

	NSManagedObjectModel *managedObjectModel;
	NSManagedObjectContext *managedObjectContext;	    
	NSPersistentStoreCoordinator *persistentStoreCoordinator;

	UIWindow *window;
	UINavigationController *navigationController;

}

@property (nonatomic, retain, readonly) NSManagedObjectModel *managedObjectModel;
@property (nonatomic, retain, readonly) NSManagedObjectContext *managedObjectContext;
@property (nonatomic, retain, readonly) NSPersistentStoreCoordinator *persistentStoreCoordinator;

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) UINavigationController *navigationController;

- (NSString *)applicationDocumentsDirectory;

@end

