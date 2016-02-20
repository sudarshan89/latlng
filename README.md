LAT LONG to Timezone and UTC Date to Local date Converter
=========

Develop a small application to read a CSV with a UTC datetime, latitude and longitude columns and append the timezone the vehicle is in and the localised datetime. 

See example of CSV input and output below. We will then run this over several test files with several rows of data. 

**Input** 
2013-07-10 02:52:49,-44.490947,171.220966 

**Output** 
2013-07-10 02:52:49,-44.490947,171.220966,Pacific/Auckland,2013-07-10 14:52:49

---

![Badge](https://img.shields.io/shippable/56c782b11895ca44747475a5.svg)

## Solution (Code Kata)

1. Leverage on Google Timezone API to get timezone for a given Lat Long position.
2. Converts UTC Time into Local date time

### Test-ability

I believe testabilty is really important, as a rule unit tests should not depend on external dependencies, 
the program is designed to allow injecting stubbed Google API implementation when executed via unit tests