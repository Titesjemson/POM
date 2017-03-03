package com.mst.selenium.extentreport;

import com.mst.selenium.constant.Constant;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReportFactory {
	
	private static ExtentReports reporter;
		 
    public static synchronized ExtentReports getReporter() {
           if (reporter == null) {
                  reporter = new ExtentReports(Constant.ReportPath, true, DisplayOrder.NEWEST_FIRST);
           }
           return reporter;
    }

 }

