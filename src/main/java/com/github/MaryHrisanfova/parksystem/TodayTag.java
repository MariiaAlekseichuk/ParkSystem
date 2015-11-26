package com.github.MaryHrisanfova.parksystem;

/**
 * Created by ���� on 23.11.2015.
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TodayTag extends TagSupport{
    private String mFormat;

    public void setFormat(String pFormat) {
        mFormat = pFormat;
    }


    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            Date today = new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat(mFormat);
            out.print(dateFormatter.format(today));

        } catch(IOException ioe) {
            throw new JspException("Error: " + ioe.getMessage());

        }
        //return SKIP_BODY;
        return EVAL_BODY_INCLUDE;
    }


    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}