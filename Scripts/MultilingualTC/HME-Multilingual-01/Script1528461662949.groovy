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
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.chrome.ChromeDriver
import com.kms.katalon.core.configuration.RunConfiguration
import org.openqa.selenium.remote.DesiredCapabilities

boolean TCflag=true
String actual=null
String expected=null
try{

	DesiredCapabilities jsCapabilities = DesiredCapabilities.chrome()
	ChromeOptions options = new ChromeOptions()
	Map<String, Object> prefs = new HashMap<>()
	prefs.put("intl.accept_languages","fr")
	options.setExperimentalOption("prefs", prefs)
	jsCapabilities.setCapability(ChromeOptions.CAPABILITY, options)

	//System.setProperty("webdriver.chrome.driver", "S://Software//ChromeDriver//chromedriver.exe");
	String chromedriverPath=RunConfiguration.getProjectDir()+"/chromedriver.exe"

	//System.setProperty("webdriver.chrome.driver", "S://Software//ChromeDriver//chromedriver.exe");

	System.setProperty("webdriver.chrome.driver", chromedriverPath);

	ChromeDriver driver = new ChromeDriver(options)
	DriverFactory.changeWebDriver(driver)
	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step 1: To navigate to the application login page
	driver.get("https://hme-dev-public-cloud-app.azurewebsites.net/")
	WebUI.maximizeWindow()

	WebUI.delay(GlobalVariable.MED_DELAY)
	//WebUI.delay(GlobalVariable.LONG_DELAY)




	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))

	WebUI.delay(GlobalVariable.LONG_DELAY)


	//Step 8: Verify downloaded file in French

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/reports_Header'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportsPage/firstGroup_Chk'))

	WebUI.click(findTestObject('Language/L_ReportsPage/fromDateSelector'))

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String monthText=WebUI.getText(findTestObject('Language/L_ReportsPage/monthText'))
	//String monthText="June 2018"


	String[] monthTextArray= monthText.split(" ")
	String month = monthTextArray[0]

	String monthTextValue="March"

	if(!monthTextValue.equals(month))
	{
		while(!monthTextValue.equals(month))
		{
			WebUI.click(findTestObject('Language/L_ReportsPage/previousDateSelector'))
			monthText=WebUI.getText(findTestObject('Language/L_ReportsPage/monthText'))
			monthTextArray= monthText.split(" ")
			month = monthTextArray[0]

		}

	}

	String data = "1"
	String startDate="(//td[text()='"+data+"'])[1]"
	println startDate
	WebElement startdateEle=driver.findElement(By.xpath(startDate))
	startdateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.click(findTestObject('Language/L_ReportsPage/toDateSelector'))

	monthText=WebUI.getText(findTestObject('Language/L_ReportsPage/monthText2'))
	monthTextArray= monthText.split(" ")
	month = monthTextArray[0]

	if(!monthTextValue.equals(month))
	{
		while(!monthTextValue.equals(month))
		{
			WebUI.click(findTestObject('Language/L_ReportsPage/previousDateSelector2'))
			monthText=WebUI.getText(findTestObject('Language/L_ReportsPage/monthText2'))
			monthTextArray= monthText.split(" ")
			month = monthTextArray[0]

		}


	}

	String endDate="1"

	String toDate="(//td[text()='"+endDate+"'])[3]"

	WebElement toDateEle=driver.findElement(By.xpath(toDate))
	toDateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String dateAttr =WebUI.getAttribute(findTestObject('Language/L_ReportsPage/selectedDate1'),"value")

	String fromDateValue ="03/01/2018"
	if(!dateAttr.equals(fromDateValue))
	{
		if(TCflag)
			TCflag=false
		println "From date is not selected properly"
		WebUI.takeScreenshot()
	}

	dateAttr =WebUI.getAttribute(findTestObject('Language/L_ReportsPage/selectedDate2'),"value")

	String toDateValue ="03/01/2018"
	if(!dateAttr.equals(toDateValue))
	{
		if(TCflag)
			TCflag=false
		println "to Date is not selected properly"
		WebUI.takeScreenshot()
	}

	WebUI.click(findTestObject('Language/L_ReportsPage/generateReportBtn'))

	WebUI.delay(GlobalVariable.LONG_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportsPage/csv_download'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportsPage/download_csv'))


	List<String> s=CustomKeywords.'projectSpecific.Reusability.checkemailandverifyattachment'()

	System.out.println("the text is---"+s.get(0))

	String englishmatch=s.get(0).substring(0, 5)
	System.out.println("1----"+englishmatch)

	if(englishmatch.contains("D") && englishmatch.contains("a") && englishmatch.contains("y")){

		System.out.println("Downloaded file  displayed in French")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Downloaded file  not displayed in French")

	}


	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)
	
	WebUI.delay(GlobalVariable.MIN_DELAY)

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))

	WebUI.delay(GlobalVariable.MIN_DELAY)






	//Step 2: To navigate to the application home page



	String welcomeMessage=CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","WelcomeMessage")

	String welcome=CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","WelcomeLink")


	String stores=CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","Stores")

	String users=CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","Users")

	String myAccount=CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","My account")



	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_HomePage/welcomeMessage'), welcomeMessage)

	actual=WebUI.getText(findTestObject('Language/L_HomePage/welcomeMessage'))
	if(actual.contains(welcomeMessage)){

		println "Welcome message displayed "
	}

	else{
		if(TCflag)
			TCflag =false
		println "Welcome message not displayed "
		WebUI.takeScreenshot()
	}

	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_HomePage/welcomelink'), welcome)

	actual=WebUI.getText(findTestObject('Language/L_HomePage/welcomelink'))
	WebUI.verifyMatch(actual, welcome,false)



	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_HomePage/store'), stores)

	actual=WebUI.getText(findTestObject('Language/L_HomePage/store'))
	WebUI.verifyMatch(actual, stores,false)




	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_HomePage/users'), users)
	actual=WebUI.getText(findTestObject('Language/L_HomePage/users'))
	WebUI.verifyMatch(actual, users,false)



	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_HomePage/myAccount'), myAccount)

	actual=WebUI.getText(findTestObject('Language/L_HomePage/myAccount'))
	WebUI.verifyMatch(actual, myAccount,false)



	//Step 3: To verify that all the pages in the application is displayed in French

	'Click on Stores Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/store'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	//Verify text in french

	actual=WebUI.getText(findTestObject('Language/L_StoresPage/manageLeaderboardGroups'))
	expected=CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","ManageLeaderBoardGroups")
	WebUI.verifyMatch(actual,expected ,false)

	actual=WebUI.getText(findTestObject('Language/L_StoresPage/manageReportGroups'))
	expected=CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","ManageReportGroups")
	WebUI.verifyMatch(actual,expected,false )

	//click on Welcome
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/welcomelink1'))
	WebUI.delay(GlobalVariable.MED_DELAY)


	'Click on Users Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/users'))

	//Verify text in french
	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_UserPage/addUser'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","AddaUser"))


	actual=WebUI.getText(findTestObject('Language/L_UserPage/addUser'))
	expected=CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","AddaUser")

	WebUI.verifyMatch(actual, expected,false)


	//click on Welcome
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/welcomelink'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	'Click on My Account'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/myAccount'))

	//Verify text in french

	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_AccountPage/userInformation'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","UserInformation"))

	actual=WebUI.getText(findTestObject('Language/L_AccountPage/userInformation'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","UserInformation"),false)

	//click on Welcome
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/welcomelink'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step 4:To verify that French is displayed in React pages


	'Click on Stores Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/store'))

	'Click on Manage Report Groups'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_StoresPage/manageReportGroups'))

	//Verify text

	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_StoresPage/reportingGroupmanagement'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageEnglish","Gestiondugroupedereporting"))

	actual=WebUI.getText(findTestObject('Language/L_StoresPage/reportingGroupmanagement'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","ReportingGroupManagement"),false)

	'Click on Add New Group'

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportingGroupManagementPage/addNewGroup_Btn'))
	//Verify text
	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_ReportingGroupDetailsPage/reportingGroupDetails_Header'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageEnglish","Détailsdugroupederapports"))

	actual=WebUI.getText(findTestObject('Language/L_ReportingGroupDetailsPage/reportingGroupDetails_Header'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","ReportingGroupDetails"),false)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/welcomelink1'))

	'Click on Reports Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/reports_Header'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	//Verify text

	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_ReportsPage/summaryReport'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageEnglish","Récapitulatifs"))

	actual=WebUI.getText(findTestObject('Language/L_ReportsPage/summaryReport'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","SummaryReports"),false)

	//CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/hmeCloud_TopHeader'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/welcomelink1'))


	//Step 5:To verify that French is displayed in coldfusion pages

	//Navigate to Store,User, Roles Page
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/store'))

	actual=WebUI.getText(findTestObject('Language/L_StoresPage/manageLeaderboardGroups'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","ManageLeaderBoardGroups"),false)

	actual=WebUI.getText(findTestObject('Language/L_StoresPage/manageReportGroups'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","ManageReportGroups"),false)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/welcomelink1'))

	'Click on Users'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/users'))

	//Verify text in french
	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_UserPage/addUser'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","AddaUser"))

	actual=WebUI.getText(findTestObject('Language/L_UserPage/addUser'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","AddaUser"),false)

	'Click on Roles'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_RolesPage/roles'))

	//Verify text in french
	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_RolesPage/addaRole'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","AddaRole"))

	actual=WebUI.getText(findTestObject('Language/L_RolesPage/addaRole'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","AddaRole"),false)


	'Click on Dashboard'

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/dashboard'))

	//Verify text in french
	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_DashboardPage/dashboardHeaderMessage'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","HMECloudDashboard"))

	actual=WebUI.getText(findTestObject('Language/L_DashboardPage/dashboardHeaderMessage'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","HMECloudDashboard"),false)



	//Step 6:To verify that all the error messages are displayed in French

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/welcomelink'))

	//click Users
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/users'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	//click Roles

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_RolesPage/roles'))

	//click Add a Role
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_RolesPage/addaRole'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_RolesPage/save_Btn'))

	//Verify error message in English
	String errormessage=WebUI.getText(findTestObject('Language/L_RolesPage/roleName_ErrorMsg'))

	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_RolesPage/roleName_ErrorMsg'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageEnglish","Vousdevezindiquerunnomderôle"))

	actual=WebUI.getText(findTestObject('Language/L_RolesPage/roleName_ErrorMsg'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","Rolenamemaynotbeblank"),false)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/welcomelink'))

	//Step 7:To verify that all the pop up messages are displayed in French


	//Nagivate >Stores >Manage Report Groups
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/store'))
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_StoresPage/manageReportGroups'))

	'click first record and click Delete'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportingGroupManagementPage/firstGroup'))

	//scroll down

	WebUI.scrollToPosition(100,1000)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportingGroupManagementPage/delete_Btn'))

	'Verify Popup message in English'
	WebUI.delay(GlobalVariable.MED_DELAY)

	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_ReportingGroupManagementPage/delete_PopupMessage'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageEnglish","Confirmerpoursupprimer"))

	actual=WebUI.getText(findTestObject('Language/L_ReportingGroupManagementPage/delete_PopupMessage'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","ConfirmtoDelete"),false)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportingGroupManagementPage/no_Btn'))

	//scrool up

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_ReportingGroupDetailsPage/cancel_Btn'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/welcomelink1'))



	//Step 8:To verify that all the downloaded files are displayed in French


	//click on xls link

	/*           String filePath='C:\\Users\\brajeshk\\Downloads'
	 String downloadedFile= CustomKeywords.'projectSpecific.Reusability.getLatestFilefromDir'(filePath)
	 println downloadedFile
	 if(downloadedFile!=null)
	 {
	 String xlFile=RunConfiguration.getProjectDir()+"\\"
	 String fileName= CustomKeywords.'projectSpecific.Reusability.copyFile'(downloadedFile,xlFile)
	 List<String> columnHeaders= CustomKeywords.'projectSpecific.Reusability.getRecord'(xlFile+fileName,"hmeCloudDashboard_French")
	 }              
	 */





	//Step 9:To verify that French language is displayed in all the drop down boxes

	//click on Dashboard

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/dashboard'))

	'Click on Metrics dropdown'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_DashboardPage/metrics'))

	//Verify text in french

	'Verify Dropdown value in French'
	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_DashboardPage/laneTotal'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","LaneTotal"))

	actual=WebUI.getText(findTestObject('Language/L_DashboardPage/laneTotal'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","LaneTotal"),false)

	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_DashboardPage/laneTotal2'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","LaneTotal2"))

	actual=WebUI.getText(findTestObject('Language/L_DashboardPage/laneTotal2'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","LaneTotal2"),false)


	//Step 10:To verify the help text in all the help icons


	//Step 11:Verify all the headers and footer sections in the pages

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/welcomelink'))

	'Verify Headers and Footers in French'

	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_HomePage/welcomelink'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","WelcomeLink"))

	actual=WebUI.getText(findTestObject('Language/L_HomePage/welcomelink'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","WelcomeLink"),false)

	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_HomePage/dashboard'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","ZOOMDashboard"))

	actual=WebUI.getText(findTestObject('Language/L_HomePage/dashboard'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","ZOOMDashboard"),false)

	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_HomePage/reports_Header'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","Reports"))

	actual=WebUI.getText(findTestObject('Language/L_HomePage/reports_Header'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","Reports"),false)

	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_HomePage/myAccount_Header'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","MyAccount"))

	actual=WebUI.getText(findTestObject('Language/L_HomePage/myAccount_Header'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","MyAccount"),false)

	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_HomePage/settings_Header'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","Settings"))

	actual=WebUI.getText(findTestObject('Language/L_HomePage/settings_Header'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","Settings"),false)



	//scrollldown

	WebUI.scrollToPosition(100,1000)
	WebUI.delay(GlobalVariable.MIN_DELAY)

	//CustomKeywords.'uiaction.CommonUIActions.verifyContentText'(findTestObject('Language/L_HomePage/privacyPolicy_Footer'), CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","PrivacyPolicy"))

	actual=WebUI.getText(findTestObject('Language/L_HomePage/privacyPolicy_Footer'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","PrivacyPolicy"),false)

	//Step 12:Verify the help text

	'Navigate to settings help'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/settingIcon'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Language/L_HomePage/help_Link'))

	'Verify Frequenty asked questions to be displayed in French languange'

	actual=WebUI.getText(findTestObject('Language/L_HelpPage/frequentlyAskedQues'))
	WebUI.verifyMatch(actual, CustomKeywords.'projectSpecific.Reusability.getTestData'("MultiLanguageFrench","FrequentlyAskedQuestions"),false)

}



catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true
