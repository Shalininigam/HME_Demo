package projectSpecific

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import internal.GlobalVariable
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.kms.katalon.core.configuration.RunConfiguration
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Date;
import java.lang.String;

import org.apache.commons.io.FilenameUtils
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath
import org.openqa.selenium.interactions.Actions;



public class Reusability{
	@Keyword
	def login(String  username,String password){
		WebUI.setText(findTestObject('LoginPage/Username'), username)
		WebUI.setText(findTestObject('LoginPage/Password'), password)
		WebUI.click(findTestObject('LoginPage/Login'))
	}

	@Keyword
	public String getTestData(String sheetName,String testdataName) throws IOException{

		def objectDir = RunConfiguration.getProjectDir()+"/Data Files" ;
		def objectfileName= objectDir+"/TestData.xlsx";

		println objectDir
		println objectfileName
		String testdata =null;

		try
		{


			FileInputStream fis = new FileInputStream(objectfileName);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName)

			// Iterate through each row heading one by one

			Row row = sheet.getRow(0);
			Row row1 = sheet.getRow(1);

			// For each row, iterate through all the columns until matching testdata is not found
			int noOfColumns = sheet.getRow(0).getLastCellNum();
			String cellValue=null;
			for(int i=0;i<noOfColumns;i++)
			{

				Cell cell = row.getCell(i);
				if(cell!=null)
				{

					switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							int value =cell.getNumericCellValue();
							cellValue=Integer.toString(value);
							break;
						case Cell.CELL_TYPE_STRING:
							cellValue= cell.getStringCellValue();
					}

					if(cellValue.equals(testdataName))
					{

						Cell cell1 = row1.getCell(i);
						if(cell1!=null)
						{

							switch (cell1.getCellType())
							{
								case Cell.CELL_TYPE_NUMERIC:
									int value =cell1.getNumericCellValue();
									cellValue=Integer.toString(value);
									break;
								case Cell.CELL_TYPE_STRING:
									cellValue= cell1.getStringCellValue();
							}


							testdata=cellValue

							break;
						}//if
					}
				}


			}//for loop

			fis.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return testdata;

	}

	@Keyword
	private File getLatestFilefromDir(String dirPath){
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}


	@Keyword
	private boolean readCSVFile(String csvFileName)
	{

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFileName));
			int index=1;
			while ((line = br.readLine()) != null)
			{

				// use comma as separator
				println line
				//String[] elements = line.split(cvsSplitBy);
				//   if((index%2!=0) && (index >1))
				//{
				//println line
				//   System.out.println("Group1" +  elements[0] + " , Store=" +  elements[1] );
				//   }
				index++


			}

		}


		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Keyword
	private String readpdfContents(String pdfFileName)
	{     PDDocument document=null
		try
		{
			File file = new File(pdfFileName);
			//Reading the pdf file
			document = PDDocument.load(file);
			if( !document.isEncrypted() ){
				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition( true );
				PDFTextStripper Tstripper = new PDFTextStripper();

				int count = document.numberOfPages;

				for(int i=1;i<=count;i++)
				{

					Tstripper.setStartPage(i);
					Tstripper.setEndPage(i);
					String pageText = Tstripper.getText(document);

					String[] lines = pageText.split("\\r?\\n");
					println "PAGE COUNT"+ i
					for (String line : lines) {
						System.out.println(line);
					}

				}

			}
		}catch(Exception e){
			e.printStackTrace();

		}

	}

	@Keyword
	public  List<String> checkemailandverifyattachment()
	{
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.navigateToUrl("https://mail.yahoo.com");
		WebUI.delay(10)
		WebElement email = driver.findElement(By.xpath("//input[@id='login-username']"));
		email.sendKeys("sangeetha_gokul@yahoo.com");
		List<String> list1 = null;

		driver.findElement(By.xpath("//input[@value='Next']")).click();
		WebUI.delay(10)
		/* Below 2 lines of code is to locate the password text box and put the valid password of the user */
		WebElement pwd = driver.findElement(By.xpath("//input[@id='login-passwd']"));
		pwd.sendKeys("Harish123");
		WebUI.delay(15)
		/* below line is to locate button and perform click event */
		driver.findElement(By.xpath("//button[@id='login-signin']")).click();

		WebUI.delay(15)


		String home = System.getProperty("user.home");
		String downloadFolderPath = home+"\\Downloads";
		println downloadFolderPath

		List<WebElement> emailSubjects= driver.findElements(By.xpath("//span[@data-test-id='message-subject']"))
		String subject="HME CLOUD";
		for(int i =0; i<emailSubjects.size();i++)
		{
			String sub = emailSubjects[i].getText();
			if(sub.contains(subject))
			{

				emailSubjects[i].click();
				WebUI.delay(10)
				WebElement element = driver.findElement(By.xpath("//div[@data-test-id='attachment-container']"));

				Actions action = new Actions(driver);

				action.moveToElement(element).build().perform();

				WebUI.delay(2)

				driver.findElement(By.xpath("//a[@data-test-id='attachment-download']")).click();

				WebUI.delay(10)

				String downloadedFile= getLatestFilefromDir(downloadFolderPath)

				println downloadedFile
				String ext1 = FilenameUtils.getExtension(downloadedFile);
				if(ext1.equals("pdf"))
				{
					list1=readpdfContentsinlist(downloadedFile)
				}
				else if(ext1.equals("csv"))
				{
					list1=readCSVFileContents(downloadedFile)
				}
				else
					println "file extension not found"
				break;

			}
			WebUI.delay(5)

			driver.findElement(By.xpath("//span[@role='presentation']")).click();
			WebUI.delay(10)
			driver.findElement(By.xpath("//span[contains(text(),'Sign out')]")).click();
			WebUI.delay(10)
			//driver.quit()
			//WebUI.delay(5)

		}
		return list1;
	}

	@Keyword
	private List<String> readCSVFileContents(String csvFileName)
	{
		List<String> lines = new ArrayList<String>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int lineno=0;
		try {

			br = new BufferedReader(new FileReader(csvFileName));
			int index=1;
			while ((line = br.readLine()) != null)
			{

				// use comma as separator
				if(line.contains(","))
				{			
					String[] elements = line.split(cvsSplitBy);
					String elementStr=null;
					String output=null;
					for(int i=0;i<elements.size();i++)
					{
						output = elements[i].replaceAll("\\s","");
						output=output.trim();
						if(elementStr==null)
						
						elementStr= output+" ";
						else 
						elementStr+=output+" ";
					}
					
				
					
					lines[lineno]=elementStr;
					println "line"+elementStr
				}
				else
				 	 lines[lineno]= line;
				lineno++;
				
				
			}

			
		}


		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return lines
	}


	@Keyword
	private List<String> readpdfContentsinlist(String pdfFileName)
	{     PDDocument document=null
		List<String> linesList= new ArrayList<String>();
		try
		{
			File file = new File(pdfFileName);
			//Reading the pdf file
			document = PDDocument.load(file);
			if( !document.isEncrypted() ){
				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition( true );
				PDFTextStripper Tstripper = new PDFTextStripper();

				int count = document.numberOfPages;
				int linecount=0;
				for(int i=1;i<=count;i++)
				{

					Tstripper.setStartPage(i);
					Tstripper.setEndPage(i);
					String pageText = Tstripper.getText(document);

					String[] lines = pageText.split("\\r?\\n");
					println "PAGE COUNT"+ i
					for (String line : lines) {
						System.out.println(line);
						linesList[linecount]= line;
						linecount++;
					}

				}

			}
		}catch(Exception e){
			e.printStackTrace();

		}
		return linesList;
	}



}


