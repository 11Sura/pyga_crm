package com.pyga_crm.genericLib;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class ITransform implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
         
		annotation.setRetryAnalyzer(com.pyga_crm.genericLib.RetryImplementation.class);
	}
	

}
