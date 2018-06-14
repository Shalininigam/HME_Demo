import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath
import java.util.regex.Pattern;
import java.util.StringTokenizer;

boolean TCflag=true
try{

//Login as CSV User credentials

	
	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)
	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","Usernamefor_csv"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","password_csv"))

	WebUI.delay(GlobalVariable.MED_DELAY)
		

	'Click on Reports Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportsLink'))

	//Step1 : To verify that user is able to select Groups
	WebUI.delay(GlobalVariable.MED_DELAY)
	
	WebDriver driver = DriverFactory.getWebDriver()

	driver.findElement(By.xpath("//span[contains(text(),'22222')]")).click()

	//To verify that user is able to select time measure

	'Select time'
	WebUI.click(findTestObject('ReportsPage/timeSelection'))

	'Click on week option'
	WebUI.click(findTestObject('ReportsPage/weekTimeOption'))

	WebUI.click(findTestObject('ReportsPage/summaryReportHeading'))

	'verify week is displayed in TimeSelection dropdown'

	WebUI.verifyElementText(findTestObject('ReportsPage/weekTimeOption'),CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","weekTimeSelection"))

	'verify week is displayed in criteria week selection'
	WebUI.verifyElementText(findTestObject('ReportsPage/criteriaTimeMeasure'),CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","criteriaWeekTimeSelection"))

	//Step 3: To verify that user is able to select From and To date by using calendar controls
	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.click(findTestObject('ReportsPage/fromDateSelector'))

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))
	String [] monthTextArray= monthText.split(" ")
	String month = monthTextArray[0]

	String monthTextValue=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Month")

	if(!monthTextValue.equals(month))
	{
		while(!monthTextValue.equals(month))
		{
			WebUI.click(findTestObject('ReportsPage/previousDateSelector'))
			monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))
			monthTextArray= monthText.split(" ")
			month = monthTextArray[0]

		}

	}

	String data = CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","StartDate")
	startDate="(//td[text()='"+data+"'])[1]"
	println startDate
	WebElement startdateEle=driver.findElement(By.xpath(startDate))
	startdateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.click(findTestObject('ReportsPage/toDateSelector'))

	monthText=WebUI.getText(findTestObject('ReportsPage/monthText2'))
	monthTextArray= monthText.split(" ")
	month = monthTextArray[0]

	if(!monthTextValue.equals(month))
	{
		while(!monthTextValue.equals(month))
		{
			WebUI.click(findTestObject('ReportsPage/previousDateSelector2'))
			monthText=WebUI.getText(findTestObject('ReportsPage/monthText2'))
			monthTextArray= monthText.split(" ")
			month = monthTextArray[0]

		}


	}

	String endDate=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","EndDate")

	toDate="(//td[text()='"+endDate+"'])[2]"

	WebElement toDateEle=driver.findElement(By.xpath(toDate))
	toDateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate1'),"value")

	String fromDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","fromDate")
	if(!dateAttr.equals(fromDateValue))
	{
		if(TCflag)
			TCflag=false
		println "From date is not selected properly"
		WebUI.takeScreenshot()
	}

	dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate2'),"value")

	String toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","toDate")
	if(!dateAttr.equals(toDateValue))
	{
		if(TCflag)
			TCflag=false
		println "to Date is not selected properly"
		WebUI.takeScreenshot()
	}

	//Step6 : To Generate report
	'click GenerateReport button'
	WebUI.click(findTestObject('ReportsPage/generateReport'))

	WebUI.delay(GlobalVariable.LONG_DELAY)

	'Click on first store'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('SummarizedReportPage/storeFirst'))
	
	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step 18: To verify the weekly report in CSV format
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('SummarizedReportPage/downloadBtn'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('SummarizedReportPage/csvLink'))

	WebUI.delay(GlobalVariable.LONG_DELAY)
	//code to read the email and read the download file
	
	List<String> s=CustomKeywords.'projectSpecific.Reusability.checkemailandverifyattachment'()

	// verification of week report
	System.out.println("the text is---"+s.get(0))
	System.out.println("the text is---"+s.get(2))
	String s1=s.get(0).substring(0,7);
	
	//String s1="W e e k"
	
	List<String> li= new ArrayList<String>();
	
	  int i=0;
		
		for(int j=1;j<=s1.length();j++){
			
			String substring1=s1.substring(i,j);
			
		
			if(substring1>'A' && substring1<'Z' || substring1>'a' && substring1<'z' ){
				
				li.add(substring1)
				
				i++;
			}else{
			i++;
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		
	for(int k=0;k<li.size();k++){
		
		sb.append(li.get(k))
		
		}
	
	System.out.println("the values are-----"+sb.toString())
	
	String sb2=sb.toString();
	boolean status=sb2.equals("Week")
		
	if(status)
	{
		System.out.println("Downloaded CSV file  displayed Week report")
	}
	else{
		if(TCflag)
			TCflag=false
		System.out.println("Downloaded CSV file  not displayed Week report")

	}
	
	//Step 20: To verify that user is able to view day wise report for a selected store

	if(status)
	{
		System.out.println("the text is---"+s.get(2))
		
		s1=s.get(2).substring(0,s.get(2).length()-1);
		
		println s1
		
	}
		
	  li= new ArrayList<String>();
	
	  int l=0;
		
		for(int j=1;j<=s1.length();j++){
			
			String substring1=s1.substring(l,j);
			
		
			if(substring1!=null ){
				
				li.add(substring1)
				
				l++;
			}else{
			l++;
			}
			
		}
		
		sb = new StringBuilder();
		
	for(int k=0;k<li.size();k++){
		
		sb.append(li.get(k))
		
		}
	
	System.out.println("the values are-----"+sb.toString())
	
	sb2=sb.toString();
	status=sb2.equals("03-01-2018-03-07-2018OPEN-CLOSE")
		
	if(status)
	{
		System.out.println("Day wise report will be generated for Store 1")
	}
	else{
		if(TCflag)
			TCflag=false
		System.out.println("Day wise report will not be generated for Store 1")

	}
		
	
	
	
	
	
	
	


	//System.out.println(st);


	
	
	//Step 19: To verify the CSV file (Pending) from here till 34 steps is pending


	//Post-Condition : Deleting the created group

	/*CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))

	WebUI.delay(GlobalVariable.MIN_DELAY)*/



	}catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true

