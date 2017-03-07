package org.launchcode.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.models.Language;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by stephen on 3/6/17.
 */

@Controller
public class HelloController {

    @RequestMapping(value="", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        String form = "<form method='POST'>" +
                "<input name='name' type='text' />" +
                "<select name='lang'>";

        for(Language l : Language.getLanguages().values()) {
            form += "<option value='" + l.value + "'>" + l.name + "</option>";
        }

        return  form + "</select>" +
                "<input type='submit' value='Greet Me!' />" +
                "</form>";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public String greeting(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="name", defaultValue = "World!", required = false) String name, @RequestParam(name = "lang", defaultValue = "EN", required = false) String lang) {
        Cookie count = null;
        for(Cookie c : request.getCookies()) {
            if(c.getName().equals("greeting-count")){
                count = c;
            }
        }

        if(count == null) {
            count = new Cookie("greeting-count", "0");
        }
        count.setValue((Integer.parseInt(count.getValue()) + 1) + "");
        response.addCookie(count);

        return "<h2>" + Language.greetingForLang(lang) + " " + name + "</h2><h4>Count : " + count.getValue() + "</h4>";
    }
}
