//
//  MTSlider.m
//  Delegates
//
//  Created by Aaron Crabtree on 5/4/12.
//  Copyright (c) 2012 Tap Dezign, LLC. All rights reserved.

/*--
 
 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTY OF ANY KIND, 
 EXPRESS OR IMPLIED,INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS 
 OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 AN ACTION OF CONTRACT,TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH 
 THE USE OF THIS CODE OR ANY OTHER DEALINGS RELATING TO THIS SOFTWARE.
 
 --*/

#import "MTSlider.h"

#pragma mark _
#pragma mark Private Interface
@interface MTSlider ()
@end

@implementation MTSlider

#pragma mark _
#pragma mark Accessor Methods
@synthesize sliderDelegate = sliderDelegate;

#pragma mark _
#pragma mark Slider Override Method
- (void)setValue:(float)value animated:(BOOL)animated
{
    //Besure to call the super class when overriding this method or the slider peg will not move
    [super setValue:value animated:animated];
    
    /*--
     * Send a message to the optional <MTSliderDelegate> method 
     * Only send a message if the delegate object (ViewController in this case) implements the MTSliderDidChange:withValue: method otherwise app will crash
     --*/
    
    if (sliderDelegate != nil && [sliderDelegate respondsToSelector:@selector(MTSliderDidChange:withValue:)]){
		[[self sliderDelegate] MTSliderDidChange:self withValue:value];
	}

}


#pragma mark _
#pragma mark Custom Initalization Method
- (id)initWithFrame:(CGRect)frame andDelegate:(id<MTSliderDelegate>)delegateObject
{
    self = [super initWithFrame:frame];
    if (self) {
        
        //Set delegate object on initalization
        self.sliderDelegate = delegateObject;
        
        //Use the delegate to set the value property of the UISlider
        self.value = [sliderDelegate startPositionForMTSlider:self];
        
        //Set left side of slider track to gray
        self.minimumTrackTintColor = [UIColor grayColor];
    }
    return self;
}



@end
