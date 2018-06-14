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

	//Step1: To verify that admin is able to select group

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/ZOOM_dashboard'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/setting_wheel'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/display_setting'))

	WebUI.delay(GlobalVariable.MED_DELAY)


	CustomKeywords.'uiaction.CommonUIActions.clear'(findTestObject('Display_Setting/GoalA_input'))

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('Display_Setting/GoalA_input'),"blue")

	CustomKeywords.'uiaction.CommonUIActions.clear'(findTestObject('Display_Setting/GoalB_input'))

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('Display_Setting/GoalB_input'),"Pink")


	CustomKeywords.'uiaction.CommonUIActions.clear'(findTestObject('Display_Setting/GoalC_input'))

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('Display_Setting/GoalC_input'),"Green")

	WebUI.scrollToPosition(100,1000)
	WebUI.delay(GlobalVariable.MIN_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('Display_Setting/save_btn'))

	WebUI.delay(GlobalVariable.LONG_DELAY)

	//Step2:To Verify user is able to navigate to Report page


	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/reportLink'))

	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.delay(GlobalVariable.LONG_DELAY)
	//Step3:To select store

	List<WebElement> list_stores=driver.findElements(By.xpath("//span[@class='StoreTitile level-0']")) //storeList

	String size_list=list_stores.size();
	WebUI.delay(GlobalVariable.MIN_DELAY)

	driver.findElement(By.xpath("//ul[@role='tree-node']/li["+size_list+"]")).click()


	//Step5:To select the date


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

	String data = CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","startdate_TC8")
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

	String endDate=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Enddate_goalstatics")

	String toDate="(//div[@class='rdt date-time rdtOpen']/div[@class='rdtPicker']/div/table/tbody/tr/td[text()='"+endDate+"'])[1]"

	println toDate

	WebElement toDateEle=driver.findElement(By.xpath(toDate))
	toDateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	String dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate1'),"value")

	String fromDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Fromdate_TC8")
	if(!dateAttr.equals(fromDateValue))
	{
		if(TCflag)
			TCflag=false
		println "From date is not selected properly"
		WebUI.takeScreenshot()
	}

	dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate2'),"value")

	String toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","todate_Enddate_goalstatics")
	if(!dateAttr.equals(toDateValue))
	{
		if(TCflag)
			TCflag=false
		println "to Date is not selected properly"
		WebUI.takeScreenshot()
	}

	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step6:To generate report


	'click GenerateReport button'
	WebUI.click(findTestObject('ReportsPage/generateReport'))

	WebUI.delay(GlobalVariable.LONG_DELAY)
	WebUI.delay(GlobalVariable.LONG_DELAY)

	//Step7:To verify the colour for Goal A

	WebElement colourfield= driver.findElement(By.xpath("(//table[@class='goalstatistics-table goalstatistics-table-content'])[1]//tr[1]"))

	String text=colourfield.getCssValue("background-color");

	String blue=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","blue")
	if(text.equals(blue))
	{
		System.out.println("The colour displayed for GOAL A is--- Blue")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("The colour displayed for GOAL A is--- not Blue")
	}


	//Step8:To verify the colour for Goal B

	WebElement colourfield1= driver.findElement(By.xpath("(//table[@class='goalstatistics-table goalstatistics-table-content'])[2]//tr[1]"))

	String text1=colourfield1.getCssValue("background-color");

	String pink=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","pink")

	if(text1.equals(pink))
	{
		System.out.println("The colour displayed for GOAL A is--- Pink")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("The colour displayed for GOAL A is--- not Pink")
	}


	//Step9:To verify the colour for Goal c

	WebElement colourfield2= driver.findElement(By.xpath("(//table[@class='goalstatistics-table goalstatistics-table-content'])[3]//tr[1]"))

	String text3=colourfield2.getCssValue("background-color");

	String green=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","green")

	if(text3.equals(green))
	{
		System.out.println("The colour displayed for GOAL A is--- Green")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("The colour displayed for GOAL A is--- not Green")
	}


}catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true








