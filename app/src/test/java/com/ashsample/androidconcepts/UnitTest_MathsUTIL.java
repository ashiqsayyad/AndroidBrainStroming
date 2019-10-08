package com.ashsample.androidconcepts;

import com.ashsample.androidconcepts.util.MathsUTIL;


import org.junit.Assert;
import org.junit.Test;

public class UnitTest_MathsUTIL {
//https://developer.android.com/training/testing/fundamentals

    @Test
    public void sum_twoPostiveNos_ActualSum() {
        Assert.assertEquals(3, MathsUTIL.sum(1, 2));

    }

    @Test
    public void sum_twoZeros_Zero() {
        Assert.assertSame(0, MathsUTIL.sum(0, 0));

    }



}


