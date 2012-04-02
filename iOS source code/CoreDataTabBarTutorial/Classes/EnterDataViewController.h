//
//  EnterDataViewController.h
//  CoreDataTabBarTutorial
//
//  Created by Kent Franks on 7/5/11.
//  Copyright 2011 TheAppCodeBlog. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface EnterDataViewController : UIViewController
{
	
    UITextField *nameTextField;
    UITextField *locationTextField;
    UITextField *dateTextField;
	
    NSFetchedResultsController  *fetchedResultsController;
    NSManagedObjectContext      *managedObjectContext;
	
}

@property (nonatomic, retain) IBOutlet  UITextField *nameTextField;
@property (nonatomic, retain) IBOutlet  UITextField *locationTextField;
@property (nonatomic, retain) IBOutlet  UITextField *dateTextField;

@property (nonatomic, retain) NSFetchedResultsController    *fetchedResultsController;
@property (nonatomic, retain) NSManagedObjectContext        *managedObjectContext;

- (IBAction) saveData;
- (void) dismissKeyboard;

@end
