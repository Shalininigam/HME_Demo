import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;


boolean TCflag=true
try{

	//Step 1: To navigate to the application login page

	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)
	
	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step 2: To navigate to the application home page

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))

	WebUI.delay(GlobalVariable.MED_DELAY)

	String welcomeMsg =CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('HomePage/welcomeMessage'))
	String expactedWelcomeMsg =CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","welcomeMsg")
	
	if(welcomeMsg.contains(expactedWelcomeMsg))
	{
		System.out.println("Welcome message is verified")
	}else{
		System.out.println("Welcome message is not verified")
	}

	//pre-condition for selecting stores on reports page

	'Click on Stores Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))
/*
	'Click on View/Edit link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/viewEditUsers'))

	'Click on Zoom Label'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/zoomlabel'))

	'Click on View Details link on Zoom Tab'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/zoomViewDetails'))

	String storeID =CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('StorePage/storeID'))
	String storenumber=storeID.trim()
	println storenumber*/
	
	String storenumber=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('StorePage/storepage_storenumber'))
	
	System.out.println(" the store number is--"+storenumber.trim())
	
	
	

	//Step 3: To navigate to report page.

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportsLink'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	//Step 4: To select the store

	WebDriver driver = DriverFactory.getWebDriver()

//	driver.findElement(By.xpath("//span[contains(text(),'"+storenumber.trim()+"')]")).click()
	
	driver.findElement(By.xpath("//span[contains(text(),'22222')]")).click()
	
	WebUI.delay(GlobalVariable.MIN_DELAY)

	//Step 5: To verify time measure

	'Select time'
	WebUI.click(findTestObject('ReportsPage/timeSelection'))

	'Click on dayPart option'
	WebUI.click(findTestObject('ReportsPage/daypartTimeOption'))

	WebUI.click(findTestObject('ReportsPage/summaryReportHeading'))

	'verify dayPart is displayed in TimeSelection dropdown'

	WebUI.verifyElementText(findTestObject('ReportsPage/daypartTimeOption'),CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","daypartTimeSelection"))

	'verify dayPart is displayed in criteria week selection'
	WebUI.verifyElementText(findTestObject('ReportsPage/criteriaTimeMeasureFordaypart'),CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","criteriaDaypartTimeSelection"))

	//Step 6: To verify date.

	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.click(findTestObject('ReportsPage/fromDateSelector'))

	WebUI.delay(GlobalVariable.MIN_DELAY)

	monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))
	monthTextArray= monthText.split(" ")
	month = monthTextArray[0]

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

	String data = CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","StartDate_March3")
	startDate="(//td[text()='"+data+"'])[1]"
	println startDate
	startdateEle=driver.findElement(By.xpath(startDate))
	startdateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.click(findTestObject('ReportsPage/toDateSelector'))

	monthText=WebUI.getText(findTestObject('ReportsPage/monthText2'))
	monthTextArray= monthText.split(" ")
	month = monthTextArray[0]

	String monthTextValue1=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Month")

	if(!monthTextValue1.equals(month))
	{
		while(!monthTextValue1.equals(month))
		{
			WebUI.click(findTestObject('ReportsPage/previousDateSelector2'))
			monthText=WebUI.getText(findTestObject('ReportsPage/monthText2'))
			monthTextArray= monthText.split(" ")
			month = monthTextArray[0]

		}


	}

	String endDate=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","EndDate_March3")

	toDate="(//td[text()='"+endDate+"'])[2]"
	println toDate

	toDateEle=driver.findElement(By.xpath(toDate))
	toDateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate1'),"value")

	String fromDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","fromDate_March3")
	if(!dateAttr.equals(fromDateValue))
	{
		if(TCflag)
			TCflag=false
		println "From date is not selected properly"
		WebUI.takeScreenshot()
	}

	String dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate2'),"value")

	String toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","toDate_March3")
	if(!dateAttr.equals(toDateValue))
	{
		if(TCflag)
			TCflag=false
		println "to Date is not selected properly"
		WebUI.takeScreenshot()
	}

	//Step 7: To verify the time zone for the Day part report in the report section by checking Print time section.

	'click GenerateReport button'
	WebUI.click(findTestObject('ReportsPage/generateReport'))

	WebUI.delay(GlobalVariable.LONG_DELAY)
	

	'Print Time is displayed in Local Time'

	String printTime =CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/printTime'))

	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
	String currentSystemTime=sdf.format(cal.getTime())
	System.out.println(currentSystemTime);

	if(currentSystemTime.contains(printTime)){

		System.out.println("Print Time is displayed the current local time ")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Print Time is not displayed the current local time ")
		WebUI.takeScreenshot()
	}
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/logoutLink'))

}
catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true
