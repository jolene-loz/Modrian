/** 
 * The purpose of this program is to decode a hidden message from a picture.
 * @author(CDeshpande)
 * version(7/25/2016)
 * 
 */


import java.awt.*;
import java.awt.Color;
class Decoder
{ /** Start of class Decoder */
    public char translate(int [] bitArray)
    { /** Start of translate() method */
        int value = 0; /** Sets default value of integer variable value to 0 */
       for(int x = bitArray.length-1; x>=0; x--)
       { /** Start of loop */
        value += (bitArray[x] * (int)Math.pow(2,7-x)); /** Traverse loop value */                
       } /** End of loop */
       return (char) value; /** Return value */
    } /** End of translate() method */

    public String solve(Picture picture)
    { /** Start of solve() method */
        Pixel[] pixelArray = picture.getPixels(); /** Puts picture in array */
        int [] bitArray = new int [8]; /** Set number max number of bytes to be traversed ot 8 */
        
        String translatedMessage = ""; /** Initiates variable translatedMessage */
        int count = 0; /** Sets default value of integer variable count to 0 */

        for(int x =0; x <pixelArray.length;  x++)
        { /** Start of loop */
            int Sourcecolor = pixelArray[x].getRed(); /** Sets integer variable Sourcecolor to the amount of redpixels in the array */
            if( Sourcecolor ==255) 
            break;
            /** Break if the amount of pixels reaches 255 */
            bitArray[count] = Sourcecolor; 
            count++; /** The sets increment of integer variable count to 1 */
            
            if (count==8)
            { 
                translatedMessage += translate(bitArray);
                count = 0;
            } /** Translates message if count equals 8 */
        } /** End of loop */

        return translatedMessage; /** Returns translatedMessage */
     } /** End of solve() method */
} /** End of class decoder */
class HideMessage
{ /** Start of class HideMessage */
    public void encodeMessage(Picture picture, int [] binaryArray)
    { /** Start of encodeMessage() method */
        Pixel pixelTarget = new Pixel(picture,0,0); /** Defines variable pixelTarget */
        Pixel [] pixelArray = picture.getPixels(); /** Defines pixelArray */
        Color pixelColor = null; /** Defines value of pixelColor as null */
        int redValue = 0; /** Sets initial value of integer variable redValue to 0 */

        for(int x = 0; x < binaryArray.length; x++)
        { /** Start of loop */
            redValue = binaryArray[x]; /** Defines redvalue as binaryArray with range of x */
            pixelTarget = pixelArray[x]; /** Defines pixelTarget as pixelArray with range of x */
            pixelTarget.setRed(redValue); /** Sets pixel target red variable as redValue */
        } /** End of loop */
        pixelTarget = pixelArray[binaryArray.length]; /** Defines pixelTarget */
        pixelTarget.setRed(255); /** Sets red value of pixel target */
        
        picture.write("HiddenMessage.bmp"); /** Writes picture to disk */
        picture.explore(); /** Explores picture */
        
    } /** End of encodeMessage() method */
    
} /** End of HideMessage Class */ 
public class DecodedMessageTester
{ /** Start of public class DecodedMessageTester */
    public static void main(String[] args)
    { /** Start of main() method */
        Picture stegoObj1 = new Picture("SecretMessage.bmp"); /** Defines picture variable stegoObj1 */
        Picture stegoObj2 = new Picture("Earth.bmp"); /** Defines picture variable stegoObj2 */
        int[] binArray = {0,1,0,0,1,0,0,0,0,1,1,0,0,1,0,1,0,1,1,0,1,1,0,0,0,1,1,0,1,1,0,0,0,1,1,0,1,1,1,1,0,0,0,0,1,0,1,0 };
        Decoder decode = new Decoder(); /** Defines variable decode as a part of class Decoder */
        HideMessage encode = new HideMessage(); /** Defines variable encode as a part of class HideMessage */
        String translatedMessage = decode.solve(stegoObj1); /** Defines string variable translatedMessage */
        encode.encodeMessage(stegoObj2, binArray); /** Defines string variable hiddenMessage */
        System.out.println("Message: " + translatedMessage); /** Prints text to screen */   
        
        
    } /** End of main() method */
} /** End of public class */