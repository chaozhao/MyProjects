
#import <UIKit/UIKit.h>
#import "Calculator.h"

@interface CalcViewController : UIViewController {
   id          displayField;
   Calculator *calculator;
}

@property (nonatomic, retain) IBOutlet id displayField;

- (IBAction) press:(id)sender;

@end
