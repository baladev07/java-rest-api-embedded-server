package com.RestApi;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="",propOrder = {"request"})
@XmlRootElement(name="RequestMapper")
public
class RequestMapper{

    @XmlElement(name="request")
    protected List<RequestMapper.Request> request;

    public void setRequest(Request request)
    {
        this.request= (List<Request>) request;;
    }

    public List<RequestMapper.Request> getRequest()
    {
        return this.request;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="",propOrder = {"parameter"})
    public static class Request {

        @XmlAttribute(name="class")
        protected String classname;

        @XmlAttribute(name="uri")
        protected String uri;

        @XmlAttribute(name="method")
        protected String method;

        @XmlAttribute(name="CVName")
        protected String CVName;

        @XmlAttribute(name="EntityName")
        protected  String entityName;

        public String getEntityName() {
            return entityName;
        }
        public void setEntityName(String entityName) {
            this.entityName = entityName;
        }
        public String getMethod() {
            return method;
        }
        public void setMethod(String method) {
            this.method = method;
        }
        public String getClassname() {
            return classname;
        }
        public void setClassname(String classname) {
            this.classname = classname;
        }
        public String getUri(String id) {
            String uri="/api"+this.uri;
            if(id!=null)
            {
                uri=uri.replace("(\\d+)",id);
            }
            return uri;
        }
        public void setUri(String uri) {this.uri = uri;}
        public String getCVName()
        {
            return CVName;
        }
        public void setCVName(String CVName){
            this.CVName=CVName;
        }

        @XmlElement(name="parameter")
        protected List<RequestMapper.Request.Parameter> parameter;

        public void setParameters(Parameter param)
        {
            this.parameter= (List<RequestMapper.Request.Parameter>) param;
        }

        public List<RequestMapper.Request.Parameter> getParameters()
        {
            return this.parameter;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Parameter
        {
            @XmlAttribute(name="name")
            protected String name;

            @XmlAttribute(name="type")
            protected String type;

            @XmlAttribute(name="value")
            protected String value;

            @XmlAttribute(name="source")
            protected String source;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }
        }
   }
}
