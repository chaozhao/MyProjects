//
//  MTSlider.h
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

#import <UIKit/UIKit.h>
@class MTSlider;

//MTSlider Delegate
@protocol MTSliderDelegate <NSObject>
@optional
- (void)MTSliderDidChange:(MTSlider *)MTSlider withValue:(CGFloat)value;
@required
- (CGFloat)startPositionForMTSlider:(MTSlider *)MTSlider;
@end

@interface MTSlider : UISlider
{
    id <MTSliderDelegate> sliderDelegate;
}
@property (nonatomic, assign) id <MTSliderDelegate> sliderDelegate;

//Custom initializer declaration
- (id)initWithFrame:(CGRect)frame andDelegate:(id<MTSliderDelegate>)delegateObject;

@end
