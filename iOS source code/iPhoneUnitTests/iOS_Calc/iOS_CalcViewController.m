

#import "iOS_CalcViewController.h"


@implementation CalcViewController

@synthesize displayField;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
   if ((self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil])) {
      calculator = [[Calculator alloc] init];
   }
   return self;
}

- (BOOL) shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
   return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

- (void) dealloc {
   [calculator release];
   [super dealloc];
}

- (IBAction) press:(id)sender {
   [calculator input:[sender titleForState:UIControlStateNormal]];
   [displayField setText:[calculator displayValue]];
}

@end
