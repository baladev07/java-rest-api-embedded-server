package com.RestApi;

public abstract  class APIHandler {

    public Object handlePreProcess(com.RestApi.RestAPIcontext context)
    {
        return null;
    }
    public void processDo(Object obj, com.RestApi.RestAPIcontext context) throws Exception {

    }

    public abstract Object handlePreprocess(com.RestApi.RestAPIcontext context);

    public abstract Object postProcess(com.RestApi.RestAPIcontext context);

}
