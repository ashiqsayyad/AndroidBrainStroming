package com.ashsample.androidconcepts;

import com.ashsample.androidconcepts.github.GitHubRepo;

import org.junit.Assert;
import org.junit.Test;

public class UnitTest_GitHubRepo {
    @Test
    public void getALLUsers_NOTNULL(){
        Assert.assertNotNull(GitHubRepo.getInstance().getALLUsers());
    }

    @Test
    public void getUserDetail_NOTNULL(){
        Assert.assertNotNull(GitHubRepo.getInstance().getUserDetail("ashiqsayyad"));
    }

    @Test
    public void getContributorsforRepo_NOTNULL(){
        Assert.assertNotNull(GitHubRepo.getInstance().getContributorsforRepo());
    }

}
