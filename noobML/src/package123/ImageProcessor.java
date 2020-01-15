package package123;

public class ImageProcessor
{
    private int [] [] [] skin = new int [256] [256] [256];
    private int [] [] [] nonSkin = new int [256] [256] [256];
    private int [] [] [] trainedData = new int [256] [256] [256];

    int skinSum=0;
    int nonSkinSum=0;

    public ImageProcessor(int[][][] skin, int[][][] nonSkin) {
        this.skin = skin;
        this.nonSkin = nonSkin;
    }

    public void processor ()
    {
        for(int i=0; i<256; i++)
        {
            for(int j=0; j<256; j++)
            {
                for(int k=0; k<256; k++)
                {
                    skin[i][j][k]++;
                    nonSkin[i][j][k]++;
                    skinSum=skinSum+skin[i][j][k];
                    nonSkinSum=nonSkinSum+nonSkin[i][j][k];
                }
            }
        }
    }
}
