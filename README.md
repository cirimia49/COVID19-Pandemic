# COVID19-Pandemic

Provides better insights on the Coronavirus pandemic. 
Analyzes the amount of money a government puts in their healthcare system vs. the coronavirus fatality rate.

In times of a coronavirus pandemic, understanding the factors that lead to high mortality rate of a country are crucial. 
This project was built using the most updated data from the API https://covidapi.info/api/v1 which was built upon the dataset from John Hopkins University. 

A country spends a lot of money on healthcare, some a lot more than others. 
The United States of America spends 17% of its GDP on health care (which amounts to $ 3.504 trillion), whereas Iran only 8.66% of its GDP.

This project correlates a country’s Case Fatality Rate (CFR) to its Healthcare expenditure. 
The case fatality rate at this point is still a variable and not a scalar value since a lot of these cases are still active, 
i.e. an outcome of either recovered or death has not yet been reported. 
The outcome of our project can be seen better once this pandemic end.

The formula used for calculating the CFR is: 
CFR = deaths / deaths + recovered 

Moreover, this project can also be used as a way of tracking how the CFR changes in a country as the virus progresses.
The reason why this project plots the CFR was that according to the worldometers website, upon asking the NHC, 
why during the initial phase, Wuhan’s CFR was so much higher than the national level, 
the NHC official replied that it was for lack of resources, 
citing as an example that there were only 110 critical care beds in the three designated hospitals where most of the cases were sent.

For running: 
- download the zip file or clone the repository on your local machine;
- open with IntelliJ and have JDK 11 as set up;
- click on gradle -> CoronaPandemic -> Tasks -> application -> run;
