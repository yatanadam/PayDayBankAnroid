# PayDayBankAnroid
Architectural Flow

![final-architecture](https://user-images.githubusercontent.com/8522982/85200475-7145e780-b300-11ea-9e45-e466293afe2c.png)


Technical questions

How long did you spend on the coding test? What would you add to your solution if you had more time? If you didn't spend much time on the coding test then use this as an opportunity to explain what you would add.

-My time is limited so i couldnt spend much time. If i have time i use both unit and UI test.

What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.

-I use groupingBy(kotlin) function for grouping array list. Example code below;

-//group transactions by category and count each category

-val groups = adapter.items!!.groupingBy({ item ->
            item.transactionCategory
        }).eachCount()


How would you track down a performance issue in production? Have you ever had to do this?

-Use instruments and device metrics. Yes

How would you debug issues related to API usage? Can you give us an example?

-First check for api call result. Check types and conventions. For example in PayDayBank api i need to change First Name to first_name for db convention.

How would you improve the Node server’s API that you just used?

-Add security checking.
