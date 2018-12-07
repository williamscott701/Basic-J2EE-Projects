package com.myapp.struts;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class IndexBean extends org.apache.struts.action.ActionForm {

    private String user;
    private String pass;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getUser() != null && getUser().trim().equalsIgnoreCase("")) {
            errors.add("usererror", new ActionMessage("error.username.required"));
        }
        if (getPass() != null && getPass().trim().equalsIgnoreCase("")) {
            errors.add("passerror", new ActionMessage("error.passname.required"));
        }
        return errors;
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        setUser("");
        setPass("");
        super.reset(mapping, request);
    }

}
