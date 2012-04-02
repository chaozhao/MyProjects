

#import "Calculator.h"

// These string constants contain the characters that the input: method accepts.
const NSString *Operators = @"+-*/";
const NSString *Equals    = @"=";
const NSString *Digits    = @"0123456789.";
const NSString *Period    = @".";
const NSString *Delete    = @"D";
const NSString *Clear     = @"C";


@implementation Calculator

#pragma mark Lifecycle

- init {
   if ((self = [super init])) {
      _display = [[NSMutableString stringWithCapacity:20] retain];
      _operator = nil;
   }
   return self;
}
- (void) dealloc {
   [_display release];
   [_operator release];
   [super dealloc];
}


#pragma mark Calculator Operation

/*
 * The input: method accepts the characters in the string constants
 * Operators, Equals, Digits, Period Delete, and Clear.
 *
 * The results of this method's computations are stored in _display.
 * This method uses _operand, and _operator in its calculations.
 */
- (void) input:(NSString *) input_character {
   static BOOL last_character_is_operator = NO;
   BOOL bad_character;
   
   // Does input_character contain exactly one character?
   if (!(bad_character = !(input_character && [input_character length] == 1))) {
      
      // Is input_character in Digits?
      if ([Digits rangeOfString: input_character].length) {
         if (last_character_is_operator) {
            // Set the display to input_character.
            [_display setString: input_character];
            
            last_character_is_operator = NO;
         }
         // Is input_character a digit, or is a period while a period has not been added to _display?
         else if (![input_character isEqualToString: (NSString *)Period] || [_display rangeOfString: (NSString *)Period].location == NSNotFound) {
            // Add input_character to _display.
            [_display appendString:input_character];
         }
      }
      
      // Is input_character in Operators or is it Equals?
      else if ([Operators rangeOfString:input_character].length || [input_character isEqualToString:(NSString *)Equals]) {
         if (!_operator && ![input_character isEqualToString:(NSString *)Equals]) {
            // input_character is this calculation's operator.
            //
            // Save the operand and the operator.
            _operand  = [[self displayValue] doubleValue];
            _operator = input_character;
         }
         else {
            // input_character is in Operators or Equals.
            //
            // Perform the computation indicated by the saved operator between the saved operand and _display.
            // Place the result in _display.
            if (_operator) {
               double operand2 = [[self displayValue] doubleValue];
               switch ([Operators rangeOfString: _operator].location) {
                  case 0:
                     _operand = _operand + operand2;
                     break;
                  case 1:
                     _operand = _operand - operand2;
                     break;
                  case 2:
                     _operand = _operand * operand2;
                     break;
                  case 3:
                     _operand = _operand / operand2;
                     break;
               }
               [_display setString: [[NSNumber numberWithDouble: _operand] stringValue]];
            }
            // Save the operation (if this is a chained computation).
            _operator = ([input_character isEqualToString:(NSString *)Equals])? nil : input_character;
         }
         last_character_is_operator = YES;
      }
      // Is input_character Delete?
      else if ([input_character isEqualToString:(NSString *)Delete]) {
         // Remove the rightmost character from _display.
         NSInteger index_of_char_to_remove = [_display length] - 1;
         if (index_of_char_to_remove >= 0) {
            [_display deleteCharactersInRange:NSMakeRange(index_of_char_to_remove, 1)];
            last_character_is_operator = NO;
         }
      }
      // Is input_character Clear?
      else if ([input_character isEqualToString:(NSString *)Clear]) {
         // If there's something in _display, clear it.
         if ([_display length]) {
            [_display setString:[NSString string]];
         }
         // Otherwise, clear the saved operator.
         else {
            _operator = nil;
         }
      }
      else {
         // input_character is an unexpected (invalid) character.
         bad_character = TRUE;
      }
   }
   if (bad_character) {
      // Raise exception for unexpected character.
      NSException *exception = [NSException exceptionWithName:NSInvalidArgumentException
                                                       reason:@"The input_character parameter contains an unexpected value."
                                                     userInfo:[NSDictionary dictionaryWithObjectsAndKeys: input_character, @"arg0", nil]];
      [exception raise];
   }
}


#pragma mark Outlets

/*
 * The displayValue method rerutns a copy of _display.
 */
- (NSString *) displayValue {
   if ([_display length]) {
      return [[_display copy] autorelease];
   }
   return @"0";
}

@end
