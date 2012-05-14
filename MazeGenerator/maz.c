/**
 * @file maz.c
 * @brief generate a maze
 *
 * @author jallen@ic.sunysb.edu - algorithm posted to rec.games.programmer
 * @author mzraly@ldbvax.dnet.lotus.com - program cleaned and reorganized
 * @author jlk - restructured, commented, created driver and output to file
 *
 * @date 11 April 2010
 *
 * @note Original code at http://www.mazeworks.com/mazegen/maze_faq
 * Now outputs mazes compatible with predefined format to file or to screen
 *
 * Original commment:
 * Don't make people pay for this, or I'll jump up and down and
 * yell and scream and embarass you in front of your friends...
 */
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <getopt.h>

typedef struct
{
    int seed;
    int multiple;
    int offset;
} RandGen; // Random number generator

// Define a bunch of default values
static const int  MULTIPLE = 57;                // random number generation parameter
static const int  OFFSET = 1;                   // random number generation parameter
static const char WALL = '1';                   // character representing the wall
static const char PATH = '0';                   // character representing the path
static const int  MAXY = 23;                    // Default height of maze
static const int  MAXX = 39;                    // default width of maze
static const bool TO_SCREEN = true;             // prints to screen by default
static const char* FILE_NAME = "randmaze.maz";  // default output filewhen not to screen

/** @brief Maze generating function
 *
 * @param maz char [] Stores maze data - an array that should already have its memory allocated
 * @param height int number of cells in height
 * @param width int number of cells in width
 * @param wall char character to use for maze walls
 * @param path char character to use for maze paths
 * @param rnd RandGen * address of randmom number generator data
 */
void maze (char maz[], int height, int width, char wall, char path, RandGen* rnd);

/** @brief Recursive maze generation
 *
 * @param pos int current position inside the maze
 * @param maz char [] maze data - contains the wall and path characters
 * @param y int maze height in number-of-cells
 * @param x int maze width in number-of-cells
 * @param rnd RandGen * address of randmom number generator data
 */
void mazegen (int pos, char maz[], char y, char x, RandGen* rnd);

/** @brief prints the maze
 *
 * @param stream FILE* file or stdout
 * @param maz[] char the maze
 * @param x int  width of maze in number-of-cells
 * @param y int height of maze in number-of-cells
 * @param path char the path character
 * @param wall char the wall character
 * @return void
 */
void pokeAndPrint (FILE* stream, char maz[], int x, int y, char path, char wall);

/** @brief Prints program usage to standard output
 * @return void
 */
void usage();

/** @brief computes a random integer between min and max
 *
 * @param min int
 * @param max int
 * @return int the random integer
 */
int randInt (int min, int max);

/*************************************************************************************/
int main (int argc, char* argv[])
{
    extern char*    optarg;
    int             x = MAXX;
    int             y = MAXY;
    char            wall = WALL;
    char            path = PATH;
    char*           filename = (char *) FILE_NAME;
    int             multiple = MULTIPLE, offset = OFFSET; // Random number generator parameters
    bool            toScreen = DEBUG; // display to screen unless file required on command line
    int i;

    srand (time (0L)); // Seed random number generator

    // Process command line
    while ( (i = getopt (argc, argv, "w:p:y:x:m:o:f:a?")) != EOF)
    {
        switch (i)
        {
        case 'w':
            wall = optarg[0];
            break;
        case 'p':
            path = optarg[0];
            break;
        case 'y':
            y = atoi (optarg);
            break;
        case 'x':
            x = atoi (optarg);
            break;
        case 'm':
            multiple = atoi (optarg);
            break;
        case 'o':
            offset = atoi (optarg);
            break;
        case 'f':
            filename = (char *) malloc (strlen (optarg) + 1);
            strcpy (filename, optarg);
        case 'a':
            toScreen = false;
            break;
        case '?':
        default:
            usage();
            return 1;
            break;
        }
    }

    // force functional maze sizes
    if (! (y & 1)) y--;
    if (! (x & 1)) x--;
    if (x < 5) x = 5;
    if (y < 5) y = 5;

    RandGen  rnd = {time (0L), multiple, offset}; // initialise random number generator
    char* maz = (char *) calloc (x * y, sizeof (char)); // Allocate and zero the maze
    if (!maz)
    {
        fprintf (stderr, "not enough memory for the maze");
        return 1;
    }

    maze (maz, y, x, wall, path, &rnd); // create the maze

    // Open entrance and exit in walls and output
    if (toScreen)
        pokeAndPrint (stdout, maz, x, y, path, wall);
    else
    {
        FILE* outStream = fopen (filename, "w");
        if (outStream)
        {
            pokeAndPrint (outStream, maz, x, y, path, wall);
            fclose (outStream);
        }
        else
        {
            fprintf (stderr, "Couldn't open %s\n", filename);
            return 1;
        }
    }
    return 0;
}

/*
 * maze() : generate a random maze of size (y by x) in maz, using vc as the
 * vertical character, hc as the horizontal character, and fc as the floor
 * character
 *
 * maz is an array that should already have its memory allocated - you could
 * malloc a char string if you like.
 */
void maze (char maz[], int y, int x, char wall, char path, RandGen* rnd)
{
    int i, yy, xx;

    mazegen ( (x + 1), maz, y, x, rnd);

    /* Now replace the 1's and 0's with appropriate chars */
    for (yy = 0; yy < y; ++yy)
    {
        for (xx = 0; xx < x; ++xx)
        {
            i = (yy * x) + xx;

            if (yy == 0 || yy == (y - 1))
                maz[i] = wall;
            else if (xx == 0 || xx == (x - 1))
                maz[i] = wall;
            else if (maz[i] == 1)
                maz[i] = path;
            else
                maz[i] = wall;
        }
    }
}

/*
 * mazegen : do the recursive maze generation
 *
 */
void mazegen (int pos, char maz[], char y, char x, RandGen* rnd)
{
    static int d, i, j; // static saves room on the stack for recursion

    maz[pos] = 1;
    while ( (d = (pos <= x * 2 ? 0 : (maz[pos - x - x] ? 0 : 1))
                 | (pos >= x * (y - 2) ? 0 : (maz[pos + x + x] ? 0 : 2))
                 | (pos % x == x - 2 ? 0 : (maz[pos + 2] ? 0 : 4))
                 | (pos % x == 1 ? 0 : (maz[pos - 2] ? 0 : 8))))
    {
        do
        {
            rnd->seed = (rnd->seed * rnd->multiple + rnd->offset);
            i = 3 & (rnd->seed / d);
        }
        while (! (d & (1 << i)));

        switch (i)
        {
        case 0:
            j = -x;
            break;
        case 1:
            j = x;
            break;
        case 2:
            j = 1;
            break;
        case 3:
            j = -1;
            break;
        default:
            break;
        }

        maz[pos + j] = 1;
        mazegen (pos + 2 * j, maz, y, x, rnd);
    }
}

/** @brief Poke entrance and exit into wall and prints the maze
 *         Fiddly code
 *
 * @param stream FILE * open file stream or stdout
 * @param maz  char [] - the maze data
 * @param x  int - Width of maze in number of characters
 * @param y  int - Height of maze in number of characters
 * @param path  char - The character representing the paths
 * @param wall  char - The character representing the walls
 * @return void
 */
void pokeAndPrint (FILE* stream, char maz[], int x, int y, char path, char wall)
{
    int inX, inY, outX, outY; // Coords of entrance and exit
    char inside;

    // Poke entrance somewhere around the top left
    do
    {
        inX = randInt (1, x / 2);
        inY = randInt (1, y / 2);
        if ( (double) inX / x > (double) inY / y)
        {
            inX = 0;
            inside = maz[x * inY + 1];
        }
        else
        {
            inY = 0;
            inside = maz[x + inX];
        }
    } while (inside == wall);

    maz[inY* x + inX] = path;

    // Poke exit somewhere around the bottom right
    do
    {
        outX = randInt (x / 2, x - 2);
        outY = randInt (y / 2, y - 2);
        if ( (double)outX / x < (double)outY / y)
        {
            outX = x - 1;
            inside = maz[ x * (outY + 1) - 2]; // was maz[x * outY - 2]; (BAD!)
        }
        else
        {
            outY = y - 1;
            inside = maz[(outY - 1) * x + outX];
        }
    } while (inside == wall);

    maz[outY* x + outX] = path;

    fprintf (stream, "%2i %2i\n", x, y); // size

    // Select entrance or exit at random as the maze entry point
    if ( (outX + outY) & 1)
        fprintf (stream, "%2i %2i\n", inX, inY);
    else
        fprintf (stream, "%2i %2i\n", outX, outY);

    for (int i = 0; i < (x * y); ++i)
    {
        fputc (' ', stream); fputc (maz[i], stream);
        if ( ( (i + 1) % x) == 0)
            fputc ('\n', stream);
    }
}


/* Print application usage */
void usage()
{
    fprintf (stderr,
             "\
  Writes a random maze to file \"randmaze.maz\"\n\
  usage: maz [xywpmofa?]\n\
  where -x specify the width of the maze (odd integer\n\
        -y specify the height (odd integer)\n\
        -w the character to use for walls\n\
        -p the character used for paths\n\
        -m factor used in random number generation\n\
        -o offset used in random number generation\n\
        -f filename for maze output\n\
        -a automatic output of maze to \"randmaze.maz\" or filename if given\n\
        -? prints this screen\n\n\
  Example: mazegen -x23 -y23 -w# -p~ -a\n");
}

/* computes a random integer between min and max */
int randInt (int min, int max)
{
    return rand() % (max - min + 1) + min;
}
