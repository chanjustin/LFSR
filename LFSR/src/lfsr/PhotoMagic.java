package lfsr;

/**
 *
 * @author test
 */

import java.awt.Color;

public class PhotoMagic 
{
    private LFSR lfsr;
    private static String fileName;
    private Picture pic;
    
    public static void main(String args[])
    {
        fileName = "Xpipe";
        new PhotoMagic("src/pictures/" + fileName + ".png","01101000010100010000",16);
    }
    
    public PhotoMagic(String filePath,String binaryPassword,int tap)
    {
        pic = new Picture(filePath);
        lfsr = new LFSR(binaryPassword,tap);
    }
    
    public void encrypt()
    {
        for (int x = 0; x < pic.width(); x++) 
        {
            for (int y = 0; y < pic.height(); y++) 
            {
                Color color = pic.get(x, y);
                int red = color.getRed();
                int blue = color.getBlue();
                int green = color.getGreen();
                int transparency = color.getTransparency();
                int alpha = color.getAlpha();
                
                int newRed = xor(paddedBitPattern(red),paddedBitPattern(lfsr.generate(8)));
                
                int newGreen = xor(paddedBitPattern(green),paddedBitPattern(lfsr.generate(8)));
                
                int newBlue = xor(paddedBitPattern(blue),paddedBitPattern(lfsr.generate(8)));
                
                Color newColor = new Color(newRed, newGreen, newBlue);
                pic.set(x, y, newColor);
            }
        }
        pic.show();
        pic.save("src/pictures/X" + fileName);
    }
    
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    
    /**
     * Pads bit pattern to the left with 0s if it is not 8 bits long
     * @param bitPattern
     * @return 
     */
    public String paddedBitPattern(int bitPattern)
    {
        String tempBit = Integer.toBinaryString(bitPattern);
        String newPattern = "";
        for(int i = 1; i < 9-tempBit.length(); i++)
        {
            newPattern += "0";
        }
        newPattern += tempBit;
        return newPattern;
    }
    
    /**
     * Performs the bitwise XOR
     * @param colorComponent
     * @param generatedBit
     * @return 
     */
    public int xor(String colorComponent, String generatedBit)
    {
        String newColor = "";
        
        for(int i = 0; i < colorComponent.length(); i++)
        {
            if(colorComponent.charAt(i) != generatedBit.charAt(i))
            {
                newColor += 1;
            }
            else
            {
                newColor += 0;
            }
        }
        return Integer.valueOf(newColor,2);
    }
}