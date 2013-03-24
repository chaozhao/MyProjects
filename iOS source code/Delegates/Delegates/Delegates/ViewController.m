//
//  ViewController.m
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

#import "ViewController.h"
@implementation ViewController

#pragma mark -
#pragma mark UIViewController Override Methods
- (void)viewDidLoad
{
    [super viewDidLoad];
    
    //set backgroundColor of viewController - use 4 color components R,G,B & A
    CGFloat sliderColorPosition = 0.3f;
    self.view.backgroundColor = [UIColor colorWithRed:sliderColorPosition green:sliderColorPosition blue:sliderColorPosition alpha:1.0f];
    
    //Instaniate a a slider from the Red, Green, and Blue Values
    CGRect redSliderFrame = CGRectMake(20.0f, 20.0f, 280.0f, 28.0f);
    MTSlider *redSlider = [[MTSlider alloc] initWithFrame:redSliderFrame andDelegate:self];
    //redSlider.sliderDelegate = self; // <-- Normally see this
    redSlider.tag = 1;
    [self.view addSubview:redSlider];
    [redSlider release];
    
    CGRect greenSliderFrame = CGRectMake(20.0f, 70.0f, 280.0f, 28.0f);
    MTSlider *greenSlider = [[MTSlider alloc] initWithFrame:greenSliderFrame andDelegate:self];
    greenSlider.tag = 2;
    [self.view addSubview:greenSlider];
    [greenSlider release];
    
    CGRect blueSliderFrame = CGRectMake(20.0f, 120.0f, 280.0f, 28.0f);
    MTSlider *blueSlider = [[MTSlider alloc] initWithFrame:blueSliderFrame andDelegate:self];
    blueSlider.tag = 3;
    [self.view addSubview:blueSlider];
    [blueSlider release];

}


#pragma mark -
#pragma mark UIViewController Rotation Methods
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);
}

#pragma mark -
#pragma mark MTSliderDelegate Methods
- (CGFloat)startPositionForMTSlider:(MTSlider *)MTSlider
{
    //Return the start value to the sliders
    CGFloat sliderStartPosition = 0.3f;
    return sliderStartPosition;
}

- (void)MTSliderDidChange:(MTSlider *)MTSlider withValue:(CGFloat)value
{
    if (MTSlider.tag == 1) { //Red Slider

        //Create a pointer to to an array of the background color values R,G,B, & A
        //location - colors[0] is the current Red Value
        //location - colors[1] is the current Green Value 
        //location - colors[2] is the current Blue Value 
        //location - colors[3] is the current Alpha Value 
        
        //In your own code this logic might be better suited in its own method
        CGColorRef bgColor = self.view.backgroundColor.CGColor;
        const CGFloat *colorsPointer = CGColorGetComponents(bgColor);
        CGFloat currentGreen = colorsPointer[1];
        CGFloat currentBlue = colorsPointer[2];
        
        //Set the passed in value from MTSlider for the appropriate color, set the others to their current value
        self.view.backgroundColor = [UIColor colorWithRed:value green:currentGreen blue:currentBlue alpha:1.0f];
 
    }
    if (MTSlider.tag == 2) { //Green Slider
        
        CGColorRef bgColor = self.view.backgroundColor.CGColor;
        const CGFloat *colorsPointer = CGColorGetComponents(bgColor);
        CGFloat currentRed = colorsPointer[0];
        CGFloat currentBlue = colorsPointer[2];
        self.view.backgroundColor = [UIColor colorWithRed:currentRed green:value blue:currentBlue alpha:1.0f];

    }
    if (MTSlider.tag == 3) { //Blue Slider
        
        CGColorRef bgColor = self.view.backgroundColor.CGColor;
        const CGFloat *colorsPointer = CGColorGetComponents(bgColor);
        CGFloat currentRed = colorsPointer[0];
        CGFloat currentGreen = colorsPointer[1];
        self.view.backgroundColor = [UIColor colorWithRed:currentRed green:currentGreen blue:value alpha:1.0f];
    }

}

@end
