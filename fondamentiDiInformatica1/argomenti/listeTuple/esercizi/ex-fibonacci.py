def printArray(array):
  if input("Wanna print the series in console? [yes/NO] ") == "yes": 
    i = 0
    while i <= len(array) - 1:
      print(array[i])
      i += 1

def searchElement(array):
  toCheck = int(input("Insert element to check existence of in the series: "))

  if toCheck > array[len(array) - 1]:
    state = "Sorry, not found, restart the program and try to use a bigger list."
  else:
    for i in range(len(array) - 1):
      if array[i] == toCheck:
        state = "Found {} at index {}".format(toCheck, i)
        break
      else:
        state = "Sorry, not found."
  

  return state

def findElement(array):
  searchType = int(input("Insert 0 to find an element, insert 1 to print the element at a specific index: "))
  if searchType == 0:
    state = searchElement(array)
    print(state)
  elif searchType == 1:
    index = int(input("Insert index: "))
    print(array[index])
  else: 
    print("Use either 0 or 1 when you choose.")

def initializeSeries():
  fibSeries = [0, 1]
  wantedElements = int(input("How many elements of the series? "))
  seriesLength = len(fibSeries)
  wantedElements -= 1
  while seriesLength <= wantedElements:
    secondLast, last = fibSeries[seriesLength-1], fibSeries[seriesLength-2]
    
    toAdd = last + secondLast
    fibSeries.append(toAdd)
    
    seriesLength = len(fibSeries)

  return fibSeries

fibSeries = initializeSeries()

printArray(fibSeries)
while True:
  findElement(fibSeries)