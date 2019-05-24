Feature:  Prepayment Charge Calculator

  Scenario Outline: Validate Error Test
    Given User have Application url to launch
    When User enters Outstanding Balance as "<OutstandingBalance>"
    And Remaining term as "<Year>" years "<Month>" months
    And Payment frequency as "<Frequency>"
    And Payment amount as "<Amount>"
    And Current Interest Rate as "<InterestRate>"
    And click on Calculate Button
    Then "<ErrorMessage>" is displayed

    Examples:
      |OutstandingBalance |Year	|Month	|Frequency	|Amount	|InterestRate	|ErrorMessage	|
      |450000        			|10		|5			|Bi-Weekly	|6500		|							|Must be a value between 0 and 40% |					
			|550000        			|7		|5			|Weekly			|5500		|							|Must be a value between 0 and 40% |
			|650000        			|9		|7			|Bi-Weekly	|5500		|							|Must be a value between 0 and 40% |

 Scenario Outline: Validate Result Test
    Given User have Application url to launch
    When User enters Outstanding Balance as "<OutstandingBalance>"
    And Remaining term as "<Year>" years "<Month>" months
    And Payment frequency as "<Frequency>"
    And Payment amount as "<Amount>"
    And Current Interest Rate as "<InterestRate>"
    And click on Calculate Button
    Then 3 Month Penalty is "<3 Month Penalty>" displayed
    And Interest Rate Differential is "<Interest Rate Differential>" displayed

    Examples:
      |OutstandingBalance |Year	|Month	|Frequency	|Amount	|InterestRate	|3 Month Penalty| Interest Rate Differential|
      |450000        			|10		|5			|Bi-Weekly	|6500		|6.45					|7256.25				|302343.75									|