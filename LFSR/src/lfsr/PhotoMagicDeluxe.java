package lfsr;

/**
 *
 * @author Justin Chan
 * @version 1.0
 */
public class PhotoMagicDeluxe 
{
    private String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    
    public static void main(String args[])
    {
        String fileName = "Xpipe";
        PhotoMagicDeluxe deluxe = new PhotoMagicDeluxe();
        PhotoMagic magic = new PhotoMagic("src/pictures/" + fileName + ".png",deluxe.getBinaryPassword("password"),16);
        magic.setFileName(fileName + ".png");
        magic.encrypt();
    }
    
    public String getBinaryPassword(String text)
    {
        String binaryPassword = "";
        for(int i = 0; i < text.length(); i++)
        {
            binaryPassword += Integer.toBinaryString(characterSet.indexOf(text.charAt(i)));
        }
        return binaryPassword;
    }
}
