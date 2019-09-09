package com.escst.commons.decrypt;

import com.escst.commons.utils.DESUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

import java.util.*;


/**
 * @author dwj
 * @desc
 * @date 15:04 2018/5/7
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {


    private List<String> encryptPropNames =new ArrayList<String>();

    protected Resource[] locations;


    @Override
    public void setLocations(Resource[] locations) {   //由于location是父类私有，所以需要记录到本类的locations中
        super.setLocations(locations);
        this.locations = locations;
        for(Resource location : locations){
            String fileName = location.getFilename();
            if(fileName.equals("jdbc.properties")){
                encryptPropNames.add("jdbc.url");
                encryptPropNames.add("jdbc.username");
                encryptPropNames.add("jdbc.password");
            }else if(fileName.equals("redis.properties")){
                encryptPropNames.add("redis.host");
                encryptPropNames.add("redis.password");
            }
        }
    }

    @Override
    protected void processProperties(
            ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        Map<String,Object> ctxPropertiesMap = new HashMap<String, Object>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }

    @Override
    public void setLocation(Resource location) {   //由于location是父类私有，所以需要记录到本类的locations中
        super.setLocation(location);
        this.locations = new Resource[]{location};
    }

    @Override
    protected String convertProperty(String propertyName, String propertyValue)
    {
        //如果在加密属性名单中发现该属性
        if (isEncryptProp(propertyName))
        {
            String decryptValue = DESUtils.getDecryptString(propertyValue);
            System.out.println(decryptValue);
            return decryptValue;
        }else {
            return propertyValue;
        }

    }

    private boolean isEncryptProp(String propertyName)
    {
        for (String encryptName : encryptPropNames)
        {
            if (encryptName.equals(propertyName))
            {
                return true;
            }
        }
        return false;
    }



}
