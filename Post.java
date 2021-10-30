package com.Codepath.myapplication;
import com.parse.ParseObject;
public class Post extends {
    @ParseClassName("Post")
    public class Post extends ParseObject{
        public static final String KEY_DESCRIPTION="description";
        public static final String KEY_IMAGE="image";
        public static final String KEY_USER="user";
        public String getDescription(){
            return getString(KEY_DESCRIPTION);
        }
    }
        public void setDescription(String description){
        put(KEY_DESCRIPTION, description);
        }
        public ParseFile getImage(){
        return getParseFile(KEY_IMAGE)
        }
        public void setImage(ParseFile,parseFile);{
            put(KEY_IMAGE,parseFile);

        public ParseFile getUser(){
            return getParseUser(KEY_USER);
        }
        public void setUser(ParseUser user){
            put(KEY_USER, user);
        }
    }

}
