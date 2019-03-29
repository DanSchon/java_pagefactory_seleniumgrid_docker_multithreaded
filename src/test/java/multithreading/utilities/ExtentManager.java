package multithreading.utilities;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
		
	private static ExtentReports extent;
		
	public static ExtentReports getInstance() {
			
		if(extent==null){
			Date d = new Date();
			String fileName = "report_"+d.toString().replace(":", "_").replace(" ", "_")+".html";
			extent = new ExtentReports(System.getProperty("user.dir")+ File.separator + "reports" + File.separator +fileName, true, DisplayOrder.NEWEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir")+ File.separator + "reports" + File.separator + "ReportsConfig.xml"));	
		}	
		
		return extent;	
	}

}
