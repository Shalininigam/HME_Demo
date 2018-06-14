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
	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))

	WebUI.delay(GlobalVariable.LONG_DELAY)
	//Step1 : To verify that user is able to select Groups

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportsLink'))
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.delay(GlobalVariable.LONG_DELAY)


	//Step 1: To verify that user is able to select Groups

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

	'Click on Add New Group button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/addNewGroup'))

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupNameTxt'),

			CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","Summarygrp"))
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportingGroupManagement/groupDescTxt'),

			CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","GroupDesc"))

	WebUI.delay(GlobalVariable.MED_DELAY)

	String groupmain=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportingGroupManagementPage","Summarygrp")

	List<WebElement> grouplist = driver.findElements(By.xpath("//ul[@class='unlinked-grouplist']/li"))
	WebUI.delay(GlobalVariable.MED_DELAY)

	for(int i=0; i<grouplist.size();i++){


		println grouplist.get(i).getText()

		if(grouplist.get(i).getText().contains("0000")){

			driver.findElement(By.xpath("(//ul[@class='unlinked-grouplist']/li/label)["+(i+1)+"]")).click()
			break;
		}
	}

	driver.findElement(By.xpath("//button[contains(text(),'>')]")).click()

	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/secondSelectAllCB'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/saveBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportsLink'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	driver.findElement(By.xpath("//span[contains(text(),'"+groupmain+"')]/../../../../span[2]")).click()


	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step2:To verify that user is able to select time measure
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/timeSelection'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/dayTimeOption'))


	//Step3: To verify that user is able to select From and To date by using calendar controls

	WebUI.click(findTestObject('ReportsPage/fromDateSelector'))

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))
	String[] monthTextArray= monthText.split(" ")
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
	String startDate="(//div[@class='rdtPicker']/div/table/tbody/tr/td[text()='"+data+"'])[1]"


	println startDate
	WebElement startdateEle=driver.findElement(By.xpath(startDate))
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

	String endDate=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","startdate_TC8")

	String toDate="(//div[@class='rdt date-time rdtOpen']/div[@class='rdtPicker']/div/table/tbody/tr/td[text()='"+endDate+"'])[1]"

	println toDate

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

	String toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Fromdate_TC8")
	if(!dateAttr.equals(toDateValue))
	{
		if(TCflag)
			TCflag=false
		println "to Date is not selected properly"
		WebUI.takeScreenshot()
	}


	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/minutesCheckbox'))

	//Step6 : To verify that user is able to Generate report for Goal statistics
	'click GenerateReport button'
	WebUI.click(findTestObject('ReportsPage/generateReport'))

	WebUI.delay(GlobalVariable.LONG_DELAY)

	//Step7: To verify the rows shown in Goal statistics report

	List<WebElement> goalStatisticsList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 28)))
	int groupSize = goalStatisticsList.size()
	List<String> actualgroupList=new ArrayList<String>();

	for(int i=0;i<groupSize;i++){

		actualgroupList.add(i,goalStatisticsList.get(i).getText())

	}


	String goalStatisticsdata=CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","goalReportHeader")


	String[] goalStatisticsdataArray=goalStatisticsdata.split(',')
	def goalStatisticsdataList=new ArrayList(Arrays.asList(goalStatisticsdataArray))
	goalStatisticsdataList.unique()
	System.out.println(goalStatisticsdataList)
	boolean matchFound=true;
	for(int j=0;j<actualgroupList.size();j++)
	{

		matchFound=WebUI.verifyMatch(actualgroupList.get(j).toString().trim(), goalStatisticsdataList.get(j).toString().trim(), false)

		if(!matchFound)
		{

			break;

		}
	}

	if(matchFound)
	{
		System.out.println("GoalStatistics Columns are verified")
	}
	else{
		if(TCflag)
			TCflag=false
		System.out.println("Failed to verify the GoalStatistics Columns")

	}

	WebUI.delay(GlobalVariable.MED_DELAY)


	//Step 8: To verify the  Weekly report according to the Goal format, For < Goal A

	String goalAText = CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","goalA")
	String goalAText1=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/goalAText'))
	boolean confirm = goalAText1.trim().contains(goalAText.trim())
	if(confirm){

		System.out.println("Goal A Report is verified")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Goal A Report is not verified")
	}

	String goalAdata=CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","goalAText")
	println goalAdata


	List<WebElement> goalStatisticsList1 = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 29)))

	for(int k=1;k<goalStatisticsList1.size;k++){

		if(goalAdata.contains(goalStatisticsList1.get(k).getText()))
		{
			System.out.println("Goal A Columns are verified")
		}
		else{
			if(TCflag)
				TCflag=false
			System.out.println("Failed to verify the Goal A Columns")

		}

	}



	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step9 :To verify the  Weekly report according to the Goal format, For < Goal B
	String goalBText = CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","goalB")
	String goalBText1=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/goalBText'))
	confirm = goalBText1.trim().contains(goalBText.trim())
	if(confirm){

		System.out.println("Goal B Report is verified")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Goal B Report is not verified")
	}

	String goalBdata=CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","goalBText")
	println goalBdata

	List<String> goalStatisticsListB = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 30)))

	for(int k1=1;k1<goalStatisticsListB.size;k1++){

		if(goalBdata.contains(goalStatisticsListB.get(k1).getText()))
		{
			System.out.println("Goal B Columns are verified")
		}
		else{
			if(TCflag)
				TCflag=false
			System.out.println("Failed to verify the Goal B Columns")

		}

	}


	WebUI.delay(GlobalVariable.MED_DELAY)

	// for deleting created group

	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/welcomeLink'))
	WebUI.delay(GlobalVariable.MIN_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))
	WebUI.delay(GlobalVariable.MIN_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/ManageReportGroupsButtton'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/Summarygrp'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/deleteBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportingGroupManagement/confirmToDelBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/logoutLink'))

}
catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true
