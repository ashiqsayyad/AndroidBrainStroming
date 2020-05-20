// IRemote.aidl
package com.example.remoteservice_calculator;

// Declare any non-default types here with import statements

interface IRemote
	{
	  int add(int a, int b);
	  int subtract(int a, int b);
	  double multiply(int a, int b);
	}
