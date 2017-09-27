package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import dao.DAO;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class MainController {
    private DAO dao;

    @Autowired
    MainController(DAO dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage(@RequestParam("name") String imageName) throws IOException{
        if (!"".equals(imageName)) {
            return dao.getImageResponse(imageName);
        } else {
            return new byte[1];
        }
    }
}
