/*
     File: LazyTableAppDelegate.m 
 Abstract: Application delegate for the LazyTableImages sample.
 It also downloads in the background the "Top Paid iPhone Apps" RSS feed using NSURLConnection.
  
  Version: 1.2 
    
 Copyright (C) 2010 Apple Inc. All Rights Reserved. 
  
 */

#import "LazyTableAppDelegate.h"
#import "RootViewController.h"
#import "ParseOperation.h"

// This framework was imported so we could use the kCFURLErrorNotConnectedToInternet error code.
#import <CFNetwork/CFNetwork.h>


static NSString *const TopPaidAppsFeed =
	@"http://phobos.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/toppaidapplications/limit=75/xml";


@implementation LazyTableAppDelegate

@synthesize window, navigationController, appRecords, rootViewController, queue, appListFeedConnection, appListData;


#pragma mark -

// -------------------------------------------------------------------------------
//	applicationDidFinishLaunching:application
// -------------------------------------------------------------------------------
- (void)applicationDidFinishLaunching:(UIApplication *)application
{
	// Configure and show the window
	[window addSubview:[self.navigationController view]];
	[window makeKeyAndVisible];

    // Initialize the array of app records and pass a reference to that list to our root view controller
    self.appRecords = [NSMutableArray array];
    rootViewController.entries = self.appRecords;
    
    NSURLRequest *urlRequest = [NSURLRequest requestWithURL:[NSURL URLWithString:TopPaidAppsFeed]];
    self.appListFeedConnection = [[[NSURLConnection alloc] initWithRequest:urlRequest delegate:self] autorelease];
    
    // Test the validity of the connection object. The most likely reason for the connection object
    // to be nil is a malformed URL, which is a programmatic error easily detected during development
    // If the URL is more dynamic, then you should implement a more flexible validation technique, and
    // be able to both recover from errors and communicate problems to the user in an unobtrusive manner.
    
    NSAssert(self.appListFeedConnection != nil, @"Failure to create URL connection.");
    
    // show in the status bar that network activity is starting
    [UIApplication sharedApplication].networkActivityIndicatorVisible = YES;
}

// -------------------------------------------------------------------------------
//	handleLoadedApps:notif
// -------------------------------------------------------------------------------
- (void)handleLoadedApps:(NSArray *)loadedApps
{
    [self.appRecords addObjectsFromArray:loadedApps];
    
    // tell our table view to reload its data, now that parsing has completed
    [rootViewController.tableView reloadData];
}

// -------------------------------------------------------------------------------
//	didFinishParsing:appList
// -------------------------------------------------------------------------------
- (void)didFinishParsing:(NSArray *)appList
{
    [self performSelectorOnMainThread:@selector(handleLoadedApps:) withObject:appList waitUntilDone:NO];
    
    self.queue = nil;   // we are finished with the queue and our ParseOperation
}

- (void)parseErrorOccurred:(NSError *)error
{
    [self performSelectorOnMainThread:@selector(handleError:) withObject:error waitUntilDone:NO];
}

#pragma mark -
#pragma mark NSURLConnection delegate methods

// -------------------------------------------------------------------------------
//	handleError:error
// -------------------------------------------------------------------------------
- (void)handleError:(NSError *)error
{
    NSString *errorMessage = [error localizedDescription];
    UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"Cannot Show Top Paid Apps"
														message:errorMessage
													   delegate:nil
											  cancelButtonTitle:@"OK"
											  otherButtonTitles:nil];
    [alertView show];
    [alertView release];
}

// The following are delegate methods for NSURLConnection. Similar to callback functions, this is how
// the connection object,  which is working in the background, can asynchronously communicate back to
// its delegate on the thread from which it was started - in this case, the main thread.
//

// -------------------------------------------------------------------------------
//	connection:didReceiveResponse:response
// -------------------------------------------------------------------------------
- (void)connection:(NSURLConnection *)connection didReceiveResponse:(NSURLResponse *)response
{
    self.appListData = [NSMutableData data];    // start off with new data
}

// -------------------------------------------------------------------------------
//	connection:didReceiveData:data
// -------------------------------------------------------------------------------
- (void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data
{
    [appListData appendData:data];  // append incoming data
}

// -------------------------------------------------------------------------------
//	connection:didFailWithError:error
// -------------------------------------------------------------------------------
- (void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error
{
    [UIApplication sharedApplication].networkActivityIndicatorVisible = NO;
    
    if ([error code] == kCFURLErrorNotConnectedToInternet)
	{
        // if we can identify the error, we can present a more precise message to the user.
        NSDictionary *userInfo = [NSDictionary dictionaryWithObject:@"No Connection Error"
															 forKey:NSLocalizedDescriptionKey];
        NSError *noConnectionError = [NSError errorWithDomain:NSCocoaErrorDomain
														 code:kCFURLErrorNotConnectedToInternet
													 userInfo:userInfo];
        [self handleError:noConnectionError];
    }
	else
	{
        // otherwise handle the error generically
        [self handleError:error];
    }
    
    self.appListFeedConnection = nil;   // release our connection
}

// -------------------------------------------------------------------------------
//	connectionDidFinishLoading:connection
// -------------------------------------------------------------------------------
- (void)connectionDidFinishLoading:(NSURLConnection *)connection
{
    self.appListFeedConnection = nil;   // release our connection
    
    [UIApplication sharedApplication].networkActivityIndicatorVisible = NO;   
    
    // create the queue to run our ParseOperation
    self.queue = [[NSOperationQueue alloc] init];
    
    // create an ParseOperation (NSOperation subclass) to parse the RSS feed data so that the UI is not blocked
    // "ownership of appListData has been transferred to the parse operation and should no longer be
    // referenced in this thread.
    //
    ParseOperation *parser = [[ParseOperation alloc] initWithData:appListData delegate:self];
    
    [queue addOperation:parser]; // this will start the "ParseOperation"
    
    [parser release];
    
    // ownership of appListData has been transferred to the parse operation
    // and should no longer be referenced in this thread
    self.appListData = nil;
}

// -------------------------------------------------------------------------------
//	dealloc
// -------------------------------------------------------------------------------
- (void)dealloc
{
	[navigationController release];
    [appRecords release];
    
	[rootViewController release];
    [appListFeedConnection release];
    [appListData release];
    
    [window release];
    
	[super dealloc];
}

@end