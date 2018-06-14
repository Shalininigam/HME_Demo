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
try{
	WebUI.navigateToUrl(GlobalVariable.SuperAdminUrl)

	//Step1:To verify that public admin is able to login as store admin
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
	String  welcomeUser=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('HomePage/welcomeUserText'))
	welcomeUser=welcomeUser.trim()
	String welcomeText =CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","welcomeText")

	//Step3: To verify that store admin name is displayed
	if(welcomeUser.equals(welcomeText))
		System.out.println("Welcome user Text is displayed")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("Welcome user Text is not displayed")
	}

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/UsersLink1'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/viewUserLink1'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step4: To navigate to Roles page
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/rolesLink'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	WebDriver driver = DriverFactory.getWebDriver()

	//Step5:To verify that public admin is able to view role details

	List<WebElement> rolesList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 18)))
	String roleName= CustomKeywords.'projectSpecific.Reusability.getTestData'("RolesPage","roleName")
	boolean found= false
	for(int i=0;i<rolesList.size();i++)
	{
		if(rolesList.get(i).getText().equals(roleName))
		{

			found=true
			break;

		}
	}
	if(found)
	{
		println roleName + "Role found"
	}
	else{

		if(TCflag)
		{
			TCflag=false
			println "Role Name not found"
			WebUI.takeScreenshot()
		}

	}

	//Step 6: To verify that public admin will be able to edit role details
	String roleViewEditXpath ="//div[@class='ctable']/table/tbody/tr/td[text()='"+roleName+"']/../td[1]"

	driver.findElement(By.xpath(roleViewEditXpath)).click()


	WebUI.verifyElementPresent(findTestObject('RolesPage/roleName'), 10)
	//Step7: To verify that role details screen has the updated changes
	String roleAttr =WebUI.getAttribute(findTestObject('RolesPage/roleName'),"value")
	WebUI.verifyMatch(roleAttr,roleName, false)
	WebUI.verifyElementPresent(findTestObject('RolesPage/permissionsHeading'),10)
	String permissionValue=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('/RolesPage/permissionsHeading'))

	String permissionText =CustomKeywords.'projectSpecific.Reusability.getTestData'("RolesPage","permissionText")

	if(permissionValue.equals(permissionText ))
		System.out.println("Verified permissions Heading")
	else
	{
		if(TCflag){
			TCflag=false
			System.out.println("Could not verify permissions Heading")
		}

	}

	WebUI.verifyElementPresent(findTestObject('RolesPage/addRoleCheckBox'),10)
	WebUI.verifyElementPresent(findTestObject('RolesPage/addUserCheckBox'),10)
	WebUI.verifyElementPresent(findTestObject('RolesPage/editRoleCheckBox'),10)
	WebUI.verifyElementPresent(findTestObject('RolesPage/editUserCheckBox'),10)


	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('RolesPage/saveButton'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step8: Click on view as user

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/UsersLink1'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/roleEditLink'))

	String userPageHeading= CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('UsersPage/userHeading'))

	String userText= CustomKeywords.'projectSpecific.Reusability.getTestData'("UsersPage","userHeading")

	if(userPageHeading.contains(userText))
		System.out.println("User Heading Text is displayed")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("User Heading Text is not displayed")
	}

	WebUI.delay(GlobalVariable.MED_DELAY)
	//Step9: To click on RolesLink
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/rolesLink'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step 10: To verify that public admin will not be able to create any roles - Step 3
	WebUI.verifyElementNotPresent(findTestObject('RolesPage/addNewRoleButton'),10)


	List<WebElement> userRolesList = driver.findElements(By.xpath(findTestData('OR_file').getValue(2, 24)))

	if(userRolesList.size() >0)
		System.out.println("Roles are displayed")
	else
	{
		if(TCflag){
			TCflag=false
			System.out.println("Roles are not displayed")
		}

	}
	//Step11: Click on UserLink
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/UsersLink1'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step12: To verify that public admin will not be able to delete any roles - Step 2

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/companyAdminEditLink'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	//Step13: To verify that public admin will not be able to delete any roles - Step 3

	WebUI.verifyElementNotPresent(findTestObject('RolesPage/deleteRoleLink'),10)


	WebUI.delay(GlobalVariable.MED_DELAY)

}catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true
