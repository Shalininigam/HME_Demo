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

	WebUI.delay(GlobalVariable.MED_DELAY)
	//Step1 : To verify that user is able to select Groups

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportsLink'))
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.delay(GlobalVariable.MED_DELAY)
	String xpath="//span[contains(text(),'00000121')]"

	driver.findElement(By.xpath(xpath)).click()


	WebUI.delay(GlobalVariable.MED_DELAY)
	//Step2:To verify that user is able to select time measure
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/timeSelection'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/weekTimeOption'))


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

	String endDate=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","EndDate")

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

	String toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","toDate")
	if(!dateAttr.equals(toDateValue))
	{
		if(TCflag)
			TCflag=false
		println "to Date is not selected properly"
		WebUI.takeScreenshot()
	}


	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('ReportsPage/minutesCheckbox'))



	//Step4 : To save this report as template
	try{

		String templateHeading=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","templateHeading")


		String existingTemplatexpath= "//div[text()='"+templateHeading+"']/../div[2]"
		WebElement existingTemplatedeleteLink = driver.findElement(By.xpath(existingTemplatexpath));
		if(existingTemplatedeleteLink.isDisplayed()==true)
			driver.findElement(By.xpath(existingTemplatexpath)).click();
	}
	catch (Exception e) {
		println "Template does not exists"
	}

	WebUI.click(findTestObject('ReportsPage/saveTemplateChkBox'))

	WebUI.verifyElementChecked(findTestObject('ReportsPage/saveTemplateChkBox'), 10)
	WebUI.click(findTestObject('ReportsPage/templateName'))

	//Step5:To verify that user is able to enter template name

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('ReportsPage/templateName'),CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","templateHeading"))

	String s1=WebUI.getAttribute(findTestObject('ReportsPage/templateName'),"value")

	boolean execFlag = WebUI.verifyMatch(s1,CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","templateHeading"), false)
	if(!execFlag)
	{
		if(TCflag)
			TCflag=false
		println "template heading is not displayed"
	}
	WebUI.delay(GlobalVariable.MIN_DELAY)

	//Step6 : To verify that user is able to Generate report for Goal statistics
	'click GenerateReport button'
	WebUI.click(findTestObject('ReportsPage/generateReport'))

	WebUI.delay(GlobalVariable.LONG_DELAY)

	//Step7: To verify the rows shown in Goal statistics report

	List<WebElement> goalStatisticsList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 27)))
	int groupSize = goalStatisticsList.size()
	List<String> actualgroupList=new ArrayList<String>();

	for(int i=0;i<groupSize;i++){

		actualgroupList.add(i,goalStatisticsList.get(i).getText())

	}


	String goalStatisticsdata=CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","averageTimeHeader")


	String[] goalStatisticsdataArray=goalStatisticsdata.split(',')
	def goalStatisticsdataList=new ArrayList(Arrays.asList(goalStatisticsdataArray))
	goalStatisticsdataList.unique()
	System.out.println(goalStatisticsdataList)
	boolean matchFound=true;
	for(int j=0;j<actualgroupList.size();j++)
	{

		matchFound=	WebUI.verifyMatch(actualgroupList.get(j).toString().trim(), goalStatisticsdataList.get(j).toString().trim(), false)

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


	List<WebElement> goalList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 31)))
	groupSize = goalList.size()
	actualgroupList=new ArrayList<String>();

	for(int i=0;i<groupSize;i++){

		actualgroupList.add(i,goalList.get(i).getText())

	}

	String goaldata=CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","goals")


	String[] goaldataArray=goaldata.split(',')
	def goaldataList=new ArrayList(Arrays.asList(goaldataArray))
	goaldataList.unique()
	System.out.println(goaldataList)
	matchFound=true;
	for(int j=0;j<actualgroupList.size();j++)
	{

		matchFound=	WebUI.verifyMatch(actualgroupList.get(j).toString().trim(), goaldataList.get(j).toString().trim(), false)

		if(!matchFound)
		{

			break;

		}
	}

	if(matchFound)
	{
		System.out.println("Goal Columns are verified")
	}
	else{
		if(TCflag)
			TCflag=false
		System.out.println("Failed to verify the Goal Columns")

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

	String[] goalAdataArray=goalAdata.split(',')
	def goalAdataList=new ArrayList(Arrays.asList(goalAdataArray))
	//goalAdataList.unique()
	System.out.println(goalAdataList)
	matchFound=true;
	List<WebElement> goaldataList2 = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 32)))
	groupSize = goaldataList2.size()
	actualgroupList=new ArrayList<String>();

	for(int i=0;i<groupSize;i++){

		actualgroupList.add(i,goaldataList2.get(i).getText())

	}



	for(int j=1;j<=5;j++)
	{

		matchFound=	WebUI.verifyMatch(actualgroupList.get(j).toString().trim(), goalAdataList.get(j-1).toString().trim(), false)

		if(!matchFound)
		{

			break;

		}
	}

	if(matchFound)
	{
		System.out.println("Goal A Columns are verified")
	}
	else{
		if(TCflag)
			TCflag=false
		System.out.println("Failed to verify the Goal A Columns")

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

	String[] goalBdataArray=goalBdata.split(',')
	def goalBdataList=new ArrayList(Arrays.asList(goalBdataArray))
	//goalBdataList.unique()
	System.out.println(goalBdataList)
	matchFound=true;

	int i=0;

	for(int j=7;j<=11;j++)
	{

		matchFound=	WebUI.verifyMatch(actualgroupList.get(j).toString().trim(), goalBdataList.get(i).toString().trim(), false)
		i++
		if(!matchFound)
		{

			break;

		}
	}

	if(matchFound)
	{
		System.out.println("Goal B Columns are verified")
	}
	else{
		if(TCflag)
			TCflag=false
		System.out.println("Failed to verify the Goal B Columns")

	}

	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step 10: To verify the  Weekly report according to the Goal format, For < Goal C
	String goalCText = CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","goalC")
	String goalCText1=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/goalCText'))
	confirm = goalCText1.trim().contains(goalCText.trim())
	if(confirm){

		System.out.println("Goal C Report is verified")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Goal C Report is not verified")
	}

	String goalCdata=CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","goalCText")
	println goalCdata

	String[] goalCdataArray=goalCdata.split(',')
	def goalCdataList=new ArrayList(Arrays.asList(goalCdataArray))
	//goalCdataList.unique()
	System.out.println(goalCdataList)
	matchFound=true;


	i=0;
	for(int j=13;j<=17;j++)
	{

		matchFound=	WebUI.verifyMatch(actualgroupList.get(j).toString().trim(), goalCdataList.get(i).toString().trim(), false)
		i++
		if(!matchFound)
		{

			break;

		}
	}

	if(matchFound)
	{
		System.out.println("Goal C Columns are verified")
	}
	else{
		if(TCflag)
			TCflag=false
		System.out.println("Failed to verify the Goal C Columns")

	}

	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step 11: To verify the  Weekly report according to the Goal format, For < Goal D
	String goalDText = CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","goalD")
	String goalDText1=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/goalDText'))
	confirm = goalDText1.trim().contains(goalDText.trim())
	if(confirm){

		System.out.println("Goal D Report is verified")
	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Goal D Report is not verified")
	}
	String goalDdata=CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","goalDText")
	println goalDdata

	String[] goalDdataArray=goalDdata.split(',')
	def goalDdataList=new ArrayList(Arrays.asList(goalDdataArray))
	//goalDdataList.unique()
	System.out.println(goalDdataList)
	matchFound=true;
	i=0
	for(int j=19;j<=23;j++)
	{

		matchFound=	WebUI.verifyMatch(actualgroupList.get(j).toString().trim(), goalDdataList.get(i).toString().trim(), false)
		i++
		if(!matchFound)
		{

			break;

		}
	}

	if(matchFound)
	{
		System.out.println("Goal D Columns are verified")
	}


	else{
		if(TCflag)
			TCflag=false
		System.out.println("Failed to verify the Goal D Columns")

	}

	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step12: To verify the  Weekly report according to the Goal format, For > Goal E

	//application errors are there
	/*	String goalEText = CustomKeywords.'projectSpecific.Reusability.getTestData'("SummarizedReportPage","goalE")
	 String goalEText1=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/goalEText'))
	 confirm = goalEText1.trim().contains(goalEText.trim())
	 if(confirm){
	 System.out.println("Goal E Report is verified")
	 }else{
	 if(TCflag)
	 TCflag=false
	 System.out.println("Goal E Report is not verified")
	 }
	 */

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


