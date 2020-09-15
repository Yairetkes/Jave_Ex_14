
/**
 * This is Maman 14.
 *
 * @author Yair Etkes
 * Id - 308224518
 * Date - 2.6.18
 * 
 */

public class Ex14
{
    /** 
     * This method get 2D int array with numbers between 0 to 99 and return how many paths 
     * to get from the first element in the array to the last element.
     * Valid path - Start in first element and go ahead the array according to the unity digit and tens digit in the array.
     * Example: if the indexes is [2][3] and the number is 15 there 2 options to go ahead in the array: 
     * add 1 to row index and 5 to coloums index and go to indexes[3][8] 
     * add 5 to row index and 1 to coloums index and go to indexes [7][4].
     * @param    mat    The input two dimensional array.
     * @return    How many paths to get from the first element in the array to the last element.
     * 
     */
    public static int countPaths(int[][]mat)
    {
        return countPaths(mat,0,0);
    }

    public static int countPaths(int[][]mat, int row, int col) //overloading - that's the method that actually does the job...
    {
        // this method is basically going "brute force" by examining all the possible options of a legal move.
        
        int paths = 0;
        int tensDigit = mat[row][col]/10;
        int onesDigit = mat[row][col]%10;

        if(row == mat.length - 1 && col == mat[0].length - 1)
            paths = 1;

        else if ((row + onesDigit > mat.length - 1) && (row + tensDigit > mat.length - 1) &&// the option when there's no possible way
        //to continue.
        (col + onesDigit > mat[0].length - 1) && (col + tensDigit > mat[0].length - 1) &&
        !(row == mat.length - 1 && col == mat[0].length - 1))
            paths = 0;

        else if (((row + onesDigit > mat.length - 1) || (col + tensDigit > mat[0].length - 1)) && !(row + tensDigit > mat.length - 1) &&
        !(col + onesDigit > mat[0].length - 1) && (onesDigit!=tensDigit)) // the option when only path clear is the one with
        //row's tens digit.
            paths = countPaths(mat,row + tensDigit, col + onesDigit);

        else if (((row + tensDigit > mat.length - 1) || (col + onesDigit > mat[0].length - 1)) && !(row + onesDigit > mat.length - 1)
        && !(col + tensDigit > mat[0].length - 1) && (onesDigit!=tensDigit)) 
        // the option when only path clear is the one with row's ones digit.
            paths = countPaths(mat,row + onesDigit, col + tensDigit); 

        else if (onesDigit==tensDigit)// for numbers which both digits are the same.
            paths = countPaths(mat,row + onesDigit, col + tensDigit);

        else if (!(row + onesDigit > mat.length - 1) && !(row + tensDigit > mat.length - 1) &&/** this case is trigered when both options
        to move are possible.
        */
        !(col + onesDigit > mat[0].length - 1) && !(col + tensDigit > mat[0].length - 1) &&
        !(row == mat.length - 1 && col == mat[0].length - 1))
            paths = countPaths(mat,row + onesDigit, col + tensDigit) + countPaths(mat,row + tensDigit, col + onesDigit);

        return paths; // paths will contain the number of possible paths.
    }

    /**
     * This method get 2 strings and check if the second string has been transformed from first string.
     * Second string has been transformed from first string if all charts in first string exists at least once.
     * Examples: s="abbcd" method return true to these second strings: "abbcd", "aaaabbcd, "abbcddd", "aabbccdd", "abbbccd".
     * Method return false to these second strings: "a", "abcd", "abbbccds", "aaccbbdd", "abbscd".
     * @param   s    The first string.
     * @param   t     The second string to check if has been transformed from s string.
     * @return  True if the t string has been transformed from s string.
     */
    public static boolean isTrans (String s, String t)
    {
        if((s.length()==0) || (t.length()==0)) //checking that the strings are not empty
            return false;
        if(s.charAt(0) == t.charAt(0)) // checking that the first characters are equals.
        {
            if ((s.length() == 1)&&(t.length() == 1)) // if both of the strings contain 1 letter, return true.
                return true;
            if(((s.length()<=t.length())) && (s.length()>1)) //checking that string s has equal or less letters.
            {
                if(s.charAt(1)==t.charAt(1)) 
                    return isTrans(s.substring(1),t.substring(1));// Recursion step -
                    //if the second letter is the same than the method is called and perform on the substring starting from that letter.
                    
                if(s.charAt(0)==t.charAt(1)) 
                    return isTrans(s.substring(0),t.substring(1));
                return false;
            }
            if(((s.length()<=t.length()))&&(s.length()==1)) // for the case that s is smaller or equals to t, and also s has only one
            //letter.
            {
                return (isTrans(s,t.substring(1)));
            }
        }
        return false;

        }
       

    }

