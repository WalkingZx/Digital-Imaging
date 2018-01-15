import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**

 * @ClassName:     erosion

 * @Description:   erode a picture 

 * @author          Shaw

 * @version         V1.0  

 * @Date           10/01/2018

 */

public class erosion {
	static{	
		System.out.println("Eroding operation is under processing. ");
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);};
	
	/** The value is used for the shape of the structure element. */
	private int MORPH_RECT = 0;
	
	public erosion(){
	}
	
	/**
	 * @param m the Mat of the original picture
	 * @return the Mat after eroding by using opencv
	 */
	public Mat sysErosion(Mat m) {
		Mat rImage = new Mat();
		Imgproc.erode(m, rImage, Imgproc.getStructuringElement(MORPH_RECT, new Size(5,5)));
		return rImage;
	}
	
	/**@Description gray processing image, traversing the matrix generated by the image to find the minimum value of the 5 * 5 matrix where each value is located
	 * 
	 * @param m the Mat of the original picture
	 * @return the Mat after eroding by using myself
	 */
	public Mat myErosion(Mat m) {
		//gray processing the image if the image is not gray
		if(m.channels()!=1) Imgproc.cvtColor(m, m, Imgproc.COLOR_RGB2GRAY);
		
		//
		Mat result = m.clone();
		int width = m.rows();
		int height = m.cols();
		
		//Use two loops to iterate over each value in the matrix of the picture
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				
				//Initialize a minimum value
				double[] min= {255.00};
				
				//Use two loops to iterate over each value in the matrix of the structure element
				for(int x=i-2; x<i+3; x++) {
					for(int y=j-2; y<j+3; y++) {
						
						//If the minimum value is found in the structure element, replace the current value with the minimum value						
						if(x>=0 && y>=0 && x<width && y<height && m.get(x, y)[0] < min[0]) {
							min = m.get(x, y);}
						} //end of the final loop
					} // end of the third loop
				result.put(i, j, min);	
				} //end of the second loop
			} //end of the first loop
		return result;
	}
	
	public static void main(String[] args){
		erosion er = new erosion();
		try {
			Mat sourceImage = Imgcodecs.imread("src/lena.png");
			Mat newImage = er.myErosion(sourceImage);
			Imgcodecs.imwrite("src/lena_erosion.png", newImage);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
