package com.Exception;


import com.RestApi.RestAPIcontext;
import com.Util.Util;

public class GeneralException extends Exception {
    public GeneralException(RestAPIcontext context) throws Exception{
        Util.handleErrorResponse(context,null);
    }

    public GeneralException(RestAPIcontext context,Exception e) throws Exception {
        Util.handleDuplicateField(e, context);
    }

//    public GeneralException() throws Exception {
//        Util.handleDuplicateField("", "");
//    }
}
