package com.mysite.core.serve2;

import com.mysite.core.serve1.Service;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@Component(service = Service.class)
@Designate(ocd = Config.Config1.class)

public class Config implements Service {
    private boolean author;

    @Activate
    @Modified
    public void active(Config1 config) {
        this.author = config.isAuthor();
    }
    public boolean isAuthor() {
        return author;
    }
    @Override
    public String author() {
        if (isAuthor())
            return "This servlet is executed from author environment";
        else
            return "This servlet is executed from publisher environment";
    }
    @Override
    public String publisher() {
        return null;
    }
    @ObjectClassDefinition(name = "servletauthpub", description = "This is our first servlet")
    public @interface Config1 {
        @AttributeDefinition(name = "author", description = "This is author definition", type = AttributeType.BOOLEAN)
        boolean isAuthor() default true;
    }
}