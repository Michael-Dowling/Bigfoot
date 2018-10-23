// MysqlTest.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

//#include "stdafx.h"
#include "pch.h"
#include <iostream>
#include <mysql.h>
#include <string>
#include "MysqlTest.h"
using namespace std;
int qstate;
//string db_name = "recycletest2";

RecycleDB::RecycleDB(const string HOST, const string USER, const string PASSWORD, const string DATABASE) {
	db_conn = mysql_init(0);
	db_conn = mysql_real_connect(db_conn, HOST.c_str(), USER.c_str(), PASSWORD.c_str(), DATABASE.c_str(), 3306, NULL, 0);
	if (db_conn) {
		puts("Connection success!!!!");
	}
	else {
		puts("Connection failed.");
	}
}

// havent done this yet
void RecycleDB::create() {

}

void RecycleDB::printAll(string db_name) {
	MYSQL_ROW row;
	MYSQL_RES *res;
	string query = "SELECT * FROM " + db_name;
	const char* q = query.c_str();
	qstate = mysql_query(db_conn, q);
	if (!qstate) {
		res = mysql_store_result(db_conn);
		while (row = mysql_fetch_row(res)) {
			printf("ID: %s, Name: %s, Bin Type: %s, UPC Code: %s\n", row[0], row[1], row[2], row[3]);
		}
	}
	else {
		cout << "Query failed " << mysql_error(db_conn) << endl;
	}
}

void RecycleDB::printByUPC(int upc, string db_name) {
	MYSQL_ROW row;
	MYSQL_RES *res;
	string query = "SELECT * FROM " + db_name + " WHERE UPC_code = " + to_string(upc);
	const char* q = query.c_str();
	qstate = mysql_query(db_conn, q);
	if (!qstate) {
		res = mysql_store_result(db_conn);
		while (row = mysql_fetch_row(res)) {
			printf("ID: %s, Name: %s, Bin Type: %s, UPC Code: %s\n", row[0], row[1], row[2], row[3]);
		}
	}
	else {
		cout << "Query failed " << mysql_error(db_conn) << endl;
	}
}


void RecycleDB::add(int Vid, string Vname, string VbinType, unsigned long long int VUPC_code, string db_name) {
	const char *qstr;
	string qstrstr = "INSERT INTO " + db_name + "(id, name, binType, UPC_code) VALUES(" + to_string(Vid) + ", '" + Vname + "', '" + VbinType + "', " + to_string(VUPC_code) + ")";
	qstr = qstrstr.c_str();
	mysql_query(db_conn, qstr);
}


void RecycleDB::add(string Vname, string VbinType, unsigned long long int VUPC_code, string db_name) {
	const char *qstr;
	string qstrstr = "INSERT INTO " + db_name + "(name, binType, UPC_code) VALUES('" + Vname + "', '" + VbinType + "', " + to_string(VUPC_code) + ")";
	qstr = qstrstr.c_str();
	mysql_query(db_conn, qstr);
}

int main()
{
	// need to use unsigned long long int

	//RecycleDB test1 = RecycleDB("localhost", "root", "oDtst0ahc*ukhigmtpmw", "testdb");
	
	//test1.add(7, "Banana Peel", "Compost", 1243242);
	//test1.printAll();
	//test1.printByUPC(102394324);

	RecycleDB test2 = RecycleDB("localhost", "root", "oDtst0ahc*ukhigmtpmw", "testdb");

	//drop the table and re run in each time. 
	//test2.add("Gatorade Bottle", "Blue Bin", 36000291452, "recycletest2");
	test2.printAll("recycletest2");
	return 0;
}



// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file


	/*
	MYSQL* conn;
	MYSQL_ROW row;
	MYSQL_RES *res;
	conn = mysql_init(0);

	conn = mysql_real_connect(conn, "localhost", "root", "oDtst0ahc*ukhigmtpmw","testdb", 3306, NULL, 0);

	if (conn) {
		puts("Connection success!!!!");

		//const char *qstr;
		//string qstrstr = "INSERT INTO recycletest(id, name, binType, UPC_code) VALUES(6,'Granola Bar Wrapper','Garbage',23534532)";
		//qstr = qstrstr.c_str();
		//mysql_query(conn, qstr);

		int cokeCanUPC = 102394324;
		string query = "SELECT * FROM recycletest"; //WHERE UPC_code = " + to_string(cokeCanUPC);
		const char* q = query.c_str();
		qstate = mysql_query(conn, q);
		if (!qstate) {
			res = mysql_store_result(conn);
			while (row = mysql_fetch_row(res)) {
				printf("ID: %s, Name: %s, Bin Type: %s, UPC Code: %s\n", row[0], row[1], row[2], row[3]);
			}
		}
		else {
			cout << "Query failed " << mysql_error(conn) << endl;
		}
	}
	else {
		puts("Connection failed.");
	}

	*/