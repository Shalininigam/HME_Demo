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

boolean TCflag=true
String actual=null
String expected=null

try{

	//Step 1: To navigate to the application login page

	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)

	'Verify Login Page displayed'
	actual=WebUI.getText(findTestObject('Language/L_LoginPage/userName'))
	if(actual.equalsIgnoreCase(CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","UserName"))){
		println "Login Page displayed"
	}

	else{
		if(TCflag)
			TCflag=false
		println "Login Page not displayed"
	}


	//Step 2: To navigate to the application home page

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))

	WebUI.delay(GlobalVariable.LONG_DELAY)

	'Verify Home Page displayed'

	actual=WebUI.getText(findTestObject('Language/L_HomePage/logout'))

	if(actual.equalsIgnoreCase("Logout")){
		println "Home Page displayed"
	}

	else{
		if(TCflag)
			TCflag=false
		println "Home Page not displayed"
	}

	//Step 3: To navigate to stores page
	'Click on Store link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/store'))
	WebUI.delay(GlobalVariable.MIN_DELAY)

	actual=WebUI.getText(findTestObject('Language/L_StoresPage/storeHeader'))
	if(actual.equalsIgnoreCase(CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","StoreHeader"))){
		println "Store Page displayed"
	}

	else{
		if(TCflag)
			TCflag=false
		println "Store Page not displayed"
	}

	//Step 4: To naviagate to Reporting group management screen Click on Manage Groups button

	'Click on Manage Report group button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_StoresPage/manageReportGroups'))
	WebUI.delay(GlobalVariable.MIN_DELAY)

	actual=WebUI.getText(findTestObject('Language/L_StoresPage/reportingGroupmanagement'))
	if(actual.equalsIgnoreCase(CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","ReportingGroupManagementHeader"))){
		println "Reporting group management screen displayed"
	}

	else{
		if(TCflag)
			TCflag=false
		println "Reporting group management screen not displayed"
	}

	//Step 5: To create a new Group (Click on Add New Group button)
	'Click on Add New Group button'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportingGroupManagementPage/addnewGroup_Btn'))
	WebUI.delay(GlobalVariable.MIN_DELAY)

	actual=WebUI.getText(findTestObject('Language/L_ReportingGroupDetailsPage/reportingGroupDetails_Header'))
	if(actual.equalsIgnoreCase(CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","ReportingGroupDetailsHeader"))){
		println "Reporting group details page  displayed"
	}

	else{
		if(TCflag)
			TCflag=false
		println "Reporting group details page  not displayed"
	}

	//Step 6: To navigate to Summary Reports page (Click on Reports tab)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/welcomelink1'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	'Click on Reports Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/reports_Header'))
	WebUI.delay(GlobalVariable.MIN_DELAY)

	actual=WebUI.getText(findTestObject('Language/L_ReportsPage/summaryReport'))
	if(actual.equalsIgnoreCase(CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","SummaryReportHeader"))){
		println "Reports page  displayed"
	}

	else{
		if(TCflag)
			TCflag=false
		println "Reports page not displayed"
	}

	//Step 7:To verify that weekly summary report
	//CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportsPage/firstGroup_Chk'))
	
	WebDriver driver = DriverFactory.getWebDriver()
	driver.findElement(By.xpath("//span[contains(text(),'22222')]")).click()
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportsPage/timeMeasureSelect'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportsPage/week_DD'))
	//Code for day selection

	WebUI.click(findTestObject('Language/L_ReportsPage/generateReportBtn'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	actual=WebUI.getText(findTestObject('Language/L_ReportsPage/summarizedReport'))
	if(actual.equalsIgnoreCase("Summarized Report")){
		println "Weekly summary report displayed"
	}

	else{
		if(TCflag)
			TCflag=false
		println "Weekly summary report  not displayed"
	}
	WebUI.back();
	WebUI.delay(GlobalVariable.MED_DELAY)
	//Step 8:To verify the day summary report

	//CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportsPage/firstGroup_Chk'))
	//WebDriver driver = DriverFactory.getWebDriver()
	driver.findElement(By.xpath("//span[contains(text(),'22222')]")).click()
	
	WebUI.delay(GlobalVariable.MED_DELAY)

	//WebUI.click(findTestObject('L_ReportsPage/fromDateSelector'))

	WebUI.click(findTestObject('Language/L_ReportsPage/generateReportBtn'))

	WebUI.delay(GlobalVariable.LONG_DELAY)

	actual=WebUI.getText(findTestObject('Language/L_ReportsPage/summarizedReport'))
	if(actual.equalsIgnoreCase("Summarized Report")){
		println "Daily summary report displayed"
	}

	else{
		if(TCflag)
			TCflag=false
		println "Daily summary report  not displayed"
	}

	WebUI.back();
	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step 9:To verify the daypart summary report
	driver.findElement(By.xpath("//span[contains(text(),'22222')]")).click()
	//CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportsPage/firstGroup_Chk'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportsPage/timeMeasureSelect'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportsPage/daypart_DD'))
	//Code for day selection

	WebUI.click(findTestObject('Language/L_ReportsPage/generateReportBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	actual=WebUI.getText(findTestObject('Language/L_ReportsPage/summarizedReport'))
	if(actual.equalsIgnoreCase("Summarized Report")){
		println "DayPart summary report displayed"
	}

	else{
		if(TCflag)
			TCflag=false
		println "DayPart summary report  not displayed"
	}

	WebUI.back();
	WebUI.delay(GlobalVariable.MED_DELAY)
	//Step 10:To verify the raw car data summary report

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportsPage/groupExpandarrow'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportsPage/firstStore'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportsPage/timeMeasureSelect'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportsPage/rawDataReport_DD'))

	//Code for day selection

	WebUI.click(findTestObject('Language/L_ReportsPage/generateReportBtn'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	actual=WebUI.getText(findTestObject('Language/L_ReportsPage/summarizedReport'))
	if(actual.equalsIgnoreCase("Raw Car Data Report")){
		println "Raw data summary report displayed"
	}

	else{
		if(TCflag)
			TCflag=false
		println "Raw data summary report  not displayed"
	}
	WebUI.back();
	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step 11:To naviagate to Accouns page (Click on Accounts)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/welcomelink1'))
	'Click on Accounts Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/myAccount'))
	WebUI.delay(GlobalVariable.MIN_DELAY)

	actual=WebUI.getText(findTestObject('Language/L_AccountPage/userInformation'))
	if(actual.equalsIgnoreCase("User Information")){
		println "Accounts page  displayed"
	}
	else{
		if(TCflag)
			TCflag=false
		println "Accounts page not displayed"
	}
	//Step 12:To naviagate to Uesrs page (Click on Users)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/welcomelink'))
	'Click on Users Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/users'))

	WebUI.delay(GlobalVariable.MIN_DELAY)

	actual=WebUI.getText(findTestObject('Language/L_UserPage/addUser'))
	if(actual.equalsIgnoreCase("Add a User")){
		println "Users page  displayed"
	}
	else{
		if(TCflag)
			TCflag=false
		println "Users page not displayed"
	}
}

catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true

