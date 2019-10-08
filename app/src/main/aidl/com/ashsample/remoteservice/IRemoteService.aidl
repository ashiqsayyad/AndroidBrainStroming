// IRemoteService.aidl
package com.ashsample.remoteservice;

// Declare any non-default types here with import statements

interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    /** Request the process ID of this service, to do evil things with it. */
                int getPid();
      int add(int a, int b);
      int subtract(int a, int b);
      double multiply(int a, int b);
}
