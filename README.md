TradesMan
=========

TradesMan is a Java application designed to implement a simple, automatic currency trading algorithm.
Although it was initially designed for currency trading, TradesMan could be developed for use in trading stocks.

A number of currencies may be traded using this algorithm.
A base or "account" currency is used as a global currency in which personal holdings are stored, and foreign or "exchange" currencies can be traded.
All transactions must occur between the base or "account" currency, and an exchange currency.
Foreign or "exchange currencies can not be traded with each other at present.


### How TradesMan Works
When a user places an investment in an exchange currency, initially, ten percent of the amount invested is safely stored in account currency, without being invested.
This provides a safe-guard against collateral risk.
As time progresses, and the algorithm earns profit based on the initial investment, a small percentage of profits are, in turn, safely stored in account currency.
Once the amount safely stored exceeds a certain margin, the safely stored currency can be automatically re-invested similarly to the process described above.
If this procedure is followed, as long as the algorithm makes a total profit across trading periods, a large amount of risk is negated, whilst earning maximum profit.
This system, however, works best for long-term investments.

An issue that could be faced when trading using the procedure describe above, especially when trading long-term, is the "crashing" of a currency, or semi-permanent devaluation.
To negate this risk, TradesMan is designed to ensure that currency trades are diversified as much as possible, particularly when re-investing secured funds.
These functions, however, can be overriden by the user.

### Using TradesMan
Unfortunately, TradesMan is currently under development and not intended for use at the moment.
Feel free to fork this repository and submit changes :palm_tree: