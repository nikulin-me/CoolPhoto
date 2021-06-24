package nikulin.app.model.util;

import nikulin.app.model.User;

public abstract  class PhotoUtil {
    public static String getAuthorName (User author){
        return author!=null?author.getUsername():"<none>";
    }
}
