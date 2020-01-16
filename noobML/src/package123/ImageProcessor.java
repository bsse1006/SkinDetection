package package123;

public class ImageProcessor
{
    private double [] [] [] skin = new double [256] [256] [256];
    private double [] [] [] nonSkin = new double [256] [256] [256];
    private double [] [] [] trainedData = new double [256] [256] [256];

    private double skinSum=0;
    private double nonSkinSum=0;

    public ImageProcessor(double[][][] skin, double[][][] nonSkin) {
        this.skin = skin;
        this.nonSkin = nonSkin;
        processor();
    }

    public double[][][] getTrainedData() {
        return trainedData;
    }

    public void processor ()
    {
        for(int i=0; i<256; i++)
        {
            for(int j=0; j<256; j++)
            {
                for(int k=0; k<256; k++)
                {
                    skin[i][j][k]=skin[i][j][k]+1;
                    nonSkin[i][j][k]=nonSkin[i][j][k]+1;
                    skinSum=skinSum+skin[i][j][k];
                    nonSkinSum=nonSkinSum+nonSkin[i][j][k];
                }
            }
        }

        for(int i=0; i<256; i++)
        {
            for(int j=0; j<256; j++)
            {
                for(int k=0; k<256; k++)
                {
                    skin[i][j][k]=skin[i][j][k]/skinSum;
                    nonSkin[i][j][k]=nonSkin[i][j][k]/nonSkinSum;
                    trainedData[i][j][k]=skin[i][j][k]/nonSkin[i][j][k];
                    System.out.println(trainedData[i][j][k]);
                }
            }
        }
    }
}
