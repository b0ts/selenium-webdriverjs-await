// Run from command line in the test folder via
// $ mocha MissingAlt-test.js --timeout=10000
// see MissingAltTest.java for Rosetta stone
// Resolving missing alt tags is important for SEO rating and
// website accessibility compliance
// https://www.w3.org/standards/webdesign/accessibility 

const assert = require('assert');
const webdriver = require('selenium-webdriver');

const By = webdriver.By;       // DRY
const until = webdriver.until;

const driver = new webdriver.Builder()
    .forBrowser('chrome')
    .build();

describe('Check for Missing Alt Tags', () => {

  process.on('unhandledRejection', error => {
    throw(error); // promote promise warning to mocha error
  });

  beforeEach(async function() {
    await driver.navigate().to('https://www.sweetlightstudios.com');
    await driver.wait(until.titleContains('South San Francisco'),10000);
  });

  it('Check Alt Tags on Home Page', async function() {
    let skipSrc = "https://dbeg14ta4byob.cloudfront.net/img/logo/";
    //skipSrc = " "; // comment this in to see test fail

    const images = await driver.findElements(By.css("img"));
    images.forEach(async function (image) {
      let srcText = await image.getAttribute("src");
      let altText = await image.getAttribute("alt");
      assert(altText.length > 0 || srcText.startsWith(skipSrc), 'No altText for: ' + srcText);       
    });
    await driver.wait(until.titleContains('South San Francisco'),10000);
  });

  afterEach(async function() {
    await driver.quit();
  });

});
