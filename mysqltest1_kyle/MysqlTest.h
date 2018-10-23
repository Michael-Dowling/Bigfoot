#pragma once
#include <mysql.h>
#include <string>

class RecycleDB{
public:
	RecycleDB(const std::string = "localhost", const std::string = "", const std::string = "", const std::string = "");
//	~recycledb();
	void create();
	void printAll(std::string);
	void printByUPC(int,std::string);
	void add(int,std::string,std::string,unsigned long long int,std::string);
	// auto_incrememnt primary key id integer
	void add(std::string, std::string, unsigned long long int,std::string);
private:
	MYSQL* db_conn;
};
