//
//  RottenTomatoesJSONViewController.m
//  RottenTomatoesJSON
//
//  Created by Sylvain Marcotte on 11-07-05.
//  Copyright 2011 14 Oranges. All rights reserved.
//
//


#import "RottenTomatoesJSONViewController.h"
#import "JSONKit.h"

NSString* const RT_API_KEY = @"rjcxfgtt5qmv4rra8rsx2ds8"; // ENTER YOUR Rotten Tomatoes API Key here

@implementation RottenTomatoesJSONViewController


-(NSInteger)searchMovie:(NSString *)movieName withYear:(NSString *)aYear
{
	NSString* escapedMovieName = [movieName stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
	
	NSString* searchURL = [NSString stringWithFormat:@"http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=%@&q=%@",RT_API_KEY, escapedMovieName];
	
	NSError* error = nil;
	NSURLResponse* response = nil;
	NSMutableURLRequest* request = [[[NSMutableURLRequest alloc] init] autorelease];
	
	NSURL* URL = [NSURL URLWithString:searchURL];
	[request setURL:URL];
	[request setCachePolicy:NSURLRequestReloadIgnoringLocalCacheData];
	[request setTimeoutInterval:30];
	
	NSData* data = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
	
	if (error)
	{
		NSLog(@"Error performing request %@", searchURL);
		return 0;
	}
		
	NSString *jsonString = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
	NSLog(@"Here is what we got %@", jsonString);
	
	NSDictionary *results = [jsonString objectFromJSONString];
	
	NSArray *movieArray = [results objectForKey:@"movies"];
	
	// Search for year to match
	for (NSDictionary *movie in movieArray)
	{
		NSNumber *year = [movie objectForKey:@"year"];
		
		NSLog(@"Year found: %@", [year stringValue]);
		
		if ([[year stringValue] isEqualToString:aYear])
		{
			NSNumber *ID = [movie objectForKey:@"id"];
		
			NSLog(@"Found ID %d", [ID integerValue]);
			
			return [ID integerValue];
		}
	}
	
	return 0;
}

-(void)getMovieDetails:(NSInteger)movieID
{
	NSString* movieURL = [NSString stringWithFormat:@"http://api.rottentomatoes.com/api/public/v1.0/movies/%d.json?apikey=%@",movieID, RT_API_KEY];
	
	NSError* error = nil;
	NSURLResponse* response = nil;
	NSMutableURLRequest* request = [[[NSMutableURLRequest alloc] init] autorelease];
	
	NSURL* URL = [NSURL URLWithString:movieURL];
	[request setURL:URL];
	[request setCachePolicy:NSURLRequestReloadIgnoringLocalCacheData];
	[request setTimeoutInterval:30];
	
	NSData* data = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
	
	if (error)
	{
		NSLog(@"Error performing request %@", movieURL);
		NSLog(@"%@", [error localizedDescription]);
		return;
	}
	
	NSDictionary *deserializedData = [data objectFromJSONData];
	
	NSDictionary *ratings = [deserializedData objectForKey:@"ratings"];
	
	NSNumber *criticsScore = [ratings objectForKey:@"critics_score"];
	NSLog(@"Critics score = %@", [criticsScore stringValue]);
	
	criticsScoreLabel.text = [criticsScore stringValue];
	
	NSNumber *audienceScore = [ratings objectForKey:@"audience_score"];
	NSLog(@"Critics score = %@", [audienceScore stringValue]);
	
	audienceScoreLabel.text = [audienceScore stringValue];
	
	
	NSDictionary *links = [deserializedData objectForKey:@"links"];
	
	movieReviewsURL = [links objectForKey:@"reviews"]; 
}

-(void)getLinks
{
	NSString* fullReviewsURL = [NSString stringWithFormat:@"%@?apikey=%@",movieReviewsURL, RT_API_KEY];
	
	NSError* error = nil;
	NSURLResponse* response = nil;
	NSMutableURLRequest* request = [[[NSMutableURLRequest alloc] init] autorelease];
	
	NSURL* URL = [NSURL URLWithString:fullReviewsURL];
	[request setURL:URL];
	[request setCachePolicy:NSURLRequestReloadIgnoringLocalCacheData];
	[request setTimeoutInterval:30];
	
	NSData* data = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
	
	if (error)
	{
		NSLog(@"Error performing request %@", fullReviewsURL);
		NSLog(@"%@", [error localizedDescription]);
		return;
	}
	
	NSDictionary *reviewsDict = [data objectFromJSONData];
	
	NSArray *reviews = [reviewsDict objectForKey:@"reviews"];
	
	NSMutableString *reviewString = [[NSMutableString alloc] init];
	
	[reviewString appendString:@"----------------------------\n"];
	
	for (NSDictionary *review in reviews)
	{
		[reviewString appendString:[review objectForKey:@"quote"]];
		[reviewString appendString:@"\n"];
		[reviewString appendString:[review objectForKey:@"critic"]];
		[reviewString appendString:@"\n"];
		[reviewString appendString:[review objectForKey:@"date"]];
		[reviewString appendString:@"\n"];
		[reviewString appendString:[review objectForKey:@"publication"]];
		[reviewString appendString:@"\n"];
		[reviewString appendString:@"----------------------------\n"];
	}
	
	resultTextView.text = reviewString;
	
	[reviewString release];
}

-(IBAction)onSearch:(id)sender
{
	[self dismissKeyboard:sender];
	
	// Get the movie name from the field
	NSString *movieName = searchField.text;
	NSString *year = yearField.text;
	
	// Find the movie id
	NSInteger movieID;
	if (movieName != nil)
	{
		movieID = [self searchMovie:movieName withYear:year];
	}
	
	// Get the movie details
	if (movieID != 0)
	{
		[self getMovieDetails:movieID];
		
		[self getLinks];
	}
}

-(IBAction)dismissKeyboard:(id)sender
{
	[searchField endEditing:YES];
	[yearField endEditing:YES];
	[searchButton endEditing:YES];
}

- (void)didReceiveMemoryWarning {
	// Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
	
	// Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
}

@end
