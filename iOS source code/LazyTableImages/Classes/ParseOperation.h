/*
     File: ParseOperation.h 
 Abstract: NSOperation code for parsing the RSS feed.
  
  Version: 1.2 
  
 Copyright (C) 2010 Apple Inc. All Rights Reserved. 
  
 */

@class AppRecord;

@protocol ParseOperationDelegate;

@interface ParseOperation : NSOperation <NSXMLParserDelegate>
{
@private
    id <ParseOperationDelegate> delegate;
    
    NSData          *dataToParse;
    
    NSMutableArray  *workingArray;
    AppRecord       *workingEntry;
    NSMutableString *workingPropertyString;
    NSArray         *elementsToParse;
    BOOL            storingCharacterData;
}

- (id)initWithData:(NSData *)data delegate:(id <ParseOperationDelegate>)theDelegate;

@end

@protocol ParseOperationDelegate
- (void)didFinishParsing:(NSArray *)appList;
- (void)parseErrorOccurred:(NSError *)error;
@end
