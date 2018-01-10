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
		Mat result = m.clone();
		int weight = m.rows();
		int hight = m.cols();
		
		for(int i=0; i<weight; i++) {
			for(int j=0; j<hight; j++) {
				double[] max= {0.00};
				boolean change = false;
				for(int x=i-2; x<i+3; x++) {
					for(int y=j-2; y<j+3; y++) {
						if(x>=0 && y>=0 && x<weight && y<hight) {
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
			Mat sourceImage = Imgcodecs.imread("src/lena.png");
			Mat newImage = d.myDilation(sourceImage);
			Imgcodecs.imwrite("src/lena_dilation.png", newImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
