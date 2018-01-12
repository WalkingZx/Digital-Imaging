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
		
	private int MORPH_RECT = 0;
	
	public erosion(){
	}
	
	public Mat sysErosion(Mat m) {
		Mat rImage = new Mat();
		Imgproc.erode(m, rImage, Imgproc.getStructuringElement(MORPH_RECT, new Size(5,5)));
		return rImage;
	}
	
	public Mat myErosion(Mat m) {
		Imgproc.cvtColor(m, m, Imgproc.COLOR_RGB2GRAY);
		Mat result = m.clone();
		int width = m.rows();
		int height = m.cols();
		
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				double[] min= {255.00};
				for(int x=i-2; x<i+3; x++) {
					for(int y=j-2; y<j+3; y++) {
						if(x>=0 && y>=0 && x<width && y<height) {
							if(m.get(x, y)[0] < min[0]) min = m.get(x, y);
							}
						}
					}
				result.put(i, j, min);	
				}
			}
		return result;
	}
	
	public static void main(String[] args){
		erosion er = new erosion();
		try {
			Mat sourceImage = Imgcodecs.imread(args[0]);
			Mat newImage = er.myErosion(sourceImage);
			Imgcodecs.imwrite(args[1], newImage);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
