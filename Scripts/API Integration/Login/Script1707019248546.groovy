import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper as JsonSlurper
import groovy.json.*

response = WS.sendRequest(findTestObject('API - Login'))

def jsonSlurper = new JsonSlurper()

def jsonResponse = jsonSlurper.parseText(response.getResponseBodyContent())

GlobalVariable.accessToken = jsonResponse.data.accessToken

GlobalVariable.refreshToken = jsonResponse.data.refreshToken

GlobalVariable.id = jsonResponse.data.user.id

GlobalVariable.email = jsonResponse.data.user.email

GlobalVariable.name = jsonResponse.data.user.name

GlobalVariable.role = jsonResponse.data.user.role

GlobalVariable.officeId = jsonResponse.data.user.officeId

GlobalVariable.companyId = jsonResponse.data.user.companyId

GlobalVariable.companyName = jsonResponse.data.user.company_name

WebUI.openBrowser('')

//localStorage key with json object
class LocalStorage {
	String accessToken
	String companyId
	String company_name
	String email
	String id
	String name
	String officeId
	String refreshToken
	String role
}
def jsonObject = new LocalStorage(
	accessToken: GlobalVariable.accessToken,
	companyId: GlobalVariable.companyId,
	company_name: GlobalVariable.companyName,
	email: GlobalVariable.email,
	id: GlobalVariable.id,
	name: GlobalVariable.name,
	officeId: GlobalVariable.officeId,
	refreshToken: GlobalVariable.refreshToken,
	role: GlobalVariable.role,
)
def jsonString = JsonOutput.toJson(jsonObject)

WebUI.navigateToUrl('https://kasirdemo.belajarqa.com')

WebUI.executeJavaScript("localStorage.setItem('KASIRAJA_USER','" + jsonString + "')", null)

