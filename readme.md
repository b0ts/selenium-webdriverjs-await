Selenium Webdriver Javascript Await
By Robert Bourbonnais

Shows how to create Selenium tests using Javascript
and makes use of await and async functions to handle javascript promises 
in a more natural way.

It contains three Selenium test files in .js and matching files in .java
Note: If you know how to write Selenium test in java, you can use the matching files as a Rosetta stone.

It will allow you to compare and contrast the classic 
Maven + Webdriver + Java + JUnit + typology

with the newer
Maven + Webdriver + Javascript + Mocha typology

Note: Both solutions need to have Chromedriver and Firefoxdriver (Gecko) in the path

In order to run the Javascript version, you will need to have Node, NPM, and Mocha installed globally. 

To run the example:
First clone the repo to a local folder
Then navigate to selenium-webdriverjs-await and 
install the dependencies via NPM
$ npm install

You can run all tests via NPM
$ npm run test

For the details on Javascript Selenese see the docs here:

https://seleniumhq.github.io/selenium/docs/api/javascript/module/selenium-webdriver/

http://seleniumhq.github.io/selenium/docs/api/javascript/module/selenium-webdriver/index_exports_By.html

https://seleniumhq.github.io/selenium/docs/api/javascript/module/selenium-webdriver/lib/until.html

