//
//  Destination.h
//  CoreDataTabBarTutorial
//
//  Created by Kent Franks on 7/5/11.
//  Copyright 2011 TheAppCodeBlog. All rights reserved.
//

#import <CoreData/CoreData.h>


@interface Destination :  NSManagedObject  
{
}

@property (nonatomic, retain) NSString * name;
@property (nonatomic, retain) NSString * location;
@property (nonatomic, retain) NSString * date;

@end



