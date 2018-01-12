import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**

 * @ClassName:     dilation

 * @Description:   dilate a picture 

 * @author          Shaw

 * @version         V1.0  

 * @Date           10/01/2018

 */

public class dilation {
	
	static{
		System.out.println("Dilating operation is under processing. ");
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);};
		
	private int MORPH_RECT = 0;
	
	public dilation(){
	}
	
	public Mat sysDilation(Mat m) {
		Mat rImage = new Mat();
		Imgproc.dilate(m, rImage, Imgproc.getStructuringElement(MORPH_RECT, new Size(5,5)));
		return rImage;
	}
	
	public Mat myDilation(Mat m) {
		Imgproc.cvtColor(m, m, Imgproc.COLOR_RGB2GRAY);
		Mat result = m.clone();
		int width = m.rows();
		int height = m.cols();
		
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				double[] max= {0.00};
				boolean change = false;
				for(int x=i-2; x<i+3; x++) {
					for(int y=j-2; y<j+3; y++) {
						if(x>=0 && y>=0 && x<width && y<height) {
							if(m.get(x, y)[0] > max[0]) {
								max = m.get(x, y);
								change = true;							
							}

							}
						}
					}
				if(change) result.put(i, j, max);
				}
			}
		return result;
	}
	
	public static void main(String[] args){
		dilation d = new dilation();
		try {
			Mat sourceImage = Imgcodecs.imread(args[0]);
			Mat newImage = d.myDilation(sourceImage);
			Imgcodecs.imwrite(args[1], newImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
