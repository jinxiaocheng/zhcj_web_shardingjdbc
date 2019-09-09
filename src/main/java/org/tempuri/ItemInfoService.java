package org.tempuri;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.16
 * 2018-07-23T14:32:11.934+08:00
 * Generated source version: 3.0.16
 * 
 */
@WebServiceClient(name = "ItemInfoService", 
                  wsdlLocation = "http://dmt.bosafe.com/DataWebservice/ItemInfoservice.asmx?wsdl",
                  targetNamespace = "http://tempuri.org/") 
public class ItemInfoService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "ItemInfoService");
    public final static QName ItemInfoServiceSoap = new QName("http://tempuri.org/", "ItemInfoServiceSoap");
    public final static QName ItemInfoServiceSoap12 = new QName("http://tempuri.org/", "ItemInfoServiceSoap12");
    static {
        URL url = null;
        try {
            url = new URL("http://dmt.bosafe.com/DataWebservice/ItemInfoservice.asmx?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ItemInfoService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://dmt.bosafe.com/DataWebservice/ItemInfoservice.asmx?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ItemInfoService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ItemInfoService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ItemInfoService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ItemInfoService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ItemInfoService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ItemInfoService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns ItemInfoServiceSoap
     */
    @WebEndpoint(name = "ItemInfoServiceSoap")
    public ItemInfoServiceSoap getItemInfoServiceSoap() {
        return super.getPort(ItemInfoServiceSoap, ItemInfoServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ItemInfoServiceSoap
     */
    @WebEndpoint(name = "ItemInfoServiceSoap")
    public ItemInfoServiceSoap getItemInfoServiceSoap(WebServiceFeature... features) {
        return super.getPort(ItemInfoServiceSoap, ItemInfoServiceSoap.class, features);
    }
    /**
     *
     * @return
     *     returns ItemInfoServiceSoap
     */
    @WebEndpoint(name = "ItemInfoServiceSoap12")
    public ItemInfoServiceSoap getItemInfoServiceSoap12() {
        return super.getPort(ItemInfoServiceSoap12, ItemInfoServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ItemInfoServiceSoap
     */
    @WebEndpoint(name = "ItemInfoServiceSoap12")
    public ItemInfoServiceSoap getItemInfoServiceSoap12(WebServiceFeature... features) {
        return super.getPort(ItemInfoServiceSoap12, ItemInfoServiceSoap.class, features);
    }

}