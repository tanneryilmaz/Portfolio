#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h>
//All interest rates should be passed as values less than 1. For example, a rate of 67% is 
//passed as 0.67, not as 67. 

typedef struct person{
  double salary; //person's yearly salary
  double savings; //amount of money in person's savings account
  double percentSaving; //percentage of salary they put into savings account each year
  double percentChecking; //percentage of salary they put into checking account each year
  double checking; //amount of money in person's checking account
  double debt; // the sum of the person's credit card and student loan debt
  double debtInterest; //yearly interest rate on credit card and student loan debt
  double housePrice; //cost of the house that the person wants to buy
  double mortgageLoan; // the amount of mortgage debt the person carries
  double mortgageDown; // when the person has this percentage of the housePrice in their checking account, they will make a down payment on a house
  int mortgageTerm; //number of years the person will take to pay off mortgage
  double yearsWithDebt; // number of years person has credit card and student loan debt
  int yearsRented; // number of years the person rented an apartment
  double debtPaid; // total amount the person paid to pay off credit card and student loans
  double savingsRate; //the yearly interest that is paid in their savings account
  double minPayment; //percentage of their credit card and student loan debt the person                       //pays off each month. 
  double addlPayment; //The additional monthly payment made on their credit card debt
  double mortgageInterest; // Yearly percent interest on the home mortgage loan 
  double apartmentCost; //monthly cost of renting an apartment
  bool house; // true if person owns a house. False otherwise.
  double wealthDivisor; //person's wealth is divided by this number at end of each year
}person;

// typedef struct inputParams{ DELETE THIS CODE
// }inputParams; DELETE THIS CODE

/* Pays interest to the person's savings account.
rate parameter is the interest rate that applies annualy. 
Running this method simulates the passing of 1 year */
void savingsPlacement(person* person, double rate){
  person -> savings *= (rate + 1);
}

/* Running this function simulates 1 year of the person paying off their credit card and student loan debt. rate parameter is the percentage of their credit card and student loan debt that the person pays off each month. initialAmount parameter is the amount of debt the 
person has at the beginning of the year. THE VALUE PASSED FOR INITIALAMOUNT MUST BE THE 
SAME AS THE VALUE THAT THE PERSON'S DEBT DATA MEMBER IS SET TO. THIS FUNCTION WILL LIKELY BEHAVE ERRONEOUSLY IF THE PERSON'S DEBT DATA MEMBER IS NOT THE SAME AS THE VALUE BEING PASSED TO THIS FUNCTION FOR INITIALAMOUNT */
void debt(person* person, double initialAmount, double rate, double addlPay){
  
  int monthsSimulated = 1;
  double monthlyPayment;

  while(monthsSimulated <= 12 && (person -> debt) > 0){
    monthlyPayment = (person -> debt) * rate + addlPay;
    person -> debt -= monthlyPayment;
    person -> debtPaid += monthlyPayment;
    monthsSimulated++;
  }
  person -> debt *= (1 + person -> debtInterest); // Debt is compounded 20% annually
  person -> yearsWithDebt++;  
}

//Running this function simulates 1 year of the person renting an apartment
void rent(person* person, double rentamt){
  person -> checking -= 12 * rentamt;
  (person -> yearsRented)++;
}

// Running this funciton simulates 1 year of the person paying off their home mortgage loan
void house(person* person, double housePrice, double interestRate, double mortgageTerm){
  int N = mortgageTerm * 12; //Calculated by multiplying 30 years by 12 months per year
  double r = interestRate / 12.0; //monthlyRate is the interest charged per month
  double monthlyPayment;
  int monthsSimulated = 1;

  // See relevant formula at this URL: https://en.wikipedia.org/wiki/Fixed-rate_mortgage
  monthlyPayment = (r * housePrice) / (1 - pow(1 + r, -N));
  
  while(monthsSimulated <= 12){
    person -> checking -= monthlyPayment;
    person -> mortgageLoan -= monthlyPayment;
    monthsSimulated++;
  }
}

//simulationLength is the number of years to be simulated
int* simulator(person* person, int simulationLength){
  
  int yearsSimulated = 1; //The number of years that have been simulated 
  double wealth = person->savings + person->checking - person->debt - person->mortgageLoan;
  double housePrice = person -> housePrice;
  int mortgageTerm = person -> mortgageTerm;
  double yearlySalary = person -> salary;
  
  static int wealthArr[101]; // this needs to be static because local variables dissapear when method is exited. The maximum number of years that can be simulated is 100 years
  wealthArr[0] = wealth; //first array element stores the person's initial wealth 
  
  while(yearsSimulated <= simulationLength){
    person -> savings += yearlySalary * (person -> percentSaving); 
    person -> checking += yearlySalary * (person -> percentChecking);
    
    if(person -> house == true){
      house(person, housePrice, person -> mortgageInterest, mortgageTerm);
    }
    else{
      if(person -> checking > housePrice * (person -> mortgageDown)){
        person -> checking -= housePrice * (person -> mortgageDown);
        person -> mortgageLoan = housePrice - (housePrice * (person -> mortgageDown));
        person -> house = true;
      }
      else{
        rent(person, person -> apartmentCost);
      }
    }
    
    if(person -> debt > 0){
      debt(person, person -> debt, person -> minPayment, person -> addlPayment);
    }
    savingsPlacement(person, person -> savingsRate);
    wealth = (person->savings + person->checking - person->debt - person->mortgageLoan);
    wealth /= person -> wealthDivisor;
    wealthArr[yearsSimulated] = (int)wealth;
    yearsSimulated++;
  }

  return wealthArr;
}

//Initializes the person struct's values with the values in the params array
void initializeParams(person* person, double* params){

  person -> yearsWithDebt = 0;
  person -> yearsRented = 0;
  person -> debtPaid = 0;
  person -> house = false;

  person -> debt = params[1]; 
  person -> minPayment = params[2]; 
  person -> addlPayment = params[3]; 
  person -> housePrice = params[4];
  person -> mortgageDown = params[5];
  person -> mortgageInterest = params[6];
  person -> savingsRate = params[7];
  person -> apartmentCost = params[8];
  person -> salary = params[9];
  person -> mortgageTerm = params[10];
  person -> percentSaving = params[11];
  person -> percentChecking = params[12];
  person -> wealthDivisor = params[13];
  person -> debtInterest = params[14];
  person -> savings = params[15];
  person -> checking = params[16];
}

double* readFile(){
  
  const int parameterCount = 17; //Number of input parameters in input file
  FILE *myFile;
  myFile = fopen("input.txt", "r"); //Opening input file

  //read file into array
  static double paramArray[parameterCount];
  int i;

  for (i = 0; i < parameterCount; i++){
    fscanf(myFile, "%lf", &paramArray[i] );
  }

  fclose(myFile);
  return paramArray; //Returns an array containing the input parameters
}

void writeToFile(person person, int* personWealth, int yearsToSimulate){

  FILE *outputFile; 
  outputFile = fopen ("output.txt", "w");

  fprintf(outputFile, "Initial wealth: $%d\n", personWealth[0]);

  for(int i = 1; i < yearsToSimulate + 1; i++){
    fprintf(outputFile, "Year %d: $%d\n", i, personWealth[i]);
  }

  fprintf(outputFile, "Debt Paid: %lf\n", person.debtPaid);
  fprintf(outputFile, "Years With Debt: %lf", person.yearsWithDebt);
  
  
  fclose(outputFile);
}

int main(void) {
  
  person person;
  int yearsToSimulate;
  int* personWealth; //Pointer to array that stores person's wealth values
  double* paramArray;
  paramArray = readFile();

  yearsToSimulate = paramArray[0];

  initializeParams(&person, paramArray);  

  personWealth = simulator(&person, yearsToSimulate);

  writeToFile(person, personWealth, yearsToSimulate);

  printf("Initial wealth: $%d\n", personWealth[0]);
  for(int i = 1; i < yearsToSimulate + 1; i++){
    printf("Year %d: $%d\n", i, personWealth[i]);
  }
  
  printf("Debt Paid: %lf\n", person.debtPaid);
  printf("Years With Debt: %lf", person.yearsWithDebt);

  return 0;
}