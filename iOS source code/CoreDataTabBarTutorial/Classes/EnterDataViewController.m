    //
//  EnterDataViewController.m
//  CoreDataTabBarTutorial
//
//  Created by Kent Franks on 7/5/11.
//  Copyright 2011 TheAppCodeBlog. All rights reserved.
//

#import "EnterDataViewController.h"
#import "Destination.h"


@implementation EnterDataViewController

@synthesize nameTextField, locationTextField, dateTextField;
@synthesize fetchedResultsController, managedObjectContext;

- (void)viewDidLoad 
{
    [super viewDidLoad];
}

- (IBAction) saveData
{
    NSLog(@"saveData");
    [self dismissKeyboard];
    Destination *dest = (Destination *)[NSEntityDescription insertNewObjectForEntityForName:@"Destination" inManagedObjectContext:managedObjectContext];
    dest.name = nameTextField.text;
    dest.location = locationTextField.text;
    dest.date = dateTextField.text;
	
    NSError *error;
	
    // here's where the actual save happens, and if it doesn't we print something out to the console
    if (![managedObjectContext save:&error])
    {
        NSLog(@"Problem saving: %@", [error localizedDescription]);
    }
	
    // **** log objects currently in database ****
    // create fetch object, this object fetch's the objects out of the database
    NSFetchRequest *fetchRequest = [[NSFetchRequest alloc] init];
    NSEntityDescription *entity = [NSEntityDescription entityForName:@"Destination" inManagedObjectContext:managedObjectContext];
    [fetchRequest setEntity:entity];
    NSArray *fetchedObjects = [managedObjectContext executeFetchRequest:fetchRequest error:&error];
	
    for (NSManagedObject *info in fetchedObjects)
    {
        NSLog(@"Destination name: %@", [info valueForKey:@"name"]);
        NSLog(@"Destination location: %@", [info valueForKey:@"location"]);
        NSLog(@"Destination date: %@", [info valueForKey:@"date"]);
    }
    [fetchRequest release];
	
}
// dismiss the keyboard when a user selects the save data button
- (void) dismissKeyboard
{
    [nameTextField resignFirstResponder];
    [locationTextField resignFirstResponder];
    [dateTextField resignFirstResponder];
}

// dismisses the keyboard when a user selects the return key
- (BOOL) textFieldShouldReturn: (UITextField *) theTextField
{
    [theTextField resignFirstResponder];
	
    return YES;
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
    [fetchedResultsController release];
    [managedObjectContext release];
	
    [nameTextField release];
    [locationTextField release];
    [dateTextField release];
	
    [super dealloc];
}


@end
