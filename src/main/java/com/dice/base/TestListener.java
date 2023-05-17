package com.dice.base;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(iTestResult.getTestContext().getCurrentXmlTest().getName() + " Test Success");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println(iTestResult.getTestContext().getCurrentXmlTest().getName() + " Test Failure");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println(iTestContext.getCurrentXmlTest().getName() + " Test Start");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println(iTestContext.getCurrentXmlTest().getName() + " Test Finish");
    }
}
