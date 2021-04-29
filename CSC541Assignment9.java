/*
course: CSC 541
project: Assignment 9/10
date: 04/25/21
author: Katlyn Murphy and Benjamin Smith
purpose: The purpose of this assignment is study JWebUnit, download the JWebUnit software 
and practice it with a web site of your choice by designing test cases to test the selected 
web site. Write a report about your study and opinions of the tool, including your testing 
results in the report. Prepare a 10-minutes video presentation about your study and testing results. 
 */

import org.junit.Test;
import net.sourceforge.jwebunit.junit.JWebUnit;
import static net.sourceforge.jwebunit.junit.JWebUnit.*;


public class CSC541Assignment9 {
	
	//started with testing EKU Library but couldn't continue due to errors  
	@Test
    public void testEKULibrary() { //testing search
    	JWebUnit.setScriptingEnabled(false); //have to use this in order to get it to access the library website
    	beginAt("https://libguides.eku.edu/library"); 
        assertTitleEquals("Home - Research Guides at Eastern Kentucky University");
        assertButtonPresent("eb-submit-button");
        setTextField("ebscohostsearchtext", "Mans Search For Meaning Viktor E. Frankl");
        clickButton("eb-submit-button");
        submit();
        assertHeaderEquals("h1 title", "Result List: Mans Search For Meaning Viktor E. Frankl");
        assertTitleEquals("Result List: Mans Search for Meaning Viktor E. Frankl: Research Databases");
        //JWebUnit.setScriptingEnabled(true);
    }
	
	//tried testing Facebook but couldn't continue because of errors
	@Test
    public void testFacebookLogin() { //testing fb login
		JWebUnit.setScriptingEnabled(false); //have to use this in order to get it to access the facebook after login website
        beginAt("https://www.facebook.com/"); //start with the web page specified in @Before
        assertTitleEquals("Facebook - Log In or Sign Up");
        setTextField("email", "testingEKU@gmail.com");
        setTextField("pass", "EKU2021!");
        submit();
        assertTitleEquals("Enter Login Code to Continue");
        assertTitleEquals("Facebook");
        //error because login code is randomly generated and always different
        //JWebUnit.setScriptingEnabled(true);
    }
	
	//Moved to testing Wikipedia 
    @Test 
    public void testingLinksAndImages() {
        beginAt("https://en.wikipedia.org/wiki/Main_Page");
        assertTitleEquals("Wikipedia, the free encyclopedia");
        clickLinkWithExactText("Contents"); 
        assertTitleEquals("Wikipedia:Contents - Wikipedia");
        clickLinkWithExactText("Philosophy");
        assertTitleEquals("Wikipedia:Contents/Philosophy and thinking - Wikipedia");
        assertImagePresent("//upload.wikimedia.org/wikipedia/commons/thumb/d/d2/The_Thinker_Musee_Rodin.jpg/150px-The_Thinker_Musee_Rodin.jpg",
        "The Thinker, a statue by Auguste Rodin, is often used to represent philosophy.");
        clickLinkWithExactText("The Philosophy Portal");
        assertTitleEquals("Portal:Philosophy - Wikipedia");
        assertImagePresent("//upload.wikimedia.org/wikipedia/commons/thumb/1/1d/JagadguruRamabhadracharya010.jpg/100px-JagadguruRamabhadracharya010.jpg",
        "Rāmabhadrācārya meditating on the banks of Mandakini river during a Payovrata. He is seated in the Sukhasana pose with fingers folded in the Chin Mudra.");
    }
    
    @Test
    public void searchWiki() { //test search bar on wiki 
    	//search for food
        beginAt("https://en.wikipedia.org/wiki/Main_Page");
        assertTitleEquals("Wikipedia, the free encyclopedia");
        assertButtonPresent("searchButton");
        assertElementPresent("searchInput");
        assertFormPresent("searchform");
        assertFormElementPresent("search");
        assertFormElementPresent("go");
        setTextField("search", "food");
        clickButton("searchButton");
        assertTitleEquals("Food - Wikipedia");
        //search for cars after searching for food
        assertButtonPresent("searchButton");
        assertElementPresent("searchInput");
        assertFormPresent("searchform");
        assertFormElementPresent("search");
        assertFormElementPresent("go");
        setTextField("search", "cars");
        clickButton("searchButton");
        assertTitleEquals("Car - Wikipedia");
        //search for something that doesn't exist
        assertButtonPresent("searchButton");
        assertElementPresent("searchInput");
        assertFormPresent("searchform");
        assertFormElementPresent("search");
        assertFormElementPresent("go");
        setTextField("search", "ajhsfjbasijdbf");
        clickButton("searchButton");
        assertTitleEquals("ajhsfjbasijdbf - Search results - Wikipedia");
    }
    
    @Test
    public void createAccount() { //test create an account on wiki 
    	beginAt("https://en.wikipedia.org/wiki/Main_Page");
        assertTitleEquals("Wikipedia, the free encyclopedia");
        clickLinkWithExactText("Create account");
        assertTitleEquals("Create account - Wikipedia");
        assertFormElementPresent("wpName");
        assertFormElementPresent("wpPassword");
        assertFormElementPresent("retype");
        assertFormElementPresent("email");
        setTextField("wpName", "testing");
        setTextField("wpPassword", "xxxxxx");
        setTextField("retype", "xxxxxx");
        setTextField("email", "xxxxxx@gmail.com");
        clickButton("wpCreateaccount");
        submit();
        //cannot test captcha - that is the point of a good captcha
        //this should be the next title page after testing captcha
        //assertTitleEquals("Wikipedia, the free encyclopedia");
    }

    @Test
    public void changeAccountPassword() { //test change password on wiki 
        beginAt("https://en.wikipedia.org/wiki/Main_Page");
        assertTitleEquals("Wikipedia, the free encyclopedia");
        clickLinkWithExactText("Log in");
        assertTitleEquals("Log in - Wikipedia");
        assertFormElementPresent("wpName");
        assertFormElementPresent("wpPassword");
        assertFormElementPresent("wploginattempt");
        setTextField("wpName", "testingEKU");
        setTextField("wpPassword", "EKU2021!");
        clickButton("wpLoginAttempt");
        submit();
        clickLinkWithExactText("Preferences");
        assertTitleEquals("Preferences - Wikipedia");
        clickElementByXPath("//*[@id=\"ooui-php-22\"]/span/a/span[2]"); //path to click change password button 
        assertTitleEquals("Change credentials - Wikipedia");
        assertFormElementPresent("password");
        assertFormElementPresent("retype");
        setTextField("password", "EKU2021!!");
        setTextField("retype", "EKU2021!!");
        clickElementByXPath("//*[@id=\"ooui-php-7\"]/button/span[2]"); //path to click change credientals button
        assertTitleEquals("Preferences - Wikipedia"); 
    } 
                
    @Test
    public void changeLang() { //test change language on wiki
    	beginAt("https://en.wikipedia.org/wiki/Main_Page");
        assertTitleEquals("Wikipedia, the free encyclopedia");
        clickLinkWithExactText("Español");
        assertTitleEquals("Wikipedia, la enciclopedia libre");
        clickLinkWithExactText("Italiano");
        assertTitleEquals("Wikipedia, l'enciclopedia libera");
        clickLinkWithExactText("English");
        assertTitleEquals("Wikipedia, the free encyclopedia");
        clickLinkWithExactText("Polski");
        assertTitleEquals("Wikipedia, wolna encyklopedia");
    }
    
    @Test 
    public void donate() { //test donate button on wiki
	   	beginAt("https://en.wikipedia.org/wiki/Main_Page");
		assertTitleEquals("Wikipedia, the free encyclopedia");
		clickLinkWithExactText("Donate");
		assertTitleEquals("Make your donation now - Wikimedia Foundation");
		assertRadioOptionPresent("amount","Other");
		assertRadioOptionPresent("frequency","onetime");
		//clickRadioOption("amount","5");
		//clicking the radioOption as listed above gave reference
		//errors, so when with inputting a value in "other"
		assertFormElementPresent("amount");
		setTextField("amount","1");
		assertSubmitButtonPresent();
		submit();
		assertTitleEquals("Wikipedia, the free encyclopedia");
   }  
   
}
