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
import java.util.regex.Pattern;

boolean TCflag=true
try{

	//Step1: To verify that public admin is able to login as store admin
	WebUI.navigateToUrl(GlobalVariable.SuperAdminUrl)
	WebUI.delay(GlobalVariable.LONG_DELAY)
	//	WebUI.delay(GlobalVariable.LONG_DELAY)
	WebDriver driver = DriverFactory.getWebDriver()

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","publicadmin"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","Password"))

	WebUI.delay(GlobalVariable.LONG_DELAY)


	//Step2:To verify that logged in user name is displayed correctly

	WebUI.verifyElementPresent(findTestObject('HomePage/loggedinUser'),10)

	String loggedInUser=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('HomePage/loggedinUser'))
	loggedInUser=loggedInUser.trim()

	String loggedinText =CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","loggedinText")

	if(loggedInUser.equals(loggedinText))
		System.out.println("Verified logged in user Text")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("Could not verify logged in user Text")
	}


	//Step3: To verify that store admin name is displayed
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/UsersLink1'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	String searchUserText2= CustomKeywords.'projectSpecific.Reusability.getTestData'("UsersPage","userSearchText2")

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('UsersPage/searchTextBox'),searchUserText2)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/searchButton'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/viewUserLink1'))

	WebUI.refresh()
	WebUI.delay(GlobalVariable.LONG_DELAY)
	//WebUI.delay(GlobalVariable.LONG_DELAY)
	String  viewAsUser=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('UsersPage/viewAsUserText'))
	viewAsUser=viewAsUser.trim()
	String viewAsUserText =CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","viewAsUserText")

	if(viewAsUser.equals(viewAsUserText))
		System.out.println("viewAsUserText  is displayed")
	else
	{

		if(TCflag)
			TCflag=false
		System.out.println("viewAsUserText is not displayed")
	}

	WebUI.delay(GlobalVariable.MED_DELAY)


	//Step4: To navigate to users page
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/UsersLink1'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/userlink2'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	WebUI.verifyElementPresent(findTestObject('UsersPage/users'),10)
	WebUI.verifyElementPresent(findTestObject('UsersPage/viewEditButton'),10)
	WebUI.delay(GlobalVariable.MIN_DELAY)
	//Step4:To verify that user names are displayed correctly
	List<WebElement> userList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 19)))
	int size1= userList.size()
	if(size1<=0)
	{
		if(TCflag)
			TCflag=false
		System.out.println("User List is not displayed")
	}
	else
		System.out.println("User List is displayed")
	
	//Step5: To navigate to stores page
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink1'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	WebUI.verifyElementPresent(findTestObject('StorePage/storeListHeading2'), 5)
	//	 WebUI.verifyElementPresent(findTestObject('StorePage/userStoreList'),10)
	
	//Step6 : To navigate to Dashboard page
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/UsersLink1'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/userlink2'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	searchUserText2= CustomKeywords.'projectSpecific.Reusability.getTestData'("UsersPage","userSearchText2")
	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('UsersPage/searchTextBox'),searchUserText2)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/searchButton'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/viewUserLink1'))
	WebUI.delay(GlobalVariable.LONG_DELAY)
//	WebUI.delay(GlobalVariable.LONG_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/dashBoardLink'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	String  dashboardHeading=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('DashBoardPage/dashboardHeading'))
	dashboardHeading=dashboardHeading.trim()
	String dashboardHeadingText =CustomKeywords.'projectSpecific.Reusability.getTestData'("UsersPage","dashboardHeadingText")
	if(dashboardHeading.equals(dashboardHeadingText))
		System.out.println("dashboardHeadingText  is displayed")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("dashboardHeadingText is not displayed")
	}
	
	//Step7: To verify the stores displayed in dashboard page
	WebUI.verifyElementPresent(	findTestObject('DashBoardPage/dashBoardStoreList'),10)
	String dayHighlighted= WebUI.getCSSValue(findTestObject('DashBoardPage/dayPartLink'), "cursor")
	if(dayHighlighted.contains("default"))
	{
		System.out.println("Day Part dashboard will be shown by default")
	}
	else{
		if(TCflag)
			TCflag=false
		System.out.println("Day dashboard is not shown by default")
		WebUI.takeScreenshot()
	}
	WebUI.delay(GlobalVariable.MED_DELAY)
	
	//Step 8: To verify the stores displayed in dashboard page for Rolling hour
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('DashBoardPage/rollingHourLink'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	String  rollingHourHeading=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('DashBoardPage/reportHeading'))
	rollingHourHeading=rollingHourHeading.trim()
	String rollingHourHeadingText =CustomKeywords.'projectSpecific.Reusability.getTestData'("UsersPage","rollingHourHeadingText")
	if(rollingHourHeading.equals(rollingHourHeadingText))
		System.out.println("rollingHourHeadingText  is displayed")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("rollingHourHeadingText is not displayed")
	}
	WebUI.verifyElementPresent(findTestObject('DashBoardPage/dashBoardStoreList'),10)
	
	
	WebUI.delay(GlobalVariable.MED_DELAY)
	//Step 9: To verify the stores displayed in dashboard page for Hour
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('DashBoardPage/hourLink'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	String  hourHeading=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('DashBoardPage/reportHeading'))
	hourHeading=hourHeading.trim()
	String hourHeadingText =CustomKeywords.'projectSpecific.Reusability.getTestData'("UsersPage","hourHeadingText")
	if(hourHeading.equals(hourHeadingText))
		System.out.println("hourHeadingText  is displayed")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("hourHeadingText is not displayed")
	}
	WebUI.verifyElementPresent(findTestObject('DashBoardPage/dashBoardStoreList'),10)
	
	WebUI.delay(GlobalVariable.MED_DELAY)
	//Step 10: To verify the stores displayed in dashboard page for Daypart
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('DashBoardPage/dayPartLink'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	String  dayPartHeading=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('DashBoardPage/reportHeading'))
	dayPartHeading= dayPartHeading.trim()
	String  dayPartHeadingText =CustomKeywords.'projectSpecific.Reusability.getTestData'("UsersPage","dayPartText")
	if(dayPartHeading.equals(dayPartHeadingText))
		System.out.println("dayPartHeadingText  is displayed")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("dayPartHeadingText is not displayed")
	}
	WebUI.verifyElementPresent(findTestObject('DashBoardPage/dashBoardStoreList'),10)
	WebUI.delay(GlobalVariable.MED_DELAY)
	//Step 11: To verify the stores displayed in dashboard page for Week
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('DashBoardPage/weekLink'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	String  weekHeading=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('DashBoardPage/reportHeading'))
	weekHeading= weekHeading.trim()
	String  weekHeadingText =CustomKeywords.'projectSpecific.Reusability.getTestData'("UsersPage","weekText")
	if(weekHeading.equals(weekHeadingText))
		System.out.println("weekHeadingText  is displayed")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("weekHeadingText is not displayed")
	}
	WebUI.verifyElementPresent(findTestObject('DashBoardPage/dashBoardStoreList'),10)
	WebUI.delay(GlobalVariable.MED_DELAY)
	//Step 12: To verify the stores displayed in dashboard page for Month
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('DashBoardPage/monthLink'))
	WebUI.delay(GlobalVariable.MED_DELAY)
	String  monthHeading=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('DashBoardPage/reportHeading'))
	monthHeading= monthHeading.trim()
	String  monthHeadingText =CustomKeywords.'projectSpecific.Reusability.getTestData'("UsersPage","monthText")
	if(monthHeading.equals(monthHeadingText))
		System.out.println("monthHeadingText  is displayed")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("monthHeadingText is not displayed")
	}
	WebUI.verifyElementPresent(findTestObject('DashBoardPage/dashBoardStoreList'),10)
	WebUI.delay(GlobalVariable.MED_DELAY)
	//Step 13: To navigate to Reports page
	
	WebUI.delay(GlobalVariable.LONG_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportLink'))
	
	WebUI.refresh()
	WebUI.delay(GlobalVariable.LONG_DELAY)
	
	//Step14: Weekly summary report will be shown
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/weekTimeOption'))

	List<WebElement> reportStoreList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 25)))

	int reportStoreListSize= reportStoreList.size()
	int index=0;
	Pattern pattern = Pattern.compile("[0-9]+");
	int count=0;
	for(int i=0;i<reportStoreListSize;i++)
	{

		String txt=reportStoreList.get(i).getText()
		index=i+1
		boolean matched=pattern.matcher(txt).matches()
		if(matched){
			String xpath ="//div[@class='saved-reports']/ul/li["+index+"]/span[2]"
			driver.findElement(By.xpath(xpath)).click()
			count++
			if(count>2)
				break;
		}

	}

	//to generate weekly report
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/generateReport'))

	WebUI.delay(GlobalVariable.LONG_DELAY)
	String  summaryHeading=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/summaryReport'))
	summaryHeading=summaryHeading.trim()
	String summaryHeadingText =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","summaryHeading")

	if(summaryHeading.equals(summaryHeadingText))
		System.out.println("Weekly Report is displayed")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("Weekly Report is not displayed")
	}


	//Step15: Click on download and select PDF

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('SummarizedReportPage/downloadBtn'))

	WebUI.verifyElementPresent(findTestObject('SummarizedReportPage/csvOption'),10)
	WebUI.verifyElementPresent(findTestObject('SummarizedReportPage/pdfOption'),10)
	

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/SummarizedReportPage/pdfOption'))

	
	WebUI.delay(GlobalVariable.LONG_DELAY)
	
	
	String confirmationText= CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","confirmationText")
	String actualconfirmationText= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/emailConfirmation'))

	boolean confirm = WebUI.verifyMatch(confirmationText,actualconfirmationText,false)
	if(confirm){

		System.out.println("Email Verification received")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Email Verification not received")
	}
	WebUI.delay(GlobalVariable.MED_DELAY)
	WebUI.back()
	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step16: Click on download and select CSV

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('SummarizedReportPage/downloadBtn'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/SummarizedReportPage/csvOption'))

	WebUI.delay(GlobalVariable.LONG_DELAY)
	
	confirmationText= CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","confirmationText")
	actualconfirmationText= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/emailConfirmation'))

	confirm = WebUI.verifyMatch(confirmationText,actualconfirmationText,false)
	if(confirm){

		System.out.println("Email Verification received")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Email Verification not received")
	}

	WebUI.back()
	WebUI.delay(GlobalVariable.LONG_DELAY)

	WebUI.back()
	WebUI.delay(GlobalVariable.LONG_DELAY)
	//Step17: Select store 2,select time measure as Day and click on [Generate Report] button

/*	reportStoreList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 25)))

	reportStoreListSize= reportStoreList.size()

	index=0;
	Pattern pattern1 = Pattern.compile("22222\$");
	
	count=0;
	for(int i=0;i<reportStoreListSize;i++)
	{

		String txt=reportStoreList.get(i).getText()
		index=i+1
		boolean matched=pattern1.matcher(txt).matches()
		if(matched){
			//count++
			//if(count==2)
			//{
				String xpath ="//div[@class='saved-reports']/ul/li["+index+"]/span[2]"
				driver.findElement(By.xpath(xpath)).click()
				break;
			}
		}
*/
	driver.findElement(By.xpath("//span[contains(text(),'22222')]")).click()

	//}

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/dayTimeOption'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	//to generate day summary report
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/generateReport'))
	WebUI.delay(GlobalVariable.LONG_DELAY)
	WebUI.delay(GlobalVariable.LONG_DELAY)
	summaryHeading=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/summaryReport'))
	summaryHeading=summaryHeading.trim()
	summaryHeadingText =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","summaryHeading")

	if(summaryHeading.equals(summaryHeadingText))
		System.out.println("Day summary report is displayed")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("Day summary report is not displayed")
	}

	WebUI.back()
	WebUI.delay(GlobalVariable.MED_DELAY)



	//Step18: Select store 3,select time measure as Daypart and click on [Generate Report] button

	/*reportStoreList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 25)))

	reportStoreListSize= reportStoreList.size()
	index=0;
	for(int i=0;i<reportStoreListSize;i++)
	{

		String txt=reportStoreList.get(i).getText()
		index=i+1
		boolean matched=pattern.matcher(txt).matches()
		if(matched){
			count++
			if(count==3)
			{
				String xpath ="//div[@class='saved-reports']/ul/li["+index+"]/span[2]"
				driver.findElement(By.xpath(xpath)).click()
				break;
			}
		}

	}*/

	driver.findElement(By.xpath("//span[contains(text(),'22222')]")).click()
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/daypartTimeOption'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/generateReport'))

	WebUI.delay(GlobalVariable.LONG_DELAY)
	summaryHeading=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/summaryReport'))
	summaryHeading=summaryHeading.trim()
	summaryHeadingText =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","summaryHeading")
	if(summaryHeading.equals(summaryHeadingText))
		System.out.println("Day part summary report is displayed")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("Daypart summary report is not displayed")
	}
	WebUI.back()
	WebUI.delay(GlobalVariable.MED_DELAY)


	//Step19: Select store 3,select time measure as raw car data and click on [Generate Report] button

	/*reportStoreList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 25)))

	reportStoreListSize= reportStoreList.size()
	index=0;
	count=0;
	for(int i=0;i<reportStoreListSize;i++)
	{

		String txt=reportStoreList.get(i).getText()
		index=i+1
		boolean matched=pattern.matcher(txt).matches()
		if(matched){
			count++
			if(count==3)
			{
				String xpath ="//div[@class='saved-reports']/ul/li["+index+"]/span[2]"
				driver.findElement(By.xpath(xpath)).click()
				break;
			}
		}
	}*/
	
	driver.findElement(By.xpath("//span[contains(text(),'22222')]")).click()
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Object Repository/ReportsPage/rawDataReportTimeOption'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/generateReport'))

	WebUI.delay(GlobalVariable.LONG_DELAY)
	summaryHeading=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/rawCarDataHeading'))
	summaryHeading=summaryHeading.trim()
	summaryHeadingText =CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","rawCarDataReportHeading")
	if(summaryHeading.equals(summaryHeadingText))
		System.out.println("Raw car data report  is displayed")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("Raw car data report is not displayed")
	}
	WebUI.back()
	WebUI.delay(GlobalVariable.MED_DELAY)
	
	//Step 20: To navigate to Device settings history page

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/UsersLink1'))

	WebUI.delay(GlobalVariable.LONG_DELAY)
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/userlink2'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	searchUserText2= CustomKeywords.'projectSpecific.Reusability.getTestData'("UsersPage","userSearchText2")

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('UsersPage/searchTextBox'),searchUserText2)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/searchButton'))

	WebUI.delay(GlobalVariable.LONG_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/viewUserLink1'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/deviceSettingsLink'))
	WebUI.delay(GlobalVariable.MIN_DELAY)
	WebUI.verifyElementPresent(findTestObject('UsersPage/deviceSettingstoreList'),10)

	//Step 21: To verify that public admin is able to view details

	String storeviewDetailsLink= "(//table/tbody/tr/td[text()='"+searchUserText2 +"']/../td[1])[1]"
	driver.findElement(By.xpath(storeviewDetailsLink)).click()
	String  createdBy=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('UsersPage/deviceSettingscreatedBy'))
	createdBy= createdBy.trim()
	if(createdBy.equals(searchUserText2))
	{
		System.out.println("CreatedBy Text is displayed")
	}
	else
	{
		System.out.println("CreatedBy Text is not displayed")
	}

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/closePopUp'))
	
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/Admin_SignOut'))

}catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true
