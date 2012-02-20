#include <iostream>
#include <iomanip>
#include <string>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
using namespace std;
//Declare function prototypes
void intro();
void header (string message);
void hold();
void randomArray (double salesArray[4][6]);
void displayArray (double displayArray[4][6]);
void displaySalesByDivision(double salesArray[4][6]);
void salesDifference (double salesArray[4][6], double differenceArray[4][6]);
double calcSalesDifference (double salesArray[4][6], int row, int col);
void displaySalesDifference (double differenceArray[4][6]);
void totalCompanySales (double salesArray[4][6]);
double calcTotalCompanySales (double salesArray[4][6], int row);
void totalCompanySalesDifference (double differenceArray[4][6]);
double calcTotalCompanyDifference (double differenceArray[4][6], int row);
void averageCompanySales (double salesArray[4][6]);
double calcAverageCompanySales (double salesArray[4][6], int row);
void highestQuarterlySalesDivision (double salesArray[4][6]);
int getHighestDivision (double salesArray[4][6], int row);
double calcHighestQuarterlySales (double salesArray[4][6], int row);
void lowestQuarterlySalesDivision (double salesArray[4][6]);
int getLowestDivision (double salesArray[4][6], int row);
double calcLowestQuarterlySales (double salesArray[4][6], int row);
void end();

int main()
{
	srand(time(0));
	double corporateSales[4][6] = {}; //Array to hold randomized sales
	double corporateSalesDifference[4][6] = {}; //Array to hold sale difference between quarters
	
	intro();
	randomArray(corporateSales);
	
	header("Initial sales array:");
	displayArray(corporateSales);
	hold();
	
	header("Sales for each division: ");
	displaySalesByDivision(corporateSales);
	hold();
	
	header("Differences in sales from quarter to quarter: ");
	salesDifference(corporateSales, corporateSalesDifference);
	hold();
	
	header("Total company sales per quarter: " );
	totalCompanySales(corporateSales);
	hold();
	
	header("Total company sales difference per quarter: ");
	totalCompanySalesDifference(corporateSalesDifference);
	hold();
	
	header("Average company sales per quarter: ");
	averageCompanySales(corporateSales);
	hold();
	
	header("The highest selling division per quarter: ");
	highestQuarterlySalesDivision(corporateSales);
	hold();
	
	header("The lowest selling division per quarter: ");
	lowestQuarterlySalesDivision(corporateSales);
	hold();
	
	end();
	fflush(stdin);
	cin.get();
	return 0;
}

//Intro screen and come up with values for corporateSales
void intro ()
{
	cout << "Welcome to the Quarterly Sales Program. This program will create " << endl;
	cout << "a random array with 4 x 6 values and use them as sales for a " << endl;
	cout << "mock company. It will then perform a variety of calculations using it." << endl;
	cout << endl;
}

//Prints out a given string to head each function call
void header (string message)
{
	cout << endl << message << endl << endl;
}

//Waits for user input to continue
void hold ()
{
	cout << "\t\t\t\tPress the [Enter] key to continue..." << endl;
	fflush(stdin);
	cin.get();
}

//Creates random values for a given array
void randomArray (double salesArray[4][6])
{
	for (int row = 0; row < 4; row++)
	{
		for (int col = 0; col < 6; col++)
		{
			salesArray[row][col] = rand() % 500;
		}
	}
}

//Displays corporateSales or any array in a box format
void displayArray (double displayArray[4][6])
{
	cout << "Division: \t" << "1 \t 2 \t 3 \t 4\t 5 \t 6\t" << endl;
	for (int row = 0; row < 4; row++)
	{
		cout << "Quarter: " << row+1 << ": \t" ;
		for (int col = 0; col < 6; col++)
		{
			cout << displayArray[row][col] << "\t";
		}
		cout << endl;
	}
}

//Displays corporateSales in a different format
void displaySalesByDivision(double salesArray[4][6])
{
	for (int col = 0; col < 3; col++)
	{
		cout << "Division " << col+1 << "\t\t\t" << "Division " << col+4 << endl;
		for (int row = 0; row < 4; row++)
		{
			cout << "\t Quarter " << row+1 << ": \t$ " << salesArray[row][col] << "\t";
			cout << "\t Quarter " << row+1 << ": \t$ " << salesArray[row][col+3];
			cout << endl;
		}
		cout << endl;
	}
}

//Calculates corporateSalesDifference and displays it
void salesDifference (double salesArray[4][6], double differenceArray[4][6])
{
	for (int row = 0; row < 3; row++)
	{
		for (int col = 0; col < 6; col++)
		{
			double difference = calcSalesDifference(salesArray, row, col);
			differenceArray[row+1][col] = difference;
		}
	}
	displaySalesDifference(differenceArray);
}

//Performs the calculations for salesDifference
double calcSalesDifference (double salesArray[4][6], int row, int col)
{
	double difference = salesArray[row+1][col] - salesArray[row][col];
	return difference;
}

//Similar to displaySalesByDivision, except only displays rows 2-4
void displaySalesDifference (double differenceArray[4][6])
{
	for (int col = 0; col < 3; col++)
	{
		cout << "Division " << col+1 << "\t\t\t" << "Division " << col+4 << endl;
		for (int row = 1; row < 4; row++)
		{
			cout << "\t Quarter " << row+1 << ": \t$ " << differenceArray[row][col] << "\t";
			cout << "\t Quarter " << row+1 << ": \t$ " << differenceArray[row][col+3];
			cout << endl;
		}
		cout << endl;
	}
}

//Displays sum of all corporateSales rows
void totalCompanySales (double salesArray[4][6])
{
	for (int row = 0; row < 4; row++)
	{
		double total = calcTotalCompanySales(salesArray, row);
		cout << "\t Quarter " << row+1 << ": \t$ " << total << endl;
	}
	cout << endl;
}

//Performs the calculations for totalCompanySales
double calcTotalCompanySales (double salesArray[4][6], int row)
{
	double total = 0;
	for (int col = 0; col < 6; col++)
	{
		total = total + salesArray[row][col];
	}
	return total;
}

//Displays the sum of all corporateSalesDifference rows
void totalCompanySalesDifference (double differenceArray[4][6])
{
	for (int row = 1; row < 4; row++)
	{
		double total = calcTotalCompanySales(differenceArray, row);
		cout << "\t Quarter " << row+1 << ": \t$ " << total << endl;
	}
	cout << endl;
}

//Performs the calculation for totalCompanySalesDifference
double calcTotalCompanyDifference (double differenceArray[4][6], int row)
{
	double total = 0;
	for (int col = 0; col < 6; col++)
	{
		total = total + differenceArray[row][col];
	}
	return total;
}

//Finds average of corporateSales rows and displays it
void averageCompanySales (double salesArray[4][6])
{
	for (int row = 0; row < 4; row++)
	{
		double average = calcAverageCompanySales(salesArray, row);
		cout << "\t Quarter " << row+1 << ": \t$ " << fixed << setprecision(2) << average << endl;
		
	}
	cout << endl;
}

//Performs the calculation for averageCompanySales
double calcAverageCompanySales (double salesArray[4][6], int row)
{
	double total = 0;
	double average = 0;
	for (int col = 0; col < 6; col++)
	{
		total = total + salesArray[row][col];
	}
	average = total/6;
	return average;
}

//Finds the highest division for each quarter and displays it
void highestQuarterlySalesDivision (double salesArray[4][6])
{
	for (int row = 0; row < 4; row++)
	{
		int highestDivision = getHighestDivision(salesArray, row);
		cout << "\t Quarter " << row+1 << ": \t" << "[Division " << highestDivision+1 << "]";
		double highest = calcHighestQuarterlySales(salesArray, row);
		cout  << "\t $" << highest << endl;
	}
	cout << endl;
}

//Returns the number corresponding to the division which is the highest in a row
int getHighestDivision (double salesArray[4][6], int row)
{
	int highestDivision;
	double highest = 0;
	for (int col = 0; col < 6; col++)
	{
		if (salesArray[row][col] > highest)
		{
			highest = salesArray[row][col];
			highestDivision = col;
		}
	}
	return highestDivision;
}

//Returns the amount made by the highest division in a given row
double calcHighestQuarterlySales (double salesArray[4][6], int row)
{
	double highest = 0;
	for (int col = 0; col < 6; col++)
	{
		if (salesArray[row][col] > highest)
			highest = salesArray[row][col];
	}
	return highest;
}

//Shows lowest selling divisions and how much they made each quarter
void lowestQuarterlySalesDivision (double salesArray[4][6])
{
	for (int row = 0; row < 4; row++)
	{
		int lowestDivision = getLowestDivision(salesArray, row);
		cout << "\t Quarter " << row+1 << ": \t" << "[Division " << lowestDivision+1 << "]";
		double lowest = calcLowestQuarterlySales(salesArray, row);
		cout  << "\t $" << lowest << endl;
	}
	cout << endl;
}

//Returns the division number of the lowest seller in a given row
int getLowestDivision (double salesArray[4][6], int row)
{
	int lowestDivision;
	double lowest = 501;
	for (int col = 0; col < 6; col++)
	{
		if (salesArray[row][col] < lowest)
		{
			lowest = salesArray[row][col];
			lowestDivision = col;
		}
	}
	return lowestDivision;
}

//Returns the amount made by the lowest seller in a given row
double calcLowestQuarterlySales (double salesArray[4][6], int row)
{
	double lowest = 501;
	for (int col = 0; col < 6; col++)
	{
		if (salesArray[row][col] < lowest)
		{
			lowest = salesArray[row][col];
		}
	}
	return lowest;
}

//End program
void end()
{
	cout << "Thank you for using the Quarterly Sales program." << endl;
	cout << "Written by Thomas de Leon on Febuary 17, 2012." << endl;
	cout << "Press the [Enter] key to exit.";
}