HyperLogLog
===========
A Simple Implementation of HyperLogLog Algorithm. HyperLogLog is an algorithm for estimating the cardinality 
of a multiset with a memory complexity in O(log(log(n))). The HyperLogLog strategy has several nice properties:

1. It is near-optimal in its estimation ability
2. allows you some coarse tuning on the amount of standard error you can tolerate
3. The data structures that are used for the estimation are fast, easily compressed 
and stored, and can be recombined to provide estimates of both the union and intersection of multiple sets