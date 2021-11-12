Feature: Login

@sanity
  Scenario: Successful Login with Valid Credential
    Given User Launch Chrome browser
    When User opens URL "http://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on Logout link"
    Then Page Title should be "Your store. Login"
    And close browser
 
@regression     
Scenario Outline: Login Data Driven
    Given User Launch Chrome browser
    When User opens URL "http://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on Logout link"
    Then Page Title should be "Your store. Login"
    And close browser
    
    Examples:
    | email | password |
    |admin@yourstore.com | admin |
    |admin1@yourstore.com | admin123 |
    
    
    
    
    
    
    
    
    
    
    
    
    
    