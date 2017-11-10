// Run from command line via
// $ mocha AirVisual-test.js --timeout=20000
// see AirVisualTest.java for Rosetta stone

const webdriver = require('selenium-webdriver');

const By = webdriver.By;       // DRY
const until = webdriver.until;

const driver = new webdriver.Builder()
    .forBrowser('chrome')
    .build();

describe('AirVisual Widget', () => {

  process.on('unhandledRejection', error => {
    throw(error); // promote promise warning to mocha error
  });
  
  beforeEach(async function() {
    await driver.navigate().to('https://www.sweetlightstudios.com/contact');
    await driver.wait(until.titleContains('South San Francisco'),10000);
  });

  it('Uses widget to nav to AirVisual', async function() {
    await driver.findElement(By.css('article.contact'));
    const widget = await driver.findElement(By.name('airvisual_widget'));
    await driver.wait(until.elementIsVisible(widget),10000);
    await driver.wait(until.elementIsEnabled(widget),10000);
    await widget.click();
    await driver.wait(until.titleContains('AirVisual - Real-time air quality'),10000);
    await driver.navigate().back();
    await driver.wait(until.titleContains('South San Francisco'),10000);
    let element = await driver.findElement(By.css("article.contact"));
    await driver.wait(until.elementIsVisible(element),10000)
    }); 

  afterEach(async function() {
    await driver.quit();
  });

});
