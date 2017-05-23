public class Tiles {

    /* TILES TYPES

    10 - floor block
    11 - tiles block
    12 - wooden floor
    13 - grass
    14 - broken grass
    20 - wooden doors
    25 - chest
    44 - character left
    45 - character right
    46 - character up
    47 - character down
    81 - bookshelf
    87 - column
    88 - wall
    89 - broken wall
    90 - plant on upper wall
    91 - plant on lower wall
    92 - plant on left wall
    93 - plant on right wall
    94 - moss on upper wall
    95 - moss on lower wall
    96 - moss on left wall
    97 - moss on right wall
    */

    static int checkThisBlock (int block) {
        /*STATIC BLOCKS*/
        if (block == 81 || (block >= 87 && block <= 97))
            return 1;
        /*FLOOR BLOCKS*/
        else if (block >= 10 && block <= 14)
            return 2;
        /*DOORS*/
        else if (block == 20)
            return 3;
        /*ACTION STATIC BLOCKS*/
        else if (block == 25)
            return 1;
        /*ACTION DYNAMIC BLOCKS*/
        else
            return 0;
    }
}
