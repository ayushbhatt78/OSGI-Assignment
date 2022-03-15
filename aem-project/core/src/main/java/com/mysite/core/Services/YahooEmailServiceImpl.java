package com.mysite.core.Services;


import com.mysite.core.Inter1.EmailService1;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@Component(service = EmailService1.class)
@Designate(ocd = YahooEmailServiceImpl.Config.class)
public class YahooEmailServiceImpl implements EmailService1{

    private boolean disable;
    private String path;

    @ObjectClassDefinition(name = "Yahoo Email Service Config", description = "Yahoo Email Service Configuration")
    public @interface Config {
        @AttributeDefinition(name = "Disable", description = "If TRUE then no email would be sent", type = AttributeType.BOOLEAN)
        boolean disable() default false;

        @AttributeDefinition(name = "Url of Google Email Service", description = "Url of Google Email Service", type = AttributeType.STRING)
        String path() default "/test";
    }

    @Activate
    @Modified
    public void activate(Config config) throws ConfigurationException {
        this.disable = config.disable();
        this.path = config.path();
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String sendEmail() {
        if (isDisable()) {
            return "Can't send email as config is disabled";
        } else {
            return "Email is sent with path" + getPath();
        }
    }
}
