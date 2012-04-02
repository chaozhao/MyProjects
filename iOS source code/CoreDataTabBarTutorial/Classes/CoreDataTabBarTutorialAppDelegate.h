//
//  CoreDataTabBarTutorialAppDelegate.h
//  CoreDataTabBarTutorial
//
//  Created by Kent Franks on 7/5/11.
//  Copyright 2011 TheAppCodeBlog. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "EnterDataViewController.h"
#import "ShowDestinationsViewController.h"
#import "SearchDestinationsViewController.h"

@interface CoreDataTabBarTutorialAppDelegate : NSObject
{
    UIWindow *window;
    UITabBarController *tabBarController;
	
    EnterDataViewController *enterDataViewController;
    ShowDestinationsViewController *showDestinationsViewController;
    SearchDestinationsViewController *searchDestinationsViewController;
	
@private
    NSManagedObjectContext *managedObjectContext;
    NSManagedObjectModel *managedObjectModel;
    NSPersistentStoreCoordinator *persistentStoreCoordinator;
	
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet UITabBarController *tabBarController;

@property (nonatomic, retain) IBOutlet EnterDataViewController *enterDataViewController;
@property (nonatomic, retain) IBOutlet ShowDestinationsViewController *showDestinationsViewController;
@property (nonatomic, retain) IBOutlet SearchDestinationsViewController *searchDestinationsViewController;

@property (nonatomic, retain, readonly) NSManagedObjectContext *managedObjectContext;
@property (nonatomic, retain, readonly) NSManagedObjectModel *managedObjectModel;
@property (nonatomic, retain, readonly) NSPersistentStoreCoordinator *persistentStoreCoordinator;

- (NSURL *)applicationDocumentsDirectory;
- (void)saveContext;

@end
