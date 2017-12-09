// Run from command line in the test folder via
// $ mocha ContactForm-test.js --timeout=20000
// see ContactFormTest.java for Rosetta stone

const webdriver = require('selenium-webdriver');
const assert = require('assert');

const By = webdriver.By;       // DRY
const until = webdriver.until;

const driver = new webdriver.Builder()
    .forBrowser('chrome')
    .build();

describe('Contact Form to Formspree', () => {

  process.on('unhandledRejection', error => {
    throw(error); // promote promise warning to mocha error
  });
  
  beforeEach(async function() {
    await driver.navigate().to('https://www.sweetlightstudios.com/contact');
    await driver.wait(until.titleContains('South San Francisco'),10000);
  });

  it('Contact Form Button Test', async function() {
    await fillOutForm();
    await driver.findElement(By.css(".contact-button")).click();
    await driver.wait(until.titleIs('Formspree'),10000);
    await driver.navigate().back(); // come back to Sweet Light Studios
    await driver.wait(until.titleContains('South San Francisco'),10000);
    await driver.wait(until.elementIsVisible(await driver.findElement(By.css("article.contact"))),10000);
  }); 

  it('Contact Form Submit Test', async function() {
    await fillOutForm();
    //await driver.findElement(By.css(".contact-button")).click();
    await driver.findElement(By.css("form[action=\"https://formspree.io/rhbourbonnais@yahoo.com\"]")).submit();
   
    await driver.wait(until.titleIs('Formspree'),10000);
    await driver.navigate().back(); // come back to Sweet Light Studios
    await driver.wait(until.titleContains('South San Francisco'),10000);
    await driver.wait(until.elementIsVisible(await driver.findElement(By.css("article.contact"))),10000);
  }); 

  const fillOutForm = (async function() {
    const testEmail = "testytest@gmail.com";
    const testName = "Mr. Testy Test";
    const radioButtonSelector = "input[value=\"Personal Branding\"]";
    const testPhone = "831-555-1212";
    const testInfo = "I want a new picture for FB";
    await driver.wait(until.elementIsVisible(await driver.findElement(By.css("article.contact"))),10000);

    // fill out form
    await driver.findElement(By.id("formControlsEmail")).sendKeys(testEmail);
    await driver.findElement(By.name("name")).sendKeys(testName);
    await driver.findElement(By.css(radioButtonSelector)).click();
    await driver.findElement(By.name("phone")).sendKeys(testPhone);
    await driver.findElement(By.name("additionalInfo")).sendKeys(testInfo);

    // verify form filled out
    assert.equal(
      await driver.findElement(By.id("formControlsEmail")).getAttribute("value"), 
      testEmail, 
      "Email Not Correct"
    );

    assert.equal( 
      await driver.findElement(By.name("name")).getAttribute("value"),
      testName,
      "Name Not Correct"
    );

    assert(
      await driver.findElement(By.css(radioButtonSelector)).isSelected(),
      "Radio Button not selected" 
    );

    assert.equal(
      await driver.findElement(By.name("phone")).getAttribute("value"),
      testPhone, 
      "Phone Not Correct"
    );

    assert.equal(
      await driver.findElement(By.name("additionalInfo")).getAttribute("value"),
      testInfo, 
      "Info Not Correct"
    );

  });

  after(async function() {
    await driver.quit();
  });

});
